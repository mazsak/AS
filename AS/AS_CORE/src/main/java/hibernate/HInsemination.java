package hibernate;

import models.Insemination;

import javax.persistence.EntityManager;
import java.util.List;

public class HInsemination {
    private static EntityManager em = FactoryHibernate.getEm();

    public static List<Insemination> read() {
        em.getTransaction().begin();
        List<Insemination> result = em.createQuery("from Insemination", Insemination.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void save(Insemination insemination) {
        em.getTransaction().begin();
        em.persist(insemination);
        em.getTransaction().commit();
    }

    //used by other HClasses
    public static void delete(List<Insemination> inseminations) {
        for (Insemination ins : inseminations) {
            em.remove(ins);
        }
    }

    public static void deleteOne(Insemination insemination) {
        em.getTransaction().begin();
        em.remove(insemination);
        em.getTransaction().commit();
    }
}
