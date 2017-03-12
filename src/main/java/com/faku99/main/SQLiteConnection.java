package com.faku99.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

/**
 * Class that handles the connection to the SQLite database.
 */
public class SQLiteConnection {

    /**
     * Connect to the SQLite database.
     */
    public static void connect() {
        SessionFactory factory = null;
        ServiceRegistry registry = null;

        Session session = null;
        Transaction transaction = null;

        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            Properties properties = configuration.getProperties();

            registry = new StandardServiceRegistryBuilder().applySettings(properties).build();
            factory = configuration.buildSessionFactory();
            System.out.println("Factory built.");

            session = factory.openSession();
            System.out.println("Session opened.");

            transaction = session.beginTransaction();

            List<Test> testList = session.createQuery("from Test").list();
            System.out.println("\n'test' table contents:");
            for(Test test : testList) {
                System.out.printf(" %d: %s\n", test.getId(), test.getStr());
            }

            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();

            transaction.rollback();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        System.out.println("[connect] Done.");

        return;
    }

    /**
     * Main program.
     *
     * @param args  Program arguments.
     */
    public static void main(String... args) {
        SQLiteConnection.connect();

        System.out.println("[main] Done.");
    }

}
