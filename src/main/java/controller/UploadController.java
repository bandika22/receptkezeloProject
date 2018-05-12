package controller;

import dao.RecipeDAO;
import impl.RecipeImpl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.*;
import org.slf4j.LoggerFactory;
import service.ReceptService;
import service.ReceptServiceImpl;

import java.util.ArrayList;
import java.util.List;


public class UploadController {

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(UploadController.class);

    @FXML
    public Label warningMessage;

    @FXML
    public ComboBox<EtelTipus> mealTypeCB;

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

    private HBox hBox;
    private TextField newField;
    private ComboBox<Tipus> typeComboBox;
    private String ingredientsName;
    private String typeName;
    private int db = 0;

    private RecipeDAO recipeDAO = new RecipeImpl(DBManager.getInstance());
    private ReceptService receptServiceDAO = new ReceptServiceImpl(recipeDAO);

    public void initialize(){
        mealTypeCB.getItems().addAll(
                EtelTipus.ELŐÉTEL,
                EtelTipus.LEVES,
                EtelTipus.FŐÉTEL,
                EtelTipus.DESSZERT
        );
        mealTypeCB.setValue(EtelTipus.FŐÉTEL);
    }


    @FXML
    private void handleButton1Action() {

        boolean empty = false;

        for (Node node : capitalpane.getChildren()) {
            if (node instanceof TextArea) {
                if (((TextArea) node).getText().trim().isEmpty()) {
                    log.warn("Empty description field");
                    message.setText("Kérlek töltsd ki a mezőket");
                    message.setTextFill(Color.RED);
                    empty = true;
                    break;
                }
            } else if (node instanceof TextField) {
                if (((TextField) node).getText().trim().isEmpty()) {
                    log.warn("Empty recipe name field");
                    message.setText("Kérlek töltsd ki a mezőket");
                    message.setTextFill(Color.RED);
                    empty = true;
                    break;
                } else if (((TextField) node).getText().matches("-?\\d+(\\.\\d+)?")) {
                    log.warn("Tried to add number instead of recipe name");
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
                            log.warn("Tried to add a number instead of ingredients");
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

            String recipeDescription = recipeDescriptionField.getText().toUpperCase();
            String recipeName = recipeNameField.getText().toUpperCase();
            String mealType = mealTypeCB.getValue().toString();

            recipeDescriptionField.clear();
            recipeNameField.clear();

            for (Node node : pane_main_grid.getChildren()) {
                if (node instanceof HBox) {
                    for (Node node1 : ((HBox) node).getChildren()) {
                        if (node1 instanceof TextField) {
                            ingredientsName = ((TextField) node1).getText().toUpperCase();
                        }
                        else if (node1 instanceof ComboBox) {
                            typeName = ((ComboBox) node1).getValue().toString();
                        }
                    }
                    ingredientsList.add(new Hozzavalo(ingredientsName, typeName));
                }
            }

            if (ingredientsList.isEmpty()) {
                message.setText("A hozzávalók listáját nem töltötted ki!");
                message.setTextFill(Color.RED);
                log.warn("Tried to upload a recipe without ingredients");
            } else {

                Recept recept = new Recept(recipeName, recipeDescription, mealType);
                receptServiceDAO.createIngredientsAddToRecipe(ingredientsList, recept);
                receptServiceDAO.createRecipe(recept);



                message.setText("Sikeresen hozzáadtad a receptet!");
                message.setTextFill(Color.GREEN);

                pane_main_grid.getChildren().clear();

                log.info("Add a recipe successfully");

            }
        }
    }

    @FXML
    public void AddTextField1() {

        log.info("Add a new TextField for ingredients");

        hBox = new HBox();
        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll(
                Tipus.HÚS,
                Tipus.KÖRET,
                Tipus.ZÖLDSÉG,
                Tipus.GYÜMÖLCS,
                Tipus.FŰSZER,
                Tipus.TEJTERMÉK,
                Tipus.ALAPANYAG
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
        } else {
            warningMessage.setText("Elérte a maximálisan bevihető hozzávalót!");
            log.warn("Tried to add new TextField more than 13");
        }
    }

    @FXML
    public void deleteTextField() {
            log.info("Deleted the last TextField");
            hBox.getChildren().remove(newField);
            hBox.getChildren().remove(typeComboBox);
            db--;
    }
}
