package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Scanner;

public class UserPrompt {
    public static SessionFactory factory;
    public static ServiceRegistry registry;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String m = "";
        System.out.println("Enter a message: ");
        m = in.nextLine();
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(MessageEntity.class);
            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Shit went wrong");
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Short msgId = null;
        try {
            tx = session.beginTransaction();
            MessageEntity msg = new MessageEntity();
            msg.setMessage(m);
            msgId = (Short) session.save(msg);
            List<MessageEntity> client = session.createQuery("from hibernate.MessageEntity ").list();
            for (MessageEntity entity1 : client) {
                System.out.println(entity1);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
