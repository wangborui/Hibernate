package com.contact.application.controller;

import com.contact.application.model.Contact;
import com.contact.application.service.ContactDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
@AllArgsConstructor
public class UpdateController {
    @Autowired
    private ContactDao contactDao;

    public Optional<Contact> updateContact(Contact contact, String id) {
        return Optional.ofNullable(contactDao.update(contact, id));
    }
}
