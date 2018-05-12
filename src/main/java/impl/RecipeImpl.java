package impl;

import dao.RecipeDAO;
import model.Hozzavalo;
import model.Recept;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

public class RecipeImpl implements RecipeDAO {

    private EntityManager entityManager;
    private List<Recept> solution;
    private List<Recept> filteredRecipeList;

    public RecipeImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Recept> getAllRecept() {
        TypedQuery<Recept> query = entityManager.createQuery(
                "SELECT r FROM Recipe r", Recept.class);
        return query.getResultList();
    }

    @Override
    public void persist(Recept entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }
    @Override
    public List<Recept> searchRecept(List<String> ingredientsList) {

        solution = new ArrayList<>();

        TypedQuery<Recept> query1 = entityManager.createQuery("SELECT r FROM Recipe r", Recept.class);

        for (int i = 0; i < query1.getResultList().size(); i++) {
            if (query1.getResultList().get(i).getHozzavalok().stream().map(Hozzavalo::getName).allMatch(ingredientsList::contains))
                solution.add(query1.getResultList().get(i));
        }
        filteredRecipeList = solution;
        return solution;
    }


    @Override
    public List<Recept> searchFilteredRecipe(List<String> mealTypeList) {
        filteredRecipeList = new ArrayList<>();
        for (Recept aSolution : solution) {
            if (mealTypeList.stream().anyMatch(aSolution.getMealType()::contains)) {
                filteredRecipeList.add(aSolution);
            }
        }
        return filteredRecipeList;
    }

    @Override
    public List<Recept> searchContainedRecipe(List<String> ingredientsTypeList) {

        List<Recept> containedRecipeList = new ArrayList<>();
        List<String> typeList = new ArrayList<>();
        for (int i = 0; i < filteredRecipeList.size(); i++) {
            for (int j = 0; j < filteredRecipeList.get(i).getHozzavalok().size(); j++){
                typeList.add(filteredRecipeList.get(i).getHozzavalok().get(j).getTipus());
            }
            System.out.println(typeList.toString());

            if (ingredientsTypeList.stream().allMatch(typeList::contains)){
                containedRecipeList.add(filteredRecipeList.get(i));
            }
        }
        System.out.println(ingredientsTypeList.toString());
        return containedRecipeList;
    }
}