package dao.impl;

import dao.RecipeDAO;
import model.Hozzavalo;
import model.Recept;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

public class RecipeImpl implements RecipeDAO {

    private EntityManager entityManager;
    private List<Recept> filteredRecipeList;

    public RecipeImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Visszaad egy listát, mely az adatbázisban szereplő összes receptet tartalmazza.
     * @return Egy lista, mely az összes receptet tartalmazza.
     */
    @Override
    public List<Recept> getAllRecipe() {
        TypedQuery<Recept> query = entityManager.createQuery(
                "SELECT r FROM Recept r", Recept.class);
        return query.getResultList();
    }

    /**
     * Feltölti az adatbázisba a receptet.
     * @param entity Az a recept, amelyet feltöltünk az adatbázisba.
     */
    @Override
    public void persist(Recept entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }

    /**
     * Visszaadja azoknak a recepteknek a listáját,
     * amelyek hozzávalói megegyeznek a paraméterben megadott hozzávalók nevével.
     * @param ingredientsList Az a hozzávalólista, amelyet meg szeretnénk keresni a receptek hozzávalói közül.
     * @return Recept lista.
     */
    @Override
    public List<Recept> searchRecipe(List<String> ingredientsList) {

        List<Recept> solution = new ArrayList<>();

        TypedQuery<Recept> query1 = entityManager.createQuery("SELECT r FROM Recept r", Recept.class);

        for (int i = 0; i < query1.getResultList().size(); i++) {
            if (query1.getResultList().get(i).getIngredients().stream().map(Hozzavalo::getName).allMatch(ingredientsList::contains))
                solution.add(query1.getResultList().get(i));
        }
        filteredRecipeList = solution;

        return solution;
    }


    /**
     * Visszaadja azoknak a recepteknek a listáját, mely a kereső függvény eredményeit tartalmazva
     * szűri még a listát a recept típusának megadásával.
     * @param mealTypeList Olyan lista, mellyel szűrni szeretnénk az eredményül kapott receptünk listáját.
     * @param ingredientsList Megadjuk a hozzávalólistát, amelyre a keresés feltétele teljesült, hogy az eredményben végezzük el a szűrést.
     * @return Recept lista.
     */
    @Override
    public List<Recept> searchFilteredRecipe(List<String> mealTypeList, List<String> ingredientsList) {
        List<Recept> solution = searchRecipe(ingredientsList);
        filteredRecipeList = new ArrayList<>();
        for (Recept aSolution : solution) {
            if (mealTypeList.stream().anyMatch(aSolution.getMealType()::contains)) {
                filteredRecipeList.add(aSolution);
            }
        }
        return filteredRecipeList;
    }

    /**
     * Visszaadja azoknak a recepteknek a listáját, mely a kereső függvény eredményeit tartalmazva
     * szűri még a listát a hozzávaló típusának megadásával, amelyet a recept mindenképp tartalmazzon.
     * @param ingredientsTypeList Olyan lista, mellyel szűrni szeretnénk az eredményül kapott receptünk listáját.
     * @param mealTypeList Ha szűrtünk ételtípusra, akkor az erre leszűrt receptlistában fogja elvégezni a további szűrést.
     * @param ingredientsList Ha ételtípusra nem szűrtünk, akkor a szűrést a hozzávaló keresés eredménye alapján fogja elvégezni.
     * @return Recept lista.
     */
    @Override
    public List<Recept> searchContainedRecipe(List<String> ingredientsTypeList, List<String> mealTypeList, List<String> ingredientsList) {

        List<Recept> containedRecipeList = new ArrayList<>();
        List<String> typeList = new ArrayList<>();

        if (!mealTypeList.isEmpty())
            filteredRecipeList = searchFilteredRecipe(mealTypeList, ingredientsList);
        else
            filteredRecipeList = searchRecipe(ingredientsList);


        for (int i = 0; i < filteredRecipeList.size(); i++) {
            for (int j = 0; j < filteredRecipeList.get(i).getIngredients().size(); j++){
                typeList.add(filteredRecipeList.get(i).getIngredients().get(j).getTipus());
            }

            if (ingredientsTypeList.stream().allMatch(typeList::contains)){
                containedRecipeList.add(filteredRecipeList.get(i));
            }
            typeList = new ArrayList<>();
        }
        return containedRecipeList;
    }
}