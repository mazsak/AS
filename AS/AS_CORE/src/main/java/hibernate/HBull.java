package hibernate;

import models.Bull;

import javax.persistence.EntityManager;
import java.util.List;

public class HBull {
    public static void read(EntityManager em){
        em.getTransaction().begin();
        List<Bull> result = em.createQuery("from Bull", Bull.class).getResultList();
        for(Bull bull : result){
            System.out.println("Byk " + bull.getName());
        }
        em.getTransaction().commit();
    }

    public static void save(EntityManager em, String name){
        em.getTransaction().begin();
        Bull bull = new Bull();
        bull.setName(name);
        em.persist(bull);
        em.getTransaction().commit();
    }
}
