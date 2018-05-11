package controller;

import dao.ReceptDAO;
import impl.ReceptImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.DBManager;
import model.Recept;
import model.Tipus;
import service.ReceptService;
import service.ReceptServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchController {

    @FXML
    public HBox hboxPane;

    @FXML
    public VBox vboxPane0;

    @FXML
    public VBox vboxPane1;

    @FXML
    public VBox vboxPane2;

    @FXML
    public Text warningMessage;

    @FXML
    public SplitPane splitPane;

    @FXML
    public CheckBox checkBox1;

    @FXML
    public CheckBox checkBox2;

    @FXML
    public CheckBox checkBox3;

    @FXML
    public CheckBox checkBox4;

    @FXML
    private ListView<String> receptView;

    @FXML
    private Label getReceptLeiras;

    @FXML
    private Label getHozzavalo;


    List<String> receptItem = new ArrayList<>();
    List<Recept> resultRecept;
    List<Recept> filteredRecept;

    ReceptDAO dao = new ReceptImpl(DBManager.getInstance());
    ReceptService receptService = new ReceptServiceImpl(dao);

    public void initialize(){
        receptView.setVisible(true);
        splitPane.setVisible(true);
        checkBox1.setText(Tipus.HÚS.name());
        checkBox2.setText(Tipus.KÖRET.name());
        checkBox3.setText(Tipus.ZÖLDSÉG.name());
        checkBox4.setText(Tipus.GYÜMÖLCS.name());

    }

    @FXML
    public void handleClickListView(MouseEvent event) {

        if (!resultRecept.isEmpty()) {
            receptView.setVisible(true);
            String element = receptView.getSelectionModel().getSelectedItem();

            for (int i = 0; i < resultRecept.size(); i++) {
                if (element.equals(resultRecept.get(i).getName())) {
                    getReceptLeiras.setText(resultRecept.get(i).getDescription());
                    getHozzavalo.setText(resultRecept.get(i).getHozzavalok().toString());
                }
            }
        }


    }
    int db = 0;
    @FXML
    public void addSearchField(MouseEvent event) {
        TextField newField = new TextField();
        VBox.setMargin(newField, new Insets(0,0,5,0));
        newField.setPrefWidth(160.0);
        newField.setPrefHeight(25.0);
        if (0 <= db && db < 5) {
            vboxPane0.getChildren().add(newField);
            db++;
        }
        else if(5 <= db && db < 10){
            vboxPane1.getChildren().add(newField);
            db++;
        }
        else if(10 <= db && db < 15){
            vboxPane2.getChildren().add(newField);
            db++;
        }
        else
            warningMessage.setText("Maximum 15 hozzávalót adhatsz hozzá !");
    }

    @FXML
    public void searchItems(MouseEvent event) {

        getReceptLeiras.setText("");
        getHozzavalo.setText("");

        warningMessage.setText("");
        receptItem.clear();

        List<String> hozzavaloList = new ArrayList<>();

        for (Node node : hboxPane.getChildren()) {
            if (node instanceof VBox) {
                for (Node node1 : ((VBox) node).getChildren()) {
                    if (node1 instanceof TextField) {
                        String searchItem = ((TextField) node1).getText().toUpperCase();
                        hozzavaloList.add(searchItem);
                        ((TextField) node1).clear();
                        System.out.println(searchItem);
                    }
                }
            }
        }
        resultRecept = receptService.searchRecept(hozzavaloList);

        if(resultRecept.isEmpty()){
            warningMessage.setText("Nincs a keresésnek megfelelő recept az adatbázisban");
        } else {
            receptView.setVisible(true);
            splitPane.setVisible(true);
            for (int i = 0; i < resultRecept.size(); i++){
                receptItem.add(resultRecept.get(i).getName());
            }
        }
        receptView.getItems().setAll(receptItem);
        receptView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void filter(ActionEvent event) {
        /*getReceptLeiras.setText("");
        getHozzavalo.setText("");
        warningMessage.setText("");
        receptItem.clear();

        List<String> typeList = new ArrayList<>();

        if(checkBox1.isSelected())
            typeList.add(checkBox1.getText());
        if(checkBox2.isSelected())
            typeList.add(checkBox2.getText());
        if(checkBox3.isSelected())
            typeList.add(checkBox3.getText());
        if(checkBox4.isSelected())
            typeList.add(checkBox4.getText());


        filteredRecept = receptService.searchFilteredRecept(typeList);

        for (int i = 0; i < filteredRecept.size(); i++){
            receptItem.add(filteredRecept.get(i).getName());
        }
        System.out.println(receptItem.toString());
        System.out.println(receptService.searchFilteredRecept(typeList).toString());*/

        /*receptView.getItems().setAll(receptItem);
        receptView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);*/


       // System.out.println(receptService.getAllRecept());
    }
}
