package hibernate;

import models.Calving;

import javax.persistence.EntityManager;
import java.util.List;

public class HCalving {
    public static List<Calving> read(EntityManager em){
        em.getTransaction().begin();
        List<Calving> result = em.createQuery("from Calving", Calving.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, Calving calving){
        em.getTransaction().begin();
        em.persist(calving);
        em.getTransaction().commit();
    }
}
