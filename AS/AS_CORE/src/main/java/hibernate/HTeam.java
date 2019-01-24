package hibernate;

import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class HTeam {
    private static EntityManager em = FactoryHibernate.getEm();
    
    public static List<Team> read(){
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team", Team.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static List<Team> getByCowshedName(String nameCowshed, String type) {
        em.getTransaction().begin();
        List<Team> result = em.createQuery("from Team t where t.idCowshed.name = :n and type= :t", Team.class).setParameter("n", nameCowshed).setParameter("t", type).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static Team getByName(String name) {
        em.getTransaction().begin();
        Team result = em.createQuery("from Team t where t.name = :n", Team.class).setParameter("n", name).getSingleResult();
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

    public static void delete(Team team) {
        int i = 0;
        while (i < team.getCattleList().size()) {
            HCattle.delete(team.getCattleList().get(i));
        }

        em.getTransaction().begin();
        em.remove(em.contains(team) ? team : em.merge(team));
        em.getTransaction().commit();
    }

    public static void update(Team team){
        em.getTransaction().begin();
        em.merge(team);
        em.getTransaction().commit();
    }

    //used by other HClasses
    public static List<Team> getByCattleIn(Cattle cattle){
        int cattle1  = cattle.getIdCattle();
        String hql = "select distinct a from Team a " +
                "join a.cattleList t " +
                "where t.idCattle = :cattle1";
        Query query = em.createQuery(hql);
        query.setParameter("cattle1", cattle1);
        List<Team> teams = query.getResultList();
        return teams;
    }

    //used by other HClasses
    public static List<Team> getByCattleInEm(Cattle cattle){
        em.getTransaction().begin();
        int cattle1  = cattle.getIdCattle();
        String hql = "select distinct a from Team a " +
                "join a.cattleList t " +
                "where t.idCattle = :cattle1";
        Query query = em.createQuery(hql);
        query.setParameter("cattle1", cattle1);
        List<Team> teams = query.getResultList();
        System.out.println(teams);
        em.getTransaction().commit();
        return teams;
    }
}
