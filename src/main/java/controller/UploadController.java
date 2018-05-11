package controller;

import dao.ReceptDAO;
import impl.ReceptImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.DBManager;
import model.Hozzavalo;
import model.Recept;
import model.Tipus;
import service.ReceptService;
import service.ReceptServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class UploadController {

    @FXML
    public Label warningMessage;

    @FXML
    private TextArea recipeDescriptionField;

    @FXML
    private TextField recipeNameField;

    @FXML
    private VBox pane_main_grid;

    @FXML
    private Pane capitalpane;

    @FXML
    private Label message;

    private String recipeDescription;
    private String recipeName;
    private HBox hBox;
    private TextField newField;
    private ComboBox typeComboBox;
    private String ingredientsName;
    private String typeName;
    private int db = 0;

    ReceptDAO recipeDAO = new ReceptImpl(DBManager.getInstance());
    ReceptService receptServiceDAO = new ReceptServiceImpl(recipeDAO);


    @FXML
    private void handleButton1Action(ActionEvent event) {

        boolean empty = false;

        for (Node node : capitalpane.getChildren()) {
            if (node instanceof TextArea) {
                if (((TextArea) node).getText().trim().isEmpty()) {
                    message.setText("Kérlek töltsd ki a mezőket");
                    message.setTextFill(Color.RED);
                    empty = true;
                    break;
                }
            } else if (node instanceof TextField) {
                if (((TextField) node).getText().trim().isEmpty()) {
                    message.setText("Kérlek töltsd ki a mezőket");
                    message.setTextFill(Color.RED);
                    empty = true;
                    break;
                } else if (((TextField) node).getText().matches("-?\\d+(\\.\\d+)?")) {
                    message.setText("Számokat nem adhatsz meg");
                    message.setTextFill(Color.RED);
                    empty = true;
                    break;
                } else {
                    message.setText("");
                    empty = false;
                }
            } else if (node instanceof VBox) {
                for (Node node1 : pane_main_grid.getChildren()) {
                    if (node1 instanceof TextField) {
                        if (((TextField) node1).getText().trim().isEmpty()) {
                            message.setText("Kérlek töltsd ki a mezőket");
                            message.setTextFill(Color.RED);
                            empty = true;
                            break;
                        } else {
                            message.setText("");
                            empty = false;
                        }
                    }
                }
            }
        }


        if(!empty) {

            List<Hozzavalo> ingredientsList = new ArrayList<>();

            recipeDescription = recipeDescriptionField.getText().toUpperCase();
            recipeName = recipeNameField.getText().toUpperCase();

            recipeDescriptionField.clear();
            recipeNameField.clear();

            for (Node node : pane_main_grid.getChildren()) {
                if (node instanceof HBox) {
                    for (Node node1 : ((HBox) node).getChildren()) {
                        if (node1 instanceof TextField) {
                            ingredientsName = ((TextField) node1).getText().toUpperCase();
                            if (node1 instanceof ComboBox) {
                                typeName = ((ComboBox) node1).getValue().toString();
                            }
                            ingredientsList.add(new Hozzavalo(ingredientsName, typeName));
                            ((TextField) node1).clear();
                        }
                    }
                }
            }
            Recept recept = new Recept(recipeName, recipeDescription);

            if (ingredientsList.isEmpty()) {
                message.setText("A hozzávalók listáját nem töltötted ki!");
                message.setTextFill(Color.RED);
            } else {

                try {
                    receptServiceDAO.createHozzavaloAddToRecept(ingredientsList, recept);
                    receptServiceDAO.createRecept(recept);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


                message.setText("Sikeresen hozzáadtad a receptet!");
                message.setTextFill(Color.GREEN);

                for (Node child : capitalpane.getChildren()) {
                    pane_main_grid.getChildren().clear();
                }
            }
        }
    }

    @FXML
    public void AddTextField1(MouseEvent event) {

        hBox = new HBox();
        typeComboBox = new ComboBox();
        typeComboBox.getItems().addAll(
                Tipus.HÚS,
                Tipus.KÖRET,
                Tipus.ZÖLDSÉG,
                Tipus.GYÜMÖLCS
        );
        typeComboBox.setValue(Tipus.HÚS);

        newField = new TextField();
        HBox.setMargin(newField, new Insets(0, 10, 10, 20));
        HBox.setMargin(hBox, new Insets(0, 0, 10, 10));


        newField.setPrefWidth(150.0);
        newField.setPrefHeight(10.0);
        typeComboBox.setPrefWidth(100.0);
        if (db <= 13) {
            pane_main_grid.getChildren().add(hBox);
            hBox.getChildren().add(newField);
            hBox.getChildren().add(typeComboBox);
            db++;
        } else
            warningMessage.setText("Elérte a maximálisan bevihető hozzávalót!");
    }

    @FXML
    public void deleteTextField(MouseEvent event) {

            hBox.getChildren().remove(newField);
            hBox.getChildren().remove(typeComboBox);
            db--;
    }
}
