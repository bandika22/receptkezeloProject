package controller;

import dao.RecipeDAO;
import dao.impl.RecipeImpl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;
import org.slf4j.LoggerFactory;
import service.RecipeService;
import service.RecipeServiceImpl;
import utility.DBManager;

import java.util.ArrayList;
import java.util.List;

public class SearchController {

    protected static final org.slf4j.Logger log = LoggerFactory.getLogger(SearchController.class);

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
    public CheckBox checkBox11;

    @FXML
    public CheckBox checkBox21;

    @FXML
    public CheckBox checkBox31;

    @FXML
    public CheckBox checkBox41;

    @FXML
    public TextArea getRecipeDescription;

    @FXML
    public TextArea getIngredients;

    @FXML
    private ListView<String> recipeView;


    private List<String> recipeItems = new ArrayList<>();
    private List<Recept> resultRecipe;
    private int count = 0;

    private RecipeService recipeService;

    public SearchController() {
        RecipeDAO dao = new RecipeImpl(DBManager.getInstance());
        recipeService = new RecipeServiceImpl(dao);
    }

    public void initialize(){
        recipeView.setVisible(true);
        splitPane.setVisible(true);

        checkBox1.setText(MealType.FŐÉTEL.name());
        checkBox2.setText(MealType.LEVES.name());
        checkBox3.setText(MealType.ELŐÉTEL.name());
        checkBox4.setText(MealType.DESSZERT.name());

        checkBox11.setText(IngredientsType.HÚS.name());
        checkBox21.setText(IngredientsType.ZÖLDSÉG.name());
        checkBox31.setText(IngredientsType.TEJTERMÉK.name());
        checkBox41.setText(IngredientsType.GYÜMÖLCS.name());

    }

    @FXML
    public void handleClickListView() {

        if (!resultRecipe.isEmpty()) {
            recipeView.setVisible(true);
            String element = recipeView.getSelectionModel().getSelectedItem();

            for (Recept aResultRecipe : resultRecipe) {
                if (element.equals(aResultRecipe.getName())) {
                    getRecipeDescription.setText(aResultRecipe.getDescription());
                    getIngredients.setText("");
                    for (int j = 0; j < aResultRecipe.getIngredients().size(); j++) {
                        getIngredients.appendText(aResultRecipe.getIngredients().get(j).getName());
                        getIngredients.appendText("\n");
                    }
                }
            }
            log.info("Choose the " + element + " recipe");
        }
    }

    @FXML
    public void addSearchField() {

        log.info("Add a new TextField for search ...");

        TextField newField = new TextField();
        VBox.setMargin(newField, new Insets(0,0,5,0));
        newField.setPrefWidth(160.0);
        newField.setPrefHeight(25.0);
        if (0 <= count && count < 5) {
            vboxPane0.getChildren().add(newField);
            count++;
        }
        else if(5 <= count && count < 10){
            vboxPane1.getChildren().add(newField);
            count++;
        }
        else if(10 <= count && count < 15){
            vboxPane2.getChildren().add(newField);
            count++;
        }
        else{
            warningMessage.setText("Maximum 15 hozzávalót adhatsz hozzá !");
            log.debug("Tried to add more TextField than 15");
        }
    }

    private List<String> ingredientsList = new ArrayList<>();
    private List<String> typeList = new ArrayList<>();
    @FXML
    public void searchItems() {
        log.info("Searched for the recipes");

        getRecipeDescription.setText("");
        getIngredients.setText("");

        warningMessage.setText("");
        recipeItems.clear();



        for (Node node : hboxPane.getChildren()) {
            if (node instanceof VBox) {
                for (Node node1 : ((VBox) node).getChildren()) {
                    if (node1 instanceof TextField) {
                        String searchItem = ((TextField) node1).getText().trim().toUpperCase();
                        ingredientsList.add(searchItem);
                    }
                }
            }
        }
        resultRecipe = recipeService.searchRecipe(ingredientsList);

        if(resultRecipe.isEmpty()){
            warningMessage.setText("Nincs a keresésnek megfelelő recept az adatbázisban");
            log.info("The search was unsuccessfully");
        } else {
            recipeView.setVisible(true);
            splitPane.setVisible(true);
            for (Recept aResultRecipe : resultRecipe) {
                recipeItems.add(aResultRecipe.getName());
            }
            log.info("The search was successfully");
        }
        recipeView.getItems().setAll(recipeItems);
        recipeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void filter() {

        getRecipeDescription.setText("");
        getIngredients.setText("");
        warningMessage.setText("");
        recipeItems.clear();



        if(checkBox1.isSelected())
            typeList.add(checkBox1.getText().toUpperCase());
        if(checkBox2.isSelected())
            typeList.add(checkBox2.getText().toUpperCase());
        if(checkBox3.isSelected())
            typeList.add(checkBox3.getText().toUpperCase());
        if(checkBox4.isSelected())
            typeList.add(checkBox4.getText().toUpperCase());

        log.info("Filtered the recipes: " + typeList.toString());

        List<Recept> filteredRecipe = recipeService.searchFilteredRecipe(typeList, ingredientsList);

        for (Recept aFilteredRecipe : filteredRecipe) {
            recipeItems.add(aFilteredRecipe.getName());
        }

        recipeView.getItems().setAll(recipeItems);
        recipeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);

    }

    @FXML
    public void contains() {

        getRecipeDescription.setText("");
        getIngredients.setText("");
        warningMessage.setText("");
        recipeItems.clear();

        List<String> containList = new ArrayList<>();

        if(checkBox11.isSelected())
            containList.add(checkBox11.getText().toUpperCase());
        if(checkBox21.isSelected())
            containList.add(checkBox21.getText().toUpperCase());
        if(checkBox31.isSelected())
            containList.add(checkBox31.getText().toUpperCase());
        if(checkBox41.isSelected())
            containList.add(checkBox41.getText().toUpperCase());

        log.info("Filtered the recipes: " + containList.toString());

        List<Recept> filteredRecipe = recipeService.searchContainedRecipe(containList, typeList, ingredientsList);

        for (Recept aFilteredRecipe : filteredRecipe) {
            recipeItems.add(aFilteredRecipe.getName());
        }

        recipeView.getItems().setAll(recipeItems);
        recipeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        checkBox11.setSelected(false);
        checkBox21.setSelected(false);
        checkBox31.setSelected(false);
        checkBox41.setSelected(false);
    }
}
