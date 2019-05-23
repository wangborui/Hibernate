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
public class PostController {
    @Autowired
    private ContactDao contactDao;

    public Optional<String> createContact(Contact contact) {
        return Optional.ofNullable(contactDao.save(contact));
    }
}
