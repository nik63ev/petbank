package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bank.petbank.model.UserCredential;
import ru.bank.petbank.model.UserSession;

public class Hibernate_one_to_many_BiDirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(UserCredential.class)
                .addAnnotatedClass(UserSession.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

//            UserCredential userCredential = new UserCredential("Alex", "123456");
//            LocalDateTime startTime1 = LocalDateTime.now();
//            LocalDateTime endTime1 = startTime1.plusHours(1);
//            UserSession UserSession1 = new UserSession("1233", startTime1, endTime1, "IPhone");
//            LocalDateTime startTime2 = LocalDateTime.now();
//            LocalDateTime endTime2 = startTime2.plusHours(1);
//            UserSession UserSession2 = new UserSession("1211", startTime2, endTime2, "Android");
//
//            userCredential.addUserSession(UserSession1);
//            userCredential.addUserSession(UserSession2);
//
//            session.persist(userCredential);
//            ********************************
            UserCredential userCredential = session.get(UserCredential.class, 10);

            System.out.println(userCredential);
            System.out.println(userCredential.getSessionList());

//            UserSession userSes = session.get(UserSession.class, 3);

//********************************************************************
//            UserSession userSes = session.get(UserSession.class, 1);
//            session.remove(userSes);

            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
