package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IRepository {
    List<Student> findAll();

    void saveOrUpdate(Student student);

    void deleteStudent(Student student);

    Optional<Student> findById(Long id);







}
