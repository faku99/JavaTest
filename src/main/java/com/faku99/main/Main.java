package com.faku99.main;

import com.faku99.sql.SQLiteConnection;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.List;

public class Main {
    public static void dropDatabase() {
        String filePath = "src/main/resources/test.sqlite";
        File dbFile = new File(filePath);

        if(dbFile.exists()) {
            dbFile.delete();
        }
    }

    public static void main(String... args) {
        dropDatabase();

        SQLiteConnection connection = SQLiteConnection.sharedInstance();
        Session session = connection.getSession();

        session.beginTransaction();

        Person david = new Person("David", 21);
        Person lucas = new Person("Lucas", 22);

        session.save(david);
        session.save(lucas);

        session.getTransaction().commit();

        List<Person> persons = session.createQuery("from Person").list();
        for(Person person : persons) {
            System.out.println(person);
        }

        CriteriaBuilder builder = session.getEntityManagerFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);

        Root<Person> root = criteria.from(Person.class);
        criteria.select(root.get(Person_.name));
        criteria.where(builder.equal(root.get(Person_.name), "David"));

        String person = session.createQuery(criteria).uniqueResult();
        System.out.println("Person: " + person);

        connection.close();
    }
}