package service;

import dao.RecipeDAO;
import dao.impl.RecipeImpl;
import model.MealType;
import model.Hozzavalo;
import model.IngredientsType;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import utility.DBManager;
import model.Recept;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {



    @Mock
    RecipeDAO dao = new RecipeImpl(DBManager.getInstance());


    private Recept createTestRecipeList() {

        Recept recipe = new Recept();
        List<Hozzavalo> ingredients = new ArrayList<>();
        ingredients.add(new Hozzavalo("Liszt", IngredientsType.ALAPANYAG.name()));
        ingredients.add(new Hozzavalo("Csirkemell", IngredientsType.HÚS.name()));
        ingredients.add(new Hozzavalo("Bors", IngredientsType.FŰSZER.name()));
        recipe.setName("Pizza");
        recipe.setDescription("Nagyon finom a pizza");
        recipe.setMealType(MealType.LEVES.name());
        recipe.setIngredients(ingredients);

        return recipe;

    }

    @Test
    public void searchRecipe() {
        List<String> searchItem = new ArrayList<>();
        List<Recept> solutionRecipe = new ArrayList<>();
        searchItem.add("Liszt");
        searchItem.add("Csirkemell");
        searchItem.add("Bors");
        dao.persist(createTestRecipeList());
        solutionRecipe.add(createTestRecipeList());
        Mockito.when(dao.searchRecipe(searchItem)).thenReturn(solutionRecipe);
        List<Recept> recipe = dao.searchRecipe(searchItem);
        assertEquals("Pizza", recipe.get(0).getName());
        assertEquals("Nagyon finom a pizza", recipe.get(0).getDescription());
        assertEquals("LEVES", recipe.get(0).getMealType());
        assertEquals("Liszt", recipe.get(0).getIngredients().get(0).getName());
        assertEquals("Csirkemell", recipe.get(0).getIngredients().get(1).getName());
        assertEquals("Bors", recipe.get(0).getIngredients().get(2).getName());
        Mockito.verify(dao).searchRecipe(searchItem);
    }

    @Test
    public void searchFilteredRecipe() {
        List<String> filteredRecipe = new ArrayList<>();
        List<Recept> solutionRecipe = new ArrayList<>();
        filteredRecipe.add("LEVES");
        dao.persist(createTestRecipeList());
        solutionRecipe.add(createTestRecipeList());
        Mockito.when(dao.searchFilteredRecipe(filteredRecipe)).thenReturn(solutionRecipe);
        List<Recept> recipe = dao.searchFilteredRecipe(filteredRecipe);
        assertEquals("Pizza", recipe.get(0).getName());
        assertEquals("Nagyon finom a pizza", recipe.get(0).getDescription());
        assertEquals("LEVES", recipe.get(0).getMealType());
        Mockito.verify(dao).searchFilteredRecipe(filteredRecipe);
    }

    @Test
    public void searchContainedRecipe() {
        List<String> containedRecipe = new ArrayList<>();
        List<Recept> solutionRecipe = new ArrayList<>();
        containedRecipe.add("HÚS");
        dao.persist(createTestRecipeList());
        solutionRecipe.add(createTestRecipeList());
        Mockito.when(dao.searchFilteredRecipe(containedRecipe)).thenReturn(solutionRecipe);
        List<Recept> recipe = dao.searchFilteredRecipe(containedRecipe);
        assertEquals("Pizza", recipe.get(0).getName());
        assertEquals("Nagyon finom a pizza", recipe.get(0).getDescription());
        assertEquals("LEVES", recipe.get(0).getMealType());
        assertEquals("HÚS", recipe.get(0).getIngredients().get(1).getTipus());
        Mockito.verify(dao).searchFilteredRecipe(containedRecipe);
    }

}