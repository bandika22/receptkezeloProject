package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HozzavaloTest {


    private Hozzavalo ingredients;

    @Before
    public void setUp() throws Exception {
        ingredients = new Hozzavalo();
    }

    @Test
    public void gettersTest(){

        List<Recept> recipeTest = new ArrayList<>();
        recipeTest.add(new Recept("Pizza", "Finom", MealType.ELŐÉTEL.name()));
        recipeTest.add(new Recept("Babgulyas", "A kedvencem", MealType.LEVES.name()));

        ingredients.setId(Long.valueOf(1));
        assertEquals(1, ingredients.getId());
        ingredients.setName("Liszt");
        assertEquals("Liszt", ingredients.getName());
        ingredients.setTipus(IngredientsType.HÚS.name());
        assertEquals(IngredientsType.HÚS.name(), ingredients.getTipus());
        ingredients.setRecipes(recipeTest);
        assertEquals(recipeTest, ingredients.getRecipes());
    }
}