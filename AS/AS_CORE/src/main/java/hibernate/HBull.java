package hibernate;

import models.Bull;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HBull {
    public static List<Bull> read(EntityManager em){
        em.getTransaction().begin();
        List<Bull> result = em.createQuery("from Bull", Bull.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static boolean searhByName(EntityManager em, String nameBull){
        em.getTransaction().begin();
        Bull bull= null;
        try {
            bull = em.createQuery("from Bull where name = :n", Bull.class).setParameter("n", nameBull).getSingleResult();
            em.getTransaction().commit();
        }catch(NoResultException nre){
            em.getTransaction().commit();
            return true;
        }
        return false;
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
