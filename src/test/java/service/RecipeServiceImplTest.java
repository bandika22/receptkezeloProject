package service;

import dao.RecipeDAO;
import dao.impl.RecipeImpl;
import model.MealType;
import model.Hozzavalo;
import model.IngredientsType;
import org.junit.Before;
import utility.DBManager;
import model.Recept;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class RecipeServiceImplTest {


    private List<String> searchItem;
    private List<String> ingredients;
    private List<String> mealType;
    private List<Recept> solutionRecipe;

    private RecipeDAO dao= new RecipeImpl(DBManager.getInstance());
    private RecipeService recipeService= new RecipeServiceImpl(dao);


    private Recept createTestRecipe() {

        List<Hozzavalo> ingredients = new ArrayList<>();
        ingredients.add(new Hozzavalo("Liszt1", IngredientsType.ALAPANYAG.name()));
        ingredients.add(new Hozzavalo("Csirkemell1", IngredientsType.HÚS.name()));
        ingredients.add(new Hozzavalo("Bors1", IngredientsType.FŰSZER.name()));
        Recept recipe = new Recept("Pizza", "Nagyon finom.", MealType.ELŐÉTEL.name());
        recipeService.createIngredientsAddToRecipe(ingredients, recipe);
        recipeService.createRecipe(recipe);

        return recipe;
    }

    private Recept recipe = createTestRecipe();

    @Test
    public void searchRecipe() {

        searchItem = new ArrayList<>();
        searchItem.add("Liszt1");
        searchItem.add("Csirkemell1");
        searchItem.add("Bors1");
        searchItem.add("Bors2");
        solutionRecipe = recipeService.searchRecipe(searchItem);

        if (solutionRecipe.isEmpty())
            assertEquals(recipe.getId(), solutionRecipe.get(0).getId());
        else {
            for (int i = 0; i < solutionRecipe.size(); i++) {
                if (recipe.getId() == solutionRecipe.get(i).getId()) {
                    assertEquals(recipe.getId(), solutionRecipe.get(i).getId());
                    assertEquals(recipe.getName(), solutionRecipe.get(i).getName());
                    assertEquals(recipe.getDescription(), solutionRecipe.get(i).getDescription());
                    assertEquals(recipe.getMealType(), solutionRecipe.get(i).getMealType());
                }
            }
        }
    }

   @Test
    public void searchFilteredRecipe() {
       ingredients = new ArrayList<>();
       List<Recept> filteredRecipe;
       mealType = new ArrayList<>();
       mealType.add(MealType.ELŐÉTEL.name());

       for (int i = 0; i < recipe.getIngredients().size(); i++)
           ingredients.add(recipe.getIngredients().get(i).getName());


       filteredRecipe = recipeService.searchFilteredRecipe(mealType, ingredients);

       if (filteredRecipe.isEmpty())
           assertEquals(recipe.getMealType(), filteredRecipe.get(0).getMealType());
       else {
           for (int i = 0; i < filteredRecipe.size(); i++) {
               if (recipe.getId() == filteredRecipe.get(i).getId()) {
                   assertEquals(recipe.getMealType(), filteredRecipe.get(i).getMealType());
               }
           }
       }

    }

    @Test
    public void searchContainedRecipe() {
        ingredients = new ArrayList<>();
        mealType = new ArrayList<>();
        mealType.add(recipe.getMealType());

        List<String> ingredientsTypeList = new ArrayList<>();
        ingredientsTypeList.add(IngredientsType.HÚS.name());

        for (int i = 0; i < recipe.getIngredients().size(); i++)
            ingredients.add(recipe.getIngredients().get(i).getName());

        List<Recept> filteredRecipe;

        filteredRecipe = recipeService.searchContainedRecipe(ingredientsTypeList,mealType, ingredients);

        if (filteredRecipe.isEmpty())
            assertEquals(recipe.getMealType(), filteredRecipe.get(0).getMealType());
        else {
            for (int i = 0; i < filteredRecipe.size(); i++) {
                if (recipe.getId() == filteredRecipe.get(i).getId()) {
                    assertEquals(recipe.getId(), filteredRecipe.get(i).getId());
                }
            }
        }

    }

}