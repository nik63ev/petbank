package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bank.petbank.model.UserCredential;

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(UserCredential.class).buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            UserCredential userCredential = new UserCredential();
            userCredential.setUsername("Oleg");
            userCredential.setPassword("1445");
            session.beginTransaction();
            session.persist(userCredential);

            Long myId = userCredential.getId();
//            session = factory.getCurrentSession();
//            session.beginTransaction();
            UserCredential getUserCredential = session.get(UserCredential.class, myId);
            session.getTransaction().commit();
            System.out.println(getUserCredential);
        }
        finally {
            factory.close();
        }
    }
}
