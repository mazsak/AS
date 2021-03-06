package hibernate;

import models.*;

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
        //team_cattle
        List<Team> teams = HTeam.getByCattleIn(cattle);
        for (int i = 0; i < teams.size(); i++) {
            Team team1 = em.find(Team.class, teams.get(i).getIdGroup());
            team1.removeCattle(cattle);
        }
        //insemination
        List<Insemination> inseminations = cattle.getInseminationList();
        HInsemination.delete(inseminations);
        cattle.getInseminationList().removeAll(inseminations);
        
        //TODO
        //calving(is parent) 
        List<Calving> calvings = cattle.getCalvingList();
        HCalving.delete(calvings);
        cattle.getCalvingList().removeAll(calvings);
        //TODO
        //calving(is child)
        List<Calving> calvings1 = cattle.getCalvingList1();
        HCalving.delete(calvings1);
        cattle.getCalvingList1().removeAll(calvings1);
        //treatment
        List<Treatment> treatments = cattle.getTreatmentList();
        HTreatment.deleteAll(treatments);
        cattle.getTreatmentList().removeAll(treatments);
        
        //stats monthly
        List<StatsMonthly> statsMonthlies = cattle.getStatsMonthlyList();
        HStats.deleteMonthly2(statsMonthlies);
        cattle.getStatsMonthlyList().removeAll(statsMonthlies);
        
        //stats daily
        List<StatsDaily> statsDailies = cattle.getStatsDailyList();
        HStats.deleteDaily2(statsDailies);
        cattle.getStatsDailyList().removeAll(statsDailies);
        
        em.merge(cattle);
        em.remove(cattle);
        em.getTransaction().commit();
    }
}
