package hibernate;

import models.Bull;

import javax.persistence.EntityManager;
import java.util.List;

public class HBull {
    public static List<Bull> read(EntityManager em){
        em.getTransaction().begin();
        List<Bull> result = em.createQuery("from Bull", Bull.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, Bull bull){
        em.getTransaction().begin();
        em.persist(bull);
        em.getTransaction().commit();
    }

    public static void update(EntityManager em, Bull bull){
        em.getTransaction().begin();
        em.merge(bull);
        em.getTransaction().commit();
    }
}
