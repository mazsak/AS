package hibernate;

import models.Bull;
import models.Insemination;

import javax.persistence.EntityManager;
import java.util.List;

public class HInsemination {
    public static List<Insemination> read(EntityManager em){
        em.getTransaction().begin();
        List<Insemination> result = em.createQuery("from Insemination", Insemination.class).getResultList();
        for(Insemination insemination : result){
            System.out.println("Zacieleie " + insemination.getInseminationDate().toString());
        }
        em.getTransaction().commit();
        return result;
    }

    public static void save(EntityManager em, String name){
        em.getTransaction().begin();
        Bull bull = new Bull();
        bull.setName(name);
        em.persist(bull);
        em.getTransaction().commit();
    }
}
