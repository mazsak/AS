package hibernate;

import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HCattle {
    public static List<Cattle> read(EntityManager em){
        em.getTransaction().begin();
        List<Cattle> result = em.createQuery("from Cattle", Cattle.class).getResultList();
        for(Cattle cattle : result){
            System.out.println("Cattle " + cattle.getName());
        }
        em.getTransaction().commit();
        return result;
    }

    public static Cattle findByEarring(EntityManager em, String earring){
        em.getTransaction().begin();
        Cattle result = em.createQuery("from Cattle where earring = :n", Cattle.class).setParameter("n", earring).getSingleResult();
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, String name, String earring, String sex, Integer cowshedNumber,
                            Date birthDate, Date joinDate, Date leaveDate, Date leaveReason, String notes){
        em.getTransaction().begin();
        Cattle cattle = new Cattle();
        cattle.setName(name);
        cattle.setEarring(earring);
        cattle.setSex(sex);
        cattle.setBirthDate(birthDate);
        cattle.setJoinDate(joinDate);
        cattle.setLeaveDate(leaveDate);
        cattle.setLeaveDate(leaveReason);
        cattle.setCowshedNumber(cowshedNumber);
        cattle.setNotes(notes);
        //cowshed.setTeamList(new ArrayList<Team>());
        em.persist(cattle);
        em.getTransaction().commit();
    }
}
