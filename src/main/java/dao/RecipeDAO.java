package dao;

import model.Hozzavalo;
import model.Recept;

import java.util.List;
import java.util.Map;

public interface RecipeDAO extends GenericDAO<Recept,Long> {
    List<Recept> getAllRecipe();
    List<Recept> searchRecipe(List<String> hozzavaloList);
    List<Recept> searchFilteredRecipe(List<String> hozzavaloList);
    List<Recept> searchContainedRecipe(List<String> hozzavaloList);
}
