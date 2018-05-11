package dao;

import model.Hozzavalo;
import model.Recept;

import java.util.List;
import java.util.Map;

public interface ReceptDAO extends GenericDAO<Recept,Long> {
    List<Recept> getAllRecept();
    List<Recept> searchRecept(List<String> hozzavaloList);
    List<Recept> searchFilteredRecept(List<String> hozzavaloList);
}
