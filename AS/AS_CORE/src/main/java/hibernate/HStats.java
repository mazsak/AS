package hibernate;

import models.StatsDaily;
import models.StatsMonthly;

import javax.persistence.EntityManager;
import java.util.List;

public class HStats {

    private static EntityManager em = FactoryHibernate.getEm();

    public static List<StatsDaily> readDaily() {
        em.getTransaction().begin();
        List<StatsDaily> result = em.createQuery("from Treatment", StatsDaily.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void saveDaily(StatsDaily statsDaily) {
        em.getTransaction().begin();
        em.persist(statsDaily);
        em.getTransaction().commit();
    }

    public static void updateDaily(StatsDaily statsDaily) {
        em.getTransaction().begin();
        em.merge(statsDaily);
        em.getTransaction().commit();
    }

    public static void deleteDaily(StatsDaily statsDaily) {
        em.getTransaction().begin();
        em.remove(statsDaily);
        em.getTransaction().commit();
    }

    public static List<StatsMonthly> readMonthly() {
        em.getTransaction().begin();
        List<StatsMonthly> result = em.createQuery("from Treatment", StatsMonthly.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static void saveMonthly(StatsMonthly statsMonthly) {
        em.getTransaction().begin();
        em.persist(statsMonthly);
        em.getTransaction().commit();
    }

    public static void updateMonthly(StatsMonthly statsMonthly) {
        em.getTransaction().begin();
        em.merge(statsMonthly);
        em.getTransaction().commit();
    }

    public static void deleteMonthly(StatsMonthly statsMonthly) {
        em.getTransaction().begin();
        em.remove(statsMonthly);
        em.getTransaction().commit();
    }

    public static void deleteDaily2(List<StatsDaily> statsDaily) {
        for (StatsDaily stsd : statsDaily) {
            em.remove(stsd);
        }
    }

    public static void deleteMonthly2(List<StatsMonthly> statsMonthly) {
        for (StatsMonthly stsm : statsMonthly) {
            em.remove(stsm);
        }
    }
}
