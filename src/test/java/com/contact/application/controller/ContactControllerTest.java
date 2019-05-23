package com.contact.application.controller;

import com.contact.application.model.Contact;
import com.contact.application.service.TestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContactControllerTest {
    private TestUtils testUtils;
    private GetController getControllerMock = mock(GetController.class);
    private PostController postControllerMock = mock(PostController.class);
    private UpdateController updateControllerMock = mock(UpdateController.class);
    private DeleteController deleteControllerMock = mock(DeleteController.class);

    private ContactController contactController;
    private List<Contact> expectedContactList;
    private Contact expectedContact;
    private String expectedContactId;
    private String invalidId;
    @Before
    public void setUp() {
        testUtils = new TestUtils();
        contactController = new ContactController(getControllerMock, postControllerMock, updateControllerMock, deleteControllerMock);
        expectedContactId = testUtils.getUUID();
        expectedContact = testUtils.getRandomContact(expectedContactId);
        expectedContactList = Collections.singletonList(expectedContact);
        invalidId = "invalid";

        when(getControllerMock.getAllContacts()).thenReturn(expectedContactList);
        when(getControllerMock.getContactById(eq(expectedContactId))).thenReturn(Optional.of(expectedContact));
        when(getControllerMock.getContactById(eq(invalidId))).thenReturn(Optional.empty());

        when(postControllerMock.createContact(eq(expectedContact))).thenReturn(Optional.of(expectedContactId));
        when(postControllerMock.createContact(eq(null))).thenReturn(Optional.empty());

        when(updateControllerMock.updateContact(eq(expectedContact), eq(expectedContactId))).thenReturn(Optional.of(expectedContact));
        when(updateControllerMock.updateContact(eq(expectedContact), eq(invalidId))).thenReturn(Optional.empty());

        when(deleteControllerMock.deleteContact(eq(expectedContact), eq(expectedContactId))).thenReturn(Optional.of(expectedContact));
        when(deleteControllerMock.deleteContact(eq(expectedContact), eq(invalidId))).thenReturn(Optional.empty());
    }

    @Test
    public void getAllContactsTest_ReturnValidList_AndReturnCorrectCode() {
        HttpEntity<List<Contact>> entity = contactController.getAllContacts();
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(), expectedContactList);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getContactById_givenValidId_ShouldReturnValid_Contact() {
        HttpEntity<Contact> entity = contactController.getContactById(expectedContactId);
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(), expectedContact);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getContactById_givenIncorrectId_ShouldNotReturnValid_Contact() {
        HttpEntity<Contact> entity = contactController.getContactById(invalidId);
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(), Contact.builder().build());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void createContactTest_givenValidContact_willCreateNewContact_AndReturnId() {
        HttpEntity<String> createdContactId = contactController.createContact(expectedContact);
        ResponseEntity responseEntity = (ResponseEntity) createdContactId;
        assertNotNull(createdContactId.getBody());
        assertEquals(createdContactId.getBody(), expectedContactId);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void createContactTest_givenNullContact_willCreateNewContact_AndReturnId() {
        HttpEntity<String> createdContactId = contactController.createContact(null);
        ResponseEntity responseEntity = (ResponseEntity) createdContactId;
        assertNotNull(createdContactId.getBody());
        assertEquals(createdContactId.getBody(), StringUtils.EMPTY);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void updateContactTest_givenValidContact_andValidContactId_ShouldReturnUpdatedContact() {
        HttpEntity<Contact> entity = contactController.updateContact(expectedContact, expectedContactId);
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(),expectedContact);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void updateContactTest_givenValidContact_andInvalidContactId_ShouldReturnEmptyContact() {
        HttpEntity<Contact> entity = contactController.updateContact(expectedContact, invalidId);
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(), Contact.builder().build());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteContactTest_givenValidContact_andValidContactId_ShouldReturnDeletedContact() {
        HttpEntity<Contact> entity = contactController.deleteContact(expectedContact, expectedContactId);
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(), expectedContact);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteContactTest_givenValidContact_andInvalidContactId_ShouldReturnDeletedContact() {
        HttpEntity<Contact> entity = contactController.deleteContact(expectedContact, invalidId);
        ResponseEntity responseEntity = (ResponseEntity) entity;
        assertNotNull(entity.getBody());
        assertEquals(entity.getBody(), Contact.builder().build());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
