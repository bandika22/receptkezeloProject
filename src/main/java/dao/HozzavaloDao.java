package dao;

import model.Hozzavalo;
import model.Recept;

import java.util.List;

public interface HozzavaloDao extends GenericDAO<Hozzavalo, Long> {
    List<Hozzavalo> getAllHozzavalo();
}
