package com.faku99.sql;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that handles the connection to the SQLite database.
 */
public class DBManager {

    private static DBManager sharedInstance = null;

    private static Logger LOG = Logger.getLogger(DBManager.class.getName());

    private SessionFactory factory = null;

    private Session session = null;

    public static DBManager sharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new DBManager();
        }

        return sharedInstance;
    }

    public void close() {
        if (session != null) {
            session.close();
        }

        if (factory != null) {
            factory.close();
        }
    }

    @Override
    public void finalize() {
        close();
    }

    private DBManager() {
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

    public void save(Object object) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOG.log(Level.SEVERE, null, e);
        }
    }

    public Session getSession() {
        return session;
    }

}
