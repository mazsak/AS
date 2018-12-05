import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManager em;

    public static void main(String[] args){
        System.out.println("Runnn");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("farm");
        em = emf.createEntityManager();
    }
}
