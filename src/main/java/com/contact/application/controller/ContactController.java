package com.contact.application.controller;

import com.contact.application.model.Contact;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@AllArgsConstructor
public class ContactController {
    @Autowired
    private GetController getController;

    @Autowired
    private PostController postController;

    @Autowired
    private UpdateController updateController;

    @Autowired
    private DeleteController deleteController;

    @GetMapping(path = "/", produces = "application/json")
    public HttpEntity<List<Contact>> getAllContacts() {
        return new ResponseEntity<>(getController.getAllContacts(), HttpStatus.OK);
    }

    @GetMapping(path = "/contact/{id}", produces = "application/json")
    public HttpEntity<Contact> getContactById(@PathVariable("id") String contactId) {
        Optional<Contact> contact = getController.getContactById(contactId);
        return contact.<HttpEntity<Contact>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Contact.builder().build(), HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/contact", produces = "application/json")
    public HttpEntity<String> createContact(@RequestBody Contact contact) {
        Optional<String> createdContactId = postController.createContact(contact);
        return createdContactId.<HttpEntity<String>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(StringUtils.EMPTY, HttpStatus.BAD_REQUEST));
    }

    @PutMapping(path = "/contact/{id}", produces = "application/json")
    public HttpEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable String contactId) {
        Optional<Contact> updatedContact = updateController.updateContact(contact, contactId);
        return updatedContact.<HttpEntity<Contact>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Contact.builder().build(), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/contact/{id}", produces = "application/json")
    public HttpEntity<Contact> deleteContact(@RequestBody Contact contact, @PathVariable String contactId) {
        Optional<Contact> deletedContact = deleteController.deleteContact(contact, contactId);
        return deletedContact.<HttpEntity<Contact>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(Contact.builder().build(), HttpStatus.NOT_FOUND));
    }
}
