package hibernate;

import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.List;
import models.Cattle;

public class HTeam {
    private static EntityManager em = FactoryHibernate.getEm();
    
    public static List<Team> read(){
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team", Team.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static List<Team> getByCowshedName(String nameCowshed){
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team t where t.idCowshed.name = :n and type='EAT'", Team.class).setParameter("n", nameCowshed).getResultList();
        for(Team team : result){
            System.out.println("Grupa " + team.getName());
        }
        em.getTransaction().commit();
        return result;
    }

    public static void save(String type, String name, Cowshed idCowshed){
        em.getTransaction().begin();
        Team team = new Team();
        team.setType(type);
        team.setName(name);
        team.setIdCowshed(idCowshed);
        idCowshed.addTeam(team);
        em.persist(team);
        em.getTransaction().commit();
    }

    public static void update(Team team){
        em.getTransaction().begin();
        em.merge(team);
        em.getTransaction().commit();
    }
}
