package com.contact.application.service;

import com.contact.application.model.Contact;
import com.contact.application.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class ContactDao {
    private SessionFactory sessionFactory;

    public List<String> save(List<Contact> contacts) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> res = new ArrayList<String>();
        for (Contact contact : contacts) {
            System.out.printf("- %s%n", contact);
            res.add((String) session.save(contact));
        }
        session.getTransaction().commit();
        session.close();
        return res;
    }

    public String save(Contact contact) {
        if (!isContactValid(contact)) {
            throw new IllegalArgumentException("Contact is invalid");
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String res = "";
        res = (String) session.save(contact);
        session.getTransaction().commit();
        session.close();
        return res;
    }

    public List<Contact> getContacts() {
        System.out.println("-- loading contact --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Contact> contacts = session.createQuery("FROM Contact ").list();
        session.close();
        return contacts;
    }

    public List<Phone> getPhones() {
        System.out.println("-- loading contact --");
        Session session = sessionFactory.openSession();
        @SuppressWarnings("unchecked")
        List<Phone> phones = session.createQuery("FROM Phone ").list();
        session.close();
        return phones;
    }

    public void update(Contact contact) {
        if (!isContactValid(contact)) {
            throw new IllegalArgumentException("Contact is invalid");
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Contact existingContact = session.get(Contact.class, contact.getId());
        if (existingContact == null) {
            throw new IllegalArgumentException("Contact does not exist");
        } else {
            existingContact.setFirstName(contact.getFirstName());
            existingContact.setLastName(contact.getLastName());
            existingContact.setPhoneList(contact.getPhoneList());
            session.update(existingContact);
            session.getTransaction().commit();
            session.close();
        }
    }

    public Contact update(Contact contact, String id) {
        //ToDo: Create update method for Dao
        return null;
    }

    public Contact delete(Contact contact, String id) {
        //ToDo: Create delete method for Dao
        return null;
    }

    public void delete(Contact contact) {
        if (!isContactValid(contact)) {
            throw new IllegalArgumentException("Contact is invalid");
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
        session.close();
    }
    private boolean isContactValid(Contact contact) {
        return contact != null && !StringUtils.isBlank(contact.getId());
    }
}
