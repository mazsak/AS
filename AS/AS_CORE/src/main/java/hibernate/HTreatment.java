package hibernate;

import models.Treatment;

import javax.persistence.EntityManager;
import java.util.List;

public class HTreatment {
    private static EntityManager em = FactoryHibernate.getEm();

    public static List<Treatment> read() {
        em.getTransaction().begin();
        List<Treatment> result = em.createQuery("from Treatment", Treatment.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void save(Treatment treatment) {
        em.getTransaction().begin();
        em.persist(treatment);
        em.getTransaction().commit();
    }

    public static void update(Treatment treatment) {
        em.getTransaction().begin();
        em.merge(treatment);
        em.getTransaction().commit();
    }

    public static void delete(Treatment treatment) {
        em.getTransaction().begin();
        HMedicine.deleteConnection(treatment.getMedsUsedList(), treatment);
        em.merge(treatment);
        em.remove(treatment);
        em.getTransaction().commit();
    }

    public static void deleteAll(List<Treatment> treatments) {
        for (Treatment treatment : treatments) {
            HMedicine.deleteConnection(treatment.getMedsUsedList(), treatment);
            treatment.getMedsUsedList().clear();
            em.merge(treatment);
            em.remove(treatment);
        }
    }
}
