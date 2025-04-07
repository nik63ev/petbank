package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bank.petbank.model.UserInfo;
import ru.bank.petbank.model.UserCredential;

public class Hibernate_one_to_one_UniDirectional {
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
//            newUser.setEmptUserCredential(userCredential);
//
//            session.beginTransaction();
//            session.persist(newUser);
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
//            UserInfo user1 = session.find(UserInfo.class,1);
//
//            session.getTransaction().commit();
//            System.out.println(user1.getEmptUserCredential());
//        }
//        finally {
//            session.close();
//            factory.close();
//        }

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            UserInfo user1 = session.get(UserInfo.class,2);
            session.remove(user1);

            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
