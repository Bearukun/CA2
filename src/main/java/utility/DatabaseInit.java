package utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseInit {
    
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public static void main(String[] args) {
        
        DatabaseInit bdI = new DatabaseInit();
        
        bdI.initiateSystem();
        
        
        
        
    }
    
        public void initiateSystem() {

        Persistence.generateSchema("pu", null);

        emf = Persistence.createEntityManagerFactory("pu");

        em = emf.createEntityManager();

    }
    
}
