package service;

import model.Hozzavalo;
import model.Recept;

import java.util.List;

public interface RecipeService {
    void createIngredientsAddToRecipe(List<Hozzavalo> ingredientsList, Recept recipe);
    void createRecipe(Recept recipe);
    List<Recept> searchRecipe(List <String> ingredientsList);
    List<Recept> searchFilteredRecipe(List <String> ingredientsList);
    List<Recept> searchContainedRecipe(List <String> ingredientsList);
    List<Recept> getAllRecipe();
}
