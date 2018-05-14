package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReceptTest {

    private Recept recipe;

    @Before
    public void setUp() throws Exception {
        recipe = new Recept();
    }


    @Test
    public void gettersTest(){
        List<Hozzavalo> ingredientsTest = new ArrayList<>();
        ingredientsTest.add(new Hozzavalo("liszt", IngredientsType.ALAPANYAG.name()));
        ingredientsTest.add(new Hozzavalo("csirkemell", IngredientsType.HÚS.name()));

        recipe.setId(Long.valueOf(1));
        assertEquals(1, recipe.getId());
        recipe.setName("Gyros");
        assertEquals("Gyros", recipe.getName());
        recipe.setDescription("Elkészítése 30 perc");
        assertEquals("Elkészítése 30 perc", recipe.getDescription());
        recipe.setMealType(MealType.LEVES.name());
        assertEquals(MealType.LEVES.name(), recipe.getMealType());
        recipe.setIngredients(ingredientsTest);
        assertEquals(ingredientsTest, recipe.getIngredients());
    }
}