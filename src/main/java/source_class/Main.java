package source_class;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] strings) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("org.hibernate.tutorial.jpa")
                .createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        //Persist a User entity
        entityTransaction.begin();
        Contact client = new Contact();
        client.setName("bob");

        Phone phone = new Phone();
        phone.setContactId("1");
        phone.setPhoneNumber("123456789");
        phone.setPhoneType(PhoneType.HOME);
        phone.setCustomType("");
        phone.setDeleted(false);

        entityManager.persist(client);
        entityManager.persist(phone);
        entityTransaction.commit();

        entityManager.close();
    }
}
