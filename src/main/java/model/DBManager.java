package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBManager implements AutoCloseable{

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("receptKezeloDB");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getInstance() {
        return entityManager;
    }

    @Override
    public void close() throws Exception {
        if (entityManagerFactory != null){
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
