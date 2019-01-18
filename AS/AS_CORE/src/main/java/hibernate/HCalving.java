package hibernate;

import models.Calving;

import javax.persistence.EntityManager;
import java.util.List;

public class HCalving {
    private static EntityManager em = FactoryHibernate.getEm();
    
    public static List<Calving> read(){
        em.getTransaction().begin();
        List<Calving> result = em.createQuery("from Calving", Calving.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void save(Calving calving){
        em.getTransaction().begin();
        em.persist(calving);
        em.getTransaction().commit();
    }
}
