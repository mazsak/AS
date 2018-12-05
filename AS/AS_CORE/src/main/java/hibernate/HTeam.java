package hibernate;

import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.List;

public class HTeam {
    public static void read(EntityManager em){
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team", Team.class).getResultList();
        for(Team team : result){
            System.out.println("Grupa " + team.getName());
        }
        em.getTransaction().commit();
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
