package dao.impl;

import dao.IngredientsDao;
import model.Hozzavalo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class IngredientsImpl implements IngredientsDao {

    private EntityManager entityManager;

    public IngredientsImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Visszaa egy listát, amely az összes hozzávalót tartalmazza az adatbázisban.
     * @return Egy lista, amely hozzávalókat tartalmaz.
     */
    @Override
    public List<Hozzavalo> getAllHozzavalo() {
        TypedQuery<Hozzavalo> query = entityManager.createQuery(
                "SELECT r FROM Recept r", Hozzavalo.class);
        return query.getResultList();
    }

    /**
     * Feltölti a hozzávalót az adatbázisba.
     * @param entity Az a hozzávaló, amelyet feltölt az adatbázisba.
     */
    @Override
    public void persist(Hozzavalo entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

}
