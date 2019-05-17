package contact_management;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactTest {

    private SessionFactory sessionFactory;
    private List<Phone> phones;
    private ContactDao contactDao;
    private TestUtils testUtils;

    private String expectedId;
    private String expectedId_two;
    private String expectedFirstName;
    private String expectedFirstName_two;
    private String expectedLastName;
    private String expectedLastName_two;
    private String phoneNumber;
    private String customType;
    private String phoneId;

    private PhoneType phoneType;
    private boolean deleted;

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
        phoneNumber = "123456";
        customType = "N/A";
        phoneType = PhoneType.WORK;
        deleted = false;
        phoneId = testUtils.getUUID();
    }

    @After
    public void tearDown() {
        sessionFactory.close();
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
    public void testAddContact_AndPhone_InsertIntoDB_LoadAndVerifyValue() {
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName);
        Phone phone = new Phone(phoneId, phoneNumber, customType, phoneType, deleted);

        contact.setPhoneList(Collections.singletonList(phone));
        contactDao.save(contact);

        List<Contact> actualContacts = contactDao.getContacts();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 1);
        Assert.assertEquals(actualContacts.get(0).getId(), expectedId);
        Assert.assertEquals(actualContacts.get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualContacts.get(0).getLastName(), expectedLastName);

        List<Phone> actualPhones = actualContacts.get(0).getPhoneList();
        Assert.assertNotNull(actualPhones);
        Assert.assertEquals(actualPhones.size(), 1);
        Assert.assertEquals(actualPhones.get(0).getId(), phoneId);
        Assert.assertEquals(actualPhones.get(0).getPhoneNumber(), phoneNumber);
        Assert.assertEquals(actualPhones.get(0).getCustomType(), customType);
        Assert.assertEquals(actualPhones.get(0).getPhoneType(), phoneType);
        Assert.assertEquals(actualPhones.get(0).isDeleted(), deleted);
        Assert.assertEquals(actualPhones.get(0).getContacts().size(), 1);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getLastName(), expectedLastName);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getId(), expectedId);
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
    public void testAddMultipleContact_AndPhoneNumbers_InsertIntoDB_LoadAndVerifyValue() {
        List<Contact> contacts = new ArrayList<Contact>();
        Phone phone = new Phone(phoneId, phoneNumber, customType, phoneType, deleted);
        phones.add(phone);

        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName);
        contact.setPhoneList(phones);

        Contact contact_two = new Contact(expectedId_two, expectedFirstName_two, expectedLastName_two);
        contact_two.setPhoneList(phones);

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

        List<Phone> actualPhones = actualContacts.get(0).getPhoneList();
        Assert.assertNotNull(actualPhones);
        Assert.assertEquals(actualPhones.size(), 1);
        Assert.assertEquals(actualPhones.get(0).getId(), phoneId);
        Assert.assertEquals(actualPhones.get(0).getPhoneNumber(), phoneNumber);
        Assert.assertEquals(actualPhones.get(0).getCustomType(), customType);
        Assert.assertEquals(actualPhones.get(0).getPhoneType(), phoneType);
        Assert.assertEquals(actualPhones.get(0).isDeleted(), deleted);
        Assert.assertEquals(actualPhones.get(0).getContacts().size(), 2);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getLastName(), expectedLastName);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getFirstName(), expectedFirstName);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getId(), expectedId);

        actualPhones = actualContacts.get(1).getPhoneList();
        Assert.assertNotNull(actualPhones);
        Assert.assertEquals(actualPhones.size(), 1);
        Assert.assertEquals(actualPhones.get(0).getId(), phoneId);
        Assert.assertEquals(actualPhones.get(0).getPhoneNumber(), phoneNumber);
        Assert.assertEquals(actualPhones.get(0).getCustomType(), customType);
        Assert.assertEquals(actualPhones.get(0).getPhoneType(), phoneType);
        Assert.assertEquals(actualPhones.get(0).isDeleted(), deleted);
        Assert.assertEquals(actualPhones.get(0).getContacts().size(), 2);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(1).getLastName(), expectedLastName_two);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(1).getFirstName(), expectedFirstName_two);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(1).getId(), expectedId_two);
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

    @Test
    public void testUpdateContact_AndPhone_InsertIntoDB_updateExistingContact_LoadAndVerifyValue() {
        Contact expectedContact = testUtils.getRandomContact(expectedId);
        String actualId = contactDao.save(expectedContact);
        Contact updatedContact = testUtils.getRandomContact(actualId);

        Phone phone = new Phone(phoneId, phoneNumber, customType, phoneType, deleted);
        updatedContact.setPhoneList(Collections.singletonList(phone));

        contactDao.update(updatedContact);
        List<Contact> actualContacts = contactDao.getContacts();
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(actualContacts.size(), 1);
        Assert.assertEquals(actualContacts.get(0).getId(), updatedContact.getId());
        Assert.assertEquals(actualContacts.get(0).getFirstName(), updatedContact.getFirstName());
        Assert.assertEquals(actualContacts.get(0).getLastName(), updatedContact.getLastName());

        List<Phone> actualPhones = actualContacts.get(0).getPhoneList();
        Assert.assertNotNull(actualPhones);
        Assert.assertEquals(actualPhones.size(), 1);
        Assert.assertEquals(actualPhones.get(0).getId(), phoneId);
        Assert.assertEquals(actualPhones.get(0).getPhoneNumber(), phoneNumber);
        Assert.assertEquals(actualPhones.get(0).getCustomType(), customType);
        Assert.assertEquals(actualPhones.get(0).getPhoneType(), phoneType);
        Assert.assertEquals(actualPhones.get(0).isDeleted(), deleted);
        Assert.assertEquals(actualPhones.get(0).getContacts().size(), 1);
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getLastName(),  updatedContact.getLastName());
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getFirstName(), updatedContact.getFirstName());
        Assert.assertEquals(actualPhones.get(0).getContacts().get(0).getId(), updatedContact.getId());
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
    public void testDeleteContact_AndPhone_InsertIntoDB_deleteWithExistingId_shouldBeOk() {
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName, phones);
        Phone phone = new Phone(phoneId, phoneNumber, customType, phoneType, deleted);
        contact.setPhoneList(Collections.singletonList(phone));

        contactDao.save(contact);
        List<Phone> actualPhones = contactDao.getPhones();
        Assert.assertEquals(1, actualPhones.size());

        contactDao.delete(contact);
        List<Contact> actualContacts = contactDao.getContacts();
        actualPhones = contactDao.getPhones();

        Assert.assertEquals(0, actualPhones.size());
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

    @Test
    public void testDeleteContact_AndPhones_InsertIntoDB_deleteWithNonExistingId_shouldNotbeDeleted() {
        Contact contact = new Contact(expectedId, expectedFirstName, expectedLastName, phones);
        Phone phone = new Phone(phoneId, phoneNumber, customType, phoneType, deleted);
        contact.setPhoneList(Collections.singletonList(phone));

        contactDao.save(contact);
        List<Phone> actualPhones = contactDao.getPhones();
        Assert.assertEquals(1, actualPhones.size());

        contactDao.delete(testUtils.getRandomContact("nonexisting"));
        List<Contact> actualContacts = contactDao.getContacts();
        actualPhones = contactDao.getPhones();
        Assert.assertEquals(1, actualPhones.size());
        Assert.assertNotNull(actualContacts);
        Assert.assertEquals(1, actualContacts.size());
    }
}