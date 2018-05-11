package impl;

import dao.ReceptDAO;
import model.Hozzavalo;
import model.Recept;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.stream.Collectors;

public class ReceptImpl implements ReceptDAO {

    private EntityManager entityManager;
    private List<Recept> solution;

    public ReceptImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Recept> getAllRecept() {
        TypedQuery<Recept> query = entityManager.createQuery(
                "SELECT r FROM Recept r", Recept.class);
        return query.getResultList();
    }

    @Override
    public void persist(Recept entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }
    @Override
    public List<Recept> searchRecept(List<String> hozzavaloList) {

        solution = new ArrayList<>();

        TypedQuery<Recept> query1 = entityManager.createQuery("SELECT r FROM Recept r", Recept.class);

        for (int i = 0; i < query1.getResultList().size(); i++) {
            if (query1.getResultList().get(i).getHozzavalok().stream().map(o -> o.getName()).allMatch(hozzavaloList::contains))
                solution.add(query1.getResultList().get(i));
        }
        return solution;
    }

    @Override
    public List<Recept> searchFilteredRecept(List<String> tipusList) {
        /*List<Recept> receptList = new ArrayList<>();
        List<Hozzavalo> hozzavaloList;
        List<String> hozzavaloList1;

        for (int i = 0; i < solution.size(); i++) {
            hozzavaloList = solution.get(i).getHozzavalok();
            for (int j = 0; j < tipusList.size(); j++){
                hozzavaloList1 = hozzavaloList.get()
            }

            *//*if (tipusList.stream().map(o -> o).allMatch(hozzavaloList::contains)) {
                receptList.add(solution.get(i));
            }*//*
         *//*   if (solution.get(i).getHozzavalok().stream().map(hozzavalo -> hozzavalo.getTipus()).contains(tipusList))) {
                receptList.add(solution.get(i));
            }*//*

        }
        return receptList;*/
        return null;


    }
}