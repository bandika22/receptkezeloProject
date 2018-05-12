package impl;

import dao.HozzavaloDao;
import model.Hozzavalo;
import model.Recept;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class IngredientsImpl implements HozzavaloDao {

    private EntityManager entityManager;

    public IngredientsImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<Hozzavalo> getAllHozzavalo() {
        TypedQuery<Hozzavalo> query = entityManager.createQuery(
                "SELECT ing FROM Ingredients ing", Hozzavalo.class);
        return query.getResultList();
    }

    @Override
    public void persist(Hozzavalo entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();

    }


}
