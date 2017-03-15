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

        bdI.testAdd();
    }

    public void initiateSystem() {

        Persistence.generateSchema("pu", null);

        emf = Persistence.createEntityManagerFactory("pu");

        em = emf.createEntityManager();

    }

    public void testAdd() {

        try {

            em.getTransaction().begin();

            Person person = new Person("Ulla", "Jensen", "test@mail.dk");
        
            person.addHobby(new Hobby("Racing", "Drifting around"));
            

            em.persist(person);
            

            em.getTransaction().commit();

            //If something goes wrong, use getTransaction().rollback();
        } finally {

            em.close();

        }

    }

}
