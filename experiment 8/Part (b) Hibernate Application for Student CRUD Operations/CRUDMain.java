package com.example.hibernate;

import org.hibernate.*;
import java.util.List;

public class CRUDMain {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // CREATE
        StudentEntity s1 = new StudentEntity("John", "Spring Boot");
        session.save(s1);

        // READ
        StudentEntity s2 = session.get(StudentEntity.class, s1.getId());
        System.out.println("Retrieved: " + s2.getName() + " - " + s2.getCourse());

        // UPDATE
        s2.setCourse("Hibernate ORM");
        session.update(s2);

        // DELETE
        session.delete(s2);

        tx.commit();
        session.close();
        factory.close();

        System.out.println("✅ CRUD Operations Completed Successfully");
    }
}
