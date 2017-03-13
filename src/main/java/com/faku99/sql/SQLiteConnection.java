package com.faku99.sql;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class that handles the connection to the SQLite database.
 */
public class SQLiteConnection {

    private static SQLiteConnection sharedInstance = null;

    private SessionFactory factory = null;
    private Session session = null;

    public static SQLiteConnection sharedInstance() {
        if(sharedInstance == null) {
            sharedInstance = new SQLiteConnection();
        }

        return sharedInstance;
    }

    public void close() {
        if(session != null) {
            session.close();
        }

        if(factory != null) {
            factory.close();
        }
    }

    @Override
    public void finalize() {
        close();
    }

    public Session getSession() {
        return session;
    }

    private SQLiteConnection() {
        this.connect();
    }

    private void connect() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            factory = configuration.buildSessionFactory();
            System.out.println("Factory built.");

            session = factory.openSession();
            System.out.println("Session opened.");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
