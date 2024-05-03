package se.yrgo.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import se.yrgo.domain.Student;
import se.yrgo.domain.Tutor;

import java.util.List;

public class HibernateTest {

    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {

        SessionFactory sf = getSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        Tutor theTutor = new Tutor("ABC234", "Natalie Woodward", 387787 );
        Student student1 = new Student("Patrik Howard");
        Student student2 = new Student("Marie Sani");
        Student student3 = new Student("Tom Nikson");

        session.save(theTutor);
        session.save(student1);
        session.save(student2);
        session.save(student3);

        theTutor.addStudentToTeachingGroup(student1);
        theTutor.addStudentToTeachingGroup(student2);
        theTutor.addStudentToTeachingGroup(student3);

        Tutor myTutor = session.get(Tutor.class, 1);
        List<Student> students = myTutor.getTeachingGroup();
        for(Student s: students) {
            System.out.println(s);
        }

        tx.commit();
        session.close();
    }

    private static SessionFactory getSessionFactory() {
        if(sessionFactory ==null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
