package com.contact.application.controller;

import com.contact.application.model.Contact;
import com.contact.application.service.ContactDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
@AllArgsConstructor
public class GetController {
    @Autowired
    ContactDao contactDao;

    public List<Contact> getAllContacts() {
        return contactDao.getContacts();
    }

    public Optional<Contact> getContactById(String id) {
        //Todo: Create method in ContactDao to retrieve a single contact provided contact id
        List<Contact> contactList = getAllContacts();
        return contactList.stream().filter(contact -> contact.getId().equals(id)).findFirst();
    }

}
