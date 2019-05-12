package contact_management;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        expectedId = testUtils.getUUID();
        expectedId_two = testUtils.getUUID();
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

    @Test
    public void testUpdateContact_InsertIntoDB_updateExistingContact_LoadAndVerifyValue() {
        Contact expectedContact = testUtils.getRandomContact(expectedId);
        String actualId = contactDao.save(expectedContact);
        Contact updatedContact = testUtils.getRandomContact(actualId);

        contactDao.update(updatedContact);
        List<Contact> actualContacts = contactDao.getContacts();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 1);
        Assert.assertEquals(actualContacts.get(0).getId(), updatedContact.getId());
        Assert.assertEquals(actualContacts.get(0).getFirstName(), updatedContact.getFirstName());
        Assert.assertEquals(actualContacts.get(0).getLastName(), updatedContact.getLastName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateContact_InsertIntoDB_updateWithNonExistingId_ShouldThrowException() {
        Contact contact = testUtils.getRandomContact(expectedId);
        contactDao.save(contact);
        contactDao.update(testUtils.getRandomContact("Random"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateContact_InsertIntoDB_updateWithNullId_ShouldThrowException() {
        Contact contact = testUtils.getRandomContact(expectedId);
        contactDao.save(contact);
        contactDao.update(testUtils.getRandomContact(null));
    }

    @Test
    public void testDeleteContact_InsertIntoDB_deleteWithExistingId_shouldBeOk() {
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName, phones);

        contactDao.save(contact);
        contactDao.delete(contact);
        List<Contact> actualContacts = contactDao.getContacts();

        Assert.assertNotNull(actualContacts);
        Assert.assertTrue(actualContacts.isEmpty());
    }

    @Test
    public void testDeleteContact_InsertIntoDB_deleteWithNonExistingId_shouldNotbeDeleted() {
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName, phones);

        contactDao.save(contact);
        contactDao.delete(testUtils.getRandomContact("nonexisting"));
        List<Contact> actualContacts = contactDao.getContacts();

        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(1, actualContacts.size());
    }
}