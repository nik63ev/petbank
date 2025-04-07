package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bank.petbank.model.UserCredential;

import java.util.List;

public class TestHibernate2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(UserCredential.class).buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            List<UserCredential> users = session.createQuery("from UserCredential " +
                                        "WHERE username = 'Oleg'").getResultList();

            for (UserCredential user : users) {
                System.out.println(user);
            }

            session.getTransaction().commit();
            System.out.println("done");
        }
        finally {
            factory.close();
        }
    }
}
