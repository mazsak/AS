package hibernate;

import models.Cattle;
import models.Cowshed;
import models.Team;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
}
