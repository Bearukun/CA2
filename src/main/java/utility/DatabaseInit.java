package utility;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseInit {
    
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public static void main(String[] args) {
        
        DatabaseInit bdI = new DatabaseInit();
        
        bdI.initiateSystem();
        
        //bdI.testAdd();
        
        
        
        
    }
    
        public void initiateSystem() {

        Persistence.generateSchema("pu", null);

        emf = Persistence.createEntityManagerFactory("pu");

        em = emf.createEntityManager();

    }
        
        public void testAdd(){
            
                    try {

            em.getTransaction().begin();
            
            List<Hobby> hobbies = new ArrayList();
            
            hobbies.add(new Hobby("Tennis", "Very nice sport yo yo "));
            
            
            List<Phone> phones = new ArrayList();
            
            phones.add(new Phone(87676542, "Home number"));

            InfoEntity ie = new InfoEntity("benny@hansen.dk", new Person("Benny", "Hansen", hobbies), new Company("Apple", "So good", 8373638, 9000, 2340), phones, new Address("Hulla bulla", "Addinfo", new CityInfo(3480, "Fredensborg")));
            

            em.persist(ie);

        
            em.getTransaction().commit();

            //If something goes wrong, use getTransaction().rollback();
        } finally {

            em.close();

        }
            
            
        }
    
}
