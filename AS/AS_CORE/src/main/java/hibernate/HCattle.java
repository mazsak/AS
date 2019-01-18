package hibernate;

import models.Cattle;

import javax.persistence.EntityManager;
import java.util.List;

public class HCattle {
    private static EntityManager em = FactoryHibernate.getEm();
    
    public static List<Cattle> read(){
        em.getTransaction().begin();
        List<Cattle> result = em.createQuery("from Cattle", Cattle.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static Cattle findByEarring(String earring){
        em.getTransaction().begin();
        Cattle result = em.createQuery("from Cattle where earring = :n", Cattle.class).setParameter("n", earring).getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public static void save(Cattle cattle){
        em.getTransaction().begin();
        em.persist(cattle);
        em.getTransaction().commit();
    }

    public static void update(Cattle cattle){
        em.getTransaction().begin();
        em.merge(cattle);
        em.getTransaction().commit();
    }
    
    public static void delete(Cattle cattle){
        em.getTransaction().begin();
        
        
        
        em.remove(cattle);
        em.getTransaction().commit();
    }
    
    public static void removeFromTeam(Cattle cattle){
        em.getTransaction().begin();
        
    }
}
