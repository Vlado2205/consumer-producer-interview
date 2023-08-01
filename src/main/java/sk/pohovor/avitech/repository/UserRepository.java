package sk.pohovor.avitech.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sk.pohovor.avitech.configuration.HibernateConfiguration;
import sk.pohovor.avitech.domain.entity.User;

/**
 * @author Vladimír Leško <a href="mailto:vladovutbr2@gmail.com">vladovutbr2@gmail.com</a>
 */
public final class UserRepository {
    private static UserRepository instance = null;

    private UserRepository() {

    }

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    public List<User> getUsers() {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public void deleteAll() {
        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query=session.createQuery("delete from User u");
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static synchronized UserRepository getInstance() {
        if (instance == null)
            instance = new UserRepository();

        return instance;
    }
}
