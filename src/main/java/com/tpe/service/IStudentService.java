package com.tpe.service;

import com.tpe.domain.Student;

import java.util.List;

public interface IStudentService {

    List<Student> listAllStudents();

    void addOrUpdateStudent(Student student);

    Student  findStudentById(Long id);

    void deleteStudent(Long id);
}
