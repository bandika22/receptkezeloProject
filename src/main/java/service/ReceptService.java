package service;

import model.Hozzavalo;
import model.Recept;

import java.util.List;

public interface ReceptService {
    void createHozzavaloAddToRecept(List<Hozzavalo> hvalo, Recept recept);
    void createRecept(Recept recept);
    List<Recept> searchRecept(List <String> hozzavaloList);
    List<Recept> searchFilteredRecept(List <String> hozzavaloList);
    List<Recept> getAllRecept();
}
