package contacts;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ContactDAO {
    public Long addContactWithPhone(String name, List<Phone> phoneList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long contactId = null;
        try {
            transaction = session.beginTransaction();
            Contact contact = new Contact();
            contact.setName(name);
            //Phone table foreign key restriction, must have id first
            session.save(contact);

            for (Phone phone : phoneList) {
                phone.setContactId(contact.getContactId());
            }

            contact.setPhoneNumber(phoneList);
            contactId = (Long) session.save(contact);
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
}
