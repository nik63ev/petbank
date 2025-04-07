package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bank.petbank.model.UserCredential;
import ru.bank.petbank.model.UserInfo;

public class Hibernate_one_to_one_BiDirectional {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml").addAnnotatedClass(UserCredential.class)
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();

        Session session = null;

//        try {
//            session = factory.getCurrentSession();
//
//            UserCredential userCredential = new UserCredential();
//            userCredential.setUsername("Alex");
//            userCredential.setPassword("14245");
//
//            LocalDate date = LocalDate.of(1992, 2, 25);
//            UserInfo newUser = new UserInfo("Borsch", "Alexey", "Ivanovich", date);
//            newUser.setUserCredential(userCredential);
//            userCredential.setUserInfo(newUser);
//
//            session.beginTransaction();
//            session.persist(userCredential);
//
//            session.getTransaction().commit();
//            System.out.println(newUser.getId());
//        }
//        finally {
//            session.close();
//            factory.close();
//        }
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            UserCredential userCredential = session.get(UserCredential.class, 6);
//            session.remove(userCredential);
//
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            session.close();
//            factory.close();
//        }
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            UserCredential userCredential = session.get(UserCredential.class, 1);
//
//            System.out.println(userCredential.getUserInfo());
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            session.close();
//            factory.close();
//        }
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            UserCredential userCredential = session.get(UserCredential.class, 1);
//            userCredential.getUserInfo().setUserCredential(null);
//            session.remove(userCredential);
//            session.getTransaction().commit();
//            System.out.println("Done");
//        }
//        finally {
//            session.close();
//            factory.close();
//        }
    }
}
