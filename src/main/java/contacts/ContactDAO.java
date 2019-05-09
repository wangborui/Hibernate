package contacts;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class ContactDAO {
    public String addContactWithPhone(String name, List<Phone> phoneList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        String contactId = null;
        try {
            transaction = session.beginTransaction();
            Contact contact = new Contact();
            contact.setContactId(UUID.randomUUID().toString());
            contact.setName(name);

            for (Phone phone : phoneList) {
                phone.setContactId(contact.getContactId());
            }
            System.out.println("Contact" + contact);
            System.out.println("Phone" + phoneList);
            contact.setPhoneNumber(phoneList);
            contactId = (String)session.save(contact);
            session.getTransaction().commit();

            return contactId;
        } catch( Exception e ) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return contactId;
        }
    }

    public void updateContactName(Long contactId, String contactName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Contact contact = (Contact) session.get(Contact.class, contactId);
            contact.setName(contactName);
            session.update(contact);
            transaction.commit();
        } catch( Exception e ) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void deleteContact(Long contactId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Contact contact = (Contact) session.get(Contact.class, contactId);
            session.delete(contact);
            transaction.commit();
        } catch( Exception e ) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public String addContactWithName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        String contactId = null;
        try {
            transaction = session.beginTransaction();
            Contact contact = new Contact();
            contact.setContactId(UUID.randomUUID().toString());
            contact.setName(name);

            contactId = (String)session.save(contact);
            session.getTransaction().commit();

            return contactId;
        } catch( Exception e ) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return contactId;
        }
    }
}
