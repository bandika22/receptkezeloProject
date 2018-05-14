package service;

import dao.RecipeDAO;
import model.Hozzavalo;
import model.Recept;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {

    private RecipeDAO dao;

    public RecipeServiceImpl(RecipeDAO dao) {
        this.dao = dao;
    }

    /**
     * Létrehozza a hozzávalók listáját, melyet hozzáad a hozzátartozó recepthez.
     * @param ingredientsList A hozzávalók listája.
     * @param recipe A recept, melyhez tartoznak a hozzávalók.
     */
    @Override
    public void createIngredientsAddToRecipe(List<Hozzavalo> ingredientsList, Recept recipe) {
        for (int i = 0; i < ingredientsList.size(); i++) {
            recipe.addIngredients(ingredientsList.get(i));
        }
    }

    /**
     * Létrehozza a receptet.
     * @param recipe A recept, melyet létrehoz.
     */
    @Override
    public void createRecipe(Recept recipe) {
        dao.persist(recipe);
    }

    /**
     * Visszaadja azoknak a recepteknek a listáját,
     * amelyek hozzávalói megegyeznek a paraméterben megadott hozzávalók nevével.
     * @param ingredientsList Az a hozzávalólista, amelyet meg szeretnénk keresni a receptek hozzávalói közül.
     * @return Recept lista.
     */
    @Override
    public List<Recept> searchRecipe(List<String> ingredientsList) {
        return dao.searchRecipe(ingredientsList);
    }

    /**
     * Visszaadja azoknak a recepteknek a listáját, mely a kereső függvény eredményeit tartalmazva
     * szűri még a listát a recept típusának megadásával.
     * @param mealTypeList Olyan lista, mellyel szűrni szeretnénk az eredményül kapott receptünk listáját.
     * @return Recept lista.
     */
    @Override
    public List<Recept> searchFilteredRecipe(List<String> mealTypeList) {
        return dao.searchFilteredRecipe(mealTypeList);
    }

    /**
     * Visszaadja azoknak a recepteknek a listáját, mely a kereső függvény eredményeit tartalmazva
     * szűri még a listát a hozzávaló típusának megadásával, amelyet a recept mindenképp tartalmazzon.
     * @param ingredientsTypeList Olyan lista, mellyel szűrni szeretnénk az eredményül kapott receptünk listáját.
     * @return Recept lista.
     */
    @Override
    public List<Recept> searchContainedRecipe(List<String> ingredientsTypeList) {
        return dao.searchContainedRecipe(ingredientsTypeList);
    }

    /**
     * Visszaad egy listát, mely az adatbázisban szereplő összes receptet tartalmazza.
     * @return Egy lista, mely az összes receptet tartalmazza.
     */
    @Override
    public List<Recept> getAllRecipe() {
        return dao.getAllRecipe();
    }
}
