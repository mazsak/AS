package hibernate;

import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class HCowshed {
    public static List<Cowshed> read(EntityManager em){
        em.getTransaction().begin();
        List<Cowshed> result = em.createQuery("from Cowshed", Cowshed.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static Cowshed findByName(EntityManager em, String name){
        em.getTransaction().begin();
        Cowshed result = em.createQuery("from Cowshed where name = :n", Cowshed.class).setParameter("n", name).getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, String address, String info, String name){
        em.getTransaction().begin();
        Cowshed cowshed = new Cowshed();
        cowshed.setAddress(address);
        cowshed.setInfo(info);
        cowshed.setName(name);
        cowshed.setTeamList(new ArrayList<Team>());
        em.persist(cowshed);
        em.getTransaction().commit();
    }
}
