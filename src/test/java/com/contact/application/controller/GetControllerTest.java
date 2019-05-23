package com.contact.application.controller;

import com.contact.application.model.Contact;
import com.contact.application.service.ContactDao;
import com.contact.application.service.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetControllerTest {
    private ContactDao contactDaoMock = mock(ContactDao.class);
    private GetController getController;
    private TestUtils testUtils;

    private List<Contact> expectedContactList;
    private Contact expectedContact;
    private String expectedContactId = "expectedId";

    @Before
    public void setUp() {
        testUtils = new TestUtils();
        getController = new GetController(contactDaoMock);
        expectedContact = testUtils.getRandomContact(expectedContactId);
        expectedContactList = Collections.singletonList(expectedContact);

        when(contactDaoMock.getContacts()).thenReturn(expectedContactList);
    }

    @Test
    public void getAllContacts_ReturnListOfContacts() {
        List<Contact> actualList = getController.getAllContacts();
        assertEquals(actualList, expectedContactList);
    }

    @Test
    public void getContactTest_givenValidId_shouldReturnValidContact() {
        Optional<Contact> actualContact = getController.getContactById(expectedContactId);
        assertTrue(actualContact.isPresent());
        assertEquals(actualContact.get(), expectedContact);
    }

}
