package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Person person1 = session.get(Person.class, 2); // REVIEW

            Person person2 = new Person("Sem", 21); // CREATE
            session.save(person2);

            Person person3 = session.get(Person.class, 4); // UPDATE
            person3.setName("New name");

            Person person4 = session.get(Person.class, 4); // DELETE
            session.remove(person4);

            session.getTransaction().commit();

            System.out.println(person2.getId()); // RETRIEVE VALUES AFTER SAVING
        }
    }
}
