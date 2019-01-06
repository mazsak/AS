package hibernate;

import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HCattle {
    public static List<Cattle> read(EntityManager em){
        em.getTransaction().begin();
        List<Cattle> result = em.createQuery("from Cattle", Cattle.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static Cattle findByEarring(EntityManager em, String earring){
        em.getTransaction().begin();
        Cattle result = em.createQuery("from Cattle where earring = :n", Cattle.class).setParameter("n", earring).getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, Cattle cattle){
        em.getTransaction().begin();
        em.persist(cattle);
        em.getTransaction().commit();
    }

    public static void update(EntityManager em, Cattle cattle){
        em.getTransaction().begin();
        em.merge(cattle);
        em.getTransaction().commit();
    }
}
