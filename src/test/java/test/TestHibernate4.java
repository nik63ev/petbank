package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bank.petbank.model.UserCredential;

public class TestHibernate4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(UserCredential.class).buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.createMutationQuery("delete UserCredential where username = 'Oleg'").executeUpdate();
//            userCredential.setPassword("2203");
//            session.createQuery("update UserCredential set password = '1111' where username = 'Oleg'").executeUpdate();

            session.getTransaction().commit();
            System.out.println("done");
        }
        finally {
            factory.close();
        }
    }
}
