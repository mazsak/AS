package hibernate;

import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class HCowshed {
    private static EntityManager em = FactoryHibernate.getEm();
    
    public static List<Cowshed> read(){
        em.getTransaction().begin();
        List<Cowshed> result = em.createQuery("from Cowshed", Cowshed.class).getResultList();
        em.getTransaction().commit();
        return result;
    }

    public static Cowshed findByName(String name){
        em.getTransaction().begin();
        Cowshed result = em.createQuery("from Cowshed where name = :n", Cowshed.class).setParameter("n", name).getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public static void save(String address, String info, String name){
        em.getTransaction().begin();
        Cowshed cowshed = new Cowshed();
        cowshed.setAddress(address);
        cowshed.setInfo(info);
        cowshed.setName(name);
        cowshed.setTeamList(new ArrayList<Team>());
        em.persist(cowshed);
        em.getTransaction().commit();
    }

    public static void delete(Cowshed cowshed) {
        for (Team team : cowshed.getTeamList()) {
            if (team.getType().equals("EAT")) {
                List<Cattle> cattles = team.getCattleList();
                int i = 0;
                while (i != cattles.size()) {
                    HCattle.delete(cattles.get(i));
                }
            }
            HTeam.delete(team);
        }

        em.getTransaction().begin();
        em.remove(cowshed);
        em.getTransaction().commit();
    }
}
