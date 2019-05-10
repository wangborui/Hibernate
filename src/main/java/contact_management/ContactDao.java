package contact_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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
}
