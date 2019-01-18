package hibernate;

import java.util.Iterator;
import models.Cattle;

import javax.persistence.EntityManager;
import java.util.List;
import models.Calving;
import models.Insemination;
import models.Team;

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
        em.merge(cattle);
        //calving
        List<Calving> calvings = cattle.getCalvingList();
        HCalving.delete(calvings);
        cattle.getCalvingList().removeAll(calvings);
        em.merge(cattle);
        //treatment
        
        //stats monthly
        
        //stats daily
        
        
        em.remove(cattle);
        em.getTransaction().commit();
    }
    
    public static void removeFromTeam(Cattle cattle){
        em.getTransaction().begin();
        
    }
}
