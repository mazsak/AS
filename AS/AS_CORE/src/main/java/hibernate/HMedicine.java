package hibernate;

import models.Medicine;
import models.Treatment;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class HMedicine {
    private static EntityManager em = FactoryHibernate.getEm();
    
    public static List<Medicine> read(){
        em.getTransaction().begin();
        List<Medicine> result = em.createQuery("from Medicine", Medicine.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static boolean searchByName(String nameMedicine){
        em.getTransaction().begin();
        Medicine medicine= null;
        try {
            medicine = em.createQuery("from Medicine where name = :n", Medicine.class).setParameter("n", nameMedicine).getSingleResult();
            em.getTransaction().commit();
        }catch(NoResultException nre){
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public static Medicine searchByNameResult(String nameMedicine){
        em.getTransaction().begin();
        Medicine medicine= null;
        try {
            medicine = em.createQuery("from Medicine where name = :n", Medicine.class).setParameter("n", nameMedicine).getSingleResult();
            em.getTransaction().commit();
        }catch(NoResultException nre){
            em.getTransaction().commit();
        }
        return medicine;
    }

    public static void save(Medicine medicine){
        em.getTransaction().begin();
        em.persist(medicine);
        em.getTransaction().commit();
    }

    public static void update(Medicine medicine){
        em.getTransaction().begin();
        em.merge(medicine);
        em.getTransaction().commit();
    }

    public static void deleteConnection(List<Medicine> medicines, Treatment treatment) {
        for (Medicine medicine : medicines) {
            medicine.getTreatments().remove(treatment);
            em.merge(medicine);
        }
    }
}
