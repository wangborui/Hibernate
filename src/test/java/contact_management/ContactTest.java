package contact_management;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactTest {

    private SessionFactory sessionFactory;
    private String expectedId;
    private String expectedId_two;
    private String expectedFirstName;
    private String expectedFirstName_two;
    private String expectedLastName;
    private String expectedLastName_two;

    @Before
    public void setUp() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        expectedId = UUID.randomUUID().toString();
        expectedId_two = UUID.randomUUID().toString();
        expectedFirstName = "first name";
        expectedFirstName_two = "first name two";
        expectedLastName = "last name";
        expectedLastName_two = "last name two";
    }

    @Test
    public void testAddContact_InsertIntoDB_LoadAndVerifyValue() {
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName);
        contacts.add(contact);
        persist(contacts);
        List<Contact> actualContacts = load();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 1);
        Assert.assertEquals(actualContacts.get(0).getId(), expectedId);
        Assert.assertEquals(actualContacts.get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualContacts.get(0).getLastName(), expectedLastName);
    }

    @Test
    public void testAddMultipleContact_InsertIntoDB_LoadAndVerifyValue() {
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName);
        Contact contact_two = new Contact(expectedId_two, expectedFirstName_two, expectedLastName_two);

        contacts.add(contact);
        contacts.add(contact_two);

        persist(contacts);
        List<Contact> actualContacts = load();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 2);
        Assert.assertEquals(actualContacts.get(0).getId(), expectedId);
        Assert.assertEquals(actualContacts.get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualContacts.get(0).getLastName(), expectedLastName);
        Assert.assertEquals(actualContacts.get(1).getId(), expectedId_two);
        Assert.assertEquals(actualContacts.get(1).getFirstName(), expectedFirstName_two);
        Assert.assertEquals(actualContacts.get(1).getLastName(), expectedLastName_two);
    }

    private void persist(List<Contact> contacts) {
        System.out.println("-- persisting contact --");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Contact contact : contacts) {
            System.out.printf("- %s%n", contact);
            session.save(contact);
        }
        session.getTransaction().commit();
    }

    private List<Contact> load() {
        System.out.println("-- loading contact --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Contact> contacts = session.createQuery("FROM Contact ").list();
        for (Contact contact : contacts) {
            System.out.printf("- %s%n", contact);
        }
        session.close();
        return contacts;
    }
}