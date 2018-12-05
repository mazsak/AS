import hibernate.FactoryHibernate;
import hibernate.HBull;
import hibernate.HCowshed;
import hibernate.HTeam;
import models.Cowshed;
import root.MainFrame;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    //private static EntityManager em;

    public static void main(String[] args){
        FactoryHibernate fh = new FactoryHibernate();
        EntityManager em = FactoryHibernate.getEm();

        //obory
        HCowshed.save(em,"Polska", "OK", "Dobra obora");
        HCowshed.read(em);

        HBull.save(em, "Jozef");
        HBull.read(em);

        HTeam.save(em, "Cieleta", "Bydlaki", new Cowshed(2));
        HTeam.read(em);

        em.close();


        MainFrame mainFrame = new MainFrame();
//        System.out.println("Runnn");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("farm");
//        em = emf.createEntityManager();
    }
}
