package contact_management;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class ContactDaoTest {
    private SessionFactory sessionFactoryMock = mock(SessionFactory.class);
    private Session sessionMock = mock(Session.class);
    private Transaction transactionMock = mock(Transaction.class);
    private Query queryMockContact = mock(Query.class);
    private Query queryMockPhone = mock(Query.class);

    private ContactDao contactDao;
    private String expectedIdOne;
    private String expectedIdTwo;
    private List<String> expectedIds;
    private TestUtils testUtils;
    private Contact expectedContact;
    private List<Contact> expectedContacts;
    private List<Phone> expectedPhones;

    @Before
    public void setUp() {
        testUtils = new TestUtils();
        expectedIdOne = testUtils.getUUID();
        expectedIdTwo = testUtils.getUUID();
        contactDao = new ContactDao(sessionFactoryMock);
        expectedContact = testUtils.getRandomContact(expectedIdOne);
        expectedContacts = testUtils.getRandomContacts(Arrays.asList(expectedIdOne, expectedIdTwo));
        expectedPhones = Collections.singletonList(testUtils.getPhone());

        expectedIds = Arrays.asList(expectedIdOne, expectedIdTwo);

        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);
        when(sessionMock.save(anyObject())).thenReturn(expectedIdOne).thenReturn(expectedIdTwo);
        when(sessionMock.getTransaction()).thenReturn(transactionMock);
        when(sessionMock.createQuery(eq("FROM Contact "))).thenReturn(queryMockContact);
        when(queryMockContact.list()).thenReturn(expectedContacts);
        when(sessionMock.createQuery(eq("FROM Phone "))).thenReturn(queryMockPhone);
        when(queryMockPhone.list()).thenReturn(expectedPhones);
    }

    @Test
    public void testContactDao_saveSingleEntry_ShouldReturnId() {
        String actualId = contactDao.save(expectedContact);
        Assert.assertEquals(actualId, expectedIdOne);
    }

    @Test
    public void testContactDao_saveMultipleEntries_ShouldReturnValidIds() {
        List<String> actualIds = contactDao.save(expectedContacts);
        Assert.assertEquals(actualIds, expectedIds);
    }

    @Test
    public void testContactDao_getContacts_ShouldReturnValidContacts() {
        List<Contact> actualContacts = contactDao.getContacts();
        Assert.assertEquals(actualContacts, expectedContacts);
    }

    @Test
    public void testContactDao_getPhones_ShouldReturnValidPhones() {
        List<Phone> actualPhones = contactDao.getPhones();
        Assert.assertEquals(actualPhones, expectedPhones);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContactDao_updateContact_withNonExistingId_ShouldThrowException() {
        Contact updatedContact = testUtils.getRandomContact("Random");
        contactDao.update(updatedContact);
    }

    @Test
    public void testContactDao_updateContact_withExistingId_ShouldUpdateSession() {
        Contact existingContact = testUtils.getRandomContact(expectedContact.getId());
        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        doNothing().when(sessionMock).update(contactArgumentCaptor.capture());
        when(sessionMock.get(eq(Contact.class), eq(expectedContact.getId()))).thenReturn(existingContact);

        contactDao.update(expectedContact);

        verify(sessionMock, times(1)).update(isA(Contact.class));
        Assert.assertEquals(expectedContact, contactArgumentCaptor.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContactDao_deleteContact_withInvalidInput_ShouldThrowException() {
        contactDao.delete(null);
    }

    @Test
    public void testContactDao_deleteContact_withValidInput_shouldDeleteRecord() {
        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        doNothing().when(sessionMock).delete(contactArgumentCaptor.capture());

        contactDao.delete(expectedContact);

        verify(sessionMock, times(1)).delete(isA(Contact.class));
        Assert.assertEquals(expectedContact, contactArgumentCaptor.getValue());
    }
}