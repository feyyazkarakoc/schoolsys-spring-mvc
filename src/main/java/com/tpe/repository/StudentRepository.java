package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Repository
public class StudentRepository implements IRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        List<Student> allList = session.createQuery("FROM Student", Student.class).getResultList();
        session.close();
        return allList;
    }

    @Override
    public void saveOrUpdate(Student student) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(student);
        tx.commit();
        session.close();

    }

    @Override
    public void deleteStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(student);
        tx.commit();
        session.close();

    }

    @Override
    public Optional<Student> findById(Long id) {
        Session session = sessionFactory.openSession();
        Student student = session.get(Student.class, id);
        Optional<Student> optional = Optional.ofNullable(student);
        session.close();
        return optional;
    }
}
