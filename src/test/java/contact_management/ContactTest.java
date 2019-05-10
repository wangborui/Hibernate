package contact_management;

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
    private List<Phone> phones;
    private ContactDao contactDao;
    private TestUtils testUtils;

    @Before
    public void setUp() {
        testUtils = new TestUtils();
        sessionFactory = new Configuration().configure().buildSessionFactory();
        contactDao = new ContactDao(sessionFactory);

        expectedId = UUID.randomUUID().toString();
        expectedId_two = UUID.randomUUID().toString();
        expectedFirstName = "first name";
        expectedFirstName_two = "first name two";
        expectedLastName = "last name";
        expectedLastName_two = "last name two";
        phones = new ArrayList<Phone>();
    }

    @Test
    public void testAddContact_InsertIntoDB_LoadAndVerifyValue() {
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName, phones);

        contactDao.save(contact);

        List<Contact> actualContacts = contactDao.getContacts();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 1);
        Assert.assertEquals(actualContacts.get(0).getId(), expectedId);
        Assert.assertEquals(actualContacts.get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualContacts.get(0).getLastName(), expectedLastName);
    }

    @Test
    public void testAddMultipleContact_InsertIntoDB_LoadAndVerifyValue() {
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName, phones);
        Contact contact_two = new Contact(expectedId_two, expectedFirstName_two, expectedLastName_two, phones);

        contacts.add(contact);
        contacts.add(contact_two);

        contactDao.save(contacts);

        List<Contact> actualContacts = contactDao.getContacts();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 2);
        Assert.assertEquals(actualContacts.get(0).getId(), expectedId);
        Assert.assertEquals(actualContacts.get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualContacts.get(0).getLastName(), expectedLastName);
        Assert.assertEquals(actualContacts.get(1).getId(), expectedId_two);
        Assert.assertEquals(actualContacts.get(1).getFirstName(), expectedFirstName_two);
        Assert.assertEquals(actualContacts.get(1).getLastName(), expectedLastName_two);
    }
}