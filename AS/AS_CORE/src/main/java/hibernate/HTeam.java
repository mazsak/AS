package hibernate;

import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.List;

public class HTeam {
    public static List<Team> read(EntityManager em){
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team", Team.class).getResultList();
        for(Team team : result){
            System.out.println("Grupa " + team.getName());
        }
        em.getTransaction().commit();
        return result;
    }

    public static List<Team> getByCowshedName(EntityManager em, String nameCowshed){
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team t where t.idCowshed.name = :n", Team.class).setParameter("n", nameCowshed).getResultList();
        for(Team team : result){
            System.out.println("Grupa " + team.getName());
        }
        em.getTransaction().commit();
        return result;
    }

    public static List<Cattle> getCattlesFromTeam(EntityManager em, String nameGroup){
        em.getTransaction().begin();
        List<Cattle> result = em.createQuery("from Cattle c where c.teamList.name = :n", Cattle.class).setParameter("n", nameGroup).getResultList();
        for(Cattle cattle : result){
            System.out.println("Cattle " + cattle.getName());
        }
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, String type, String name, Cowshed idCowshed){
        em.getTransaction().begin();
        Team team = new Team();
        team.setType(type);
        team.setName(name);
        team.setIdCowshed(idCowshed);
        idCowshed.addTeam(team);
        em.persist(team);
        em.getTransaction().commit();
    }
}
