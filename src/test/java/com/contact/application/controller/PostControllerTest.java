package com.contact.application.controller;

import com.contact.application.model.Contact;
import com.contact.application.service.ContactDao;
import com.contact.application.service.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostControllerTest {
    private TestUtils testUtils;
    private ContactDao contactDaoMock = mock(ContactDao.class);
    private PostController postController;

    private Contact expectedContact;
    private String expectedContactId;

    @Before
    public void setUp() {
        testUtils = new TestUtils();
        postController = new PostController(contactDaoMock);
        expectedContactId = testUtils.getUUID();
        expectedContact = testUtils.getRandomContact(expectedContactId);
        when(contactDaoMock.save(eq(expectedContact))).thenReturn(expectedContactId);
    }

    @Test
    public void createContactTest_givenValidContact_ShouldReturnContactId() {
        Optional<String> actualContactId = postController.createContact(expectedContact);
        assertTrue(actualContactId.isPresent());
        assertEquals(actualContactId.get(), expectedContactId);
    }
}
