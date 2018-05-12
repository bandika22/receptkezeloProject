package service;

import model.Hozzavalo;
import model.Recept;

import java.util.List;

public interface ReceptService {
    void createIngredientsAddToRecipe(List<Hozzavalo> hvalo, Recept recept);
    void createRecipe(Recept recept);
    List<Recept> searchRecipe(List <String> hozzavaloList);
    List<Recept> searchFilteredRecipe(List <String> hozzavaloList);
    List<Recept> searchContainedRecipe(List <String> hozzavaloList);
    List<Recept> getAllRecept();
}
