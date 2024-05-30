package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements IStudentService {


    private IRepository repository;

    @Autowired
    public StudentService(IRepository repository) {
        this.repository = repository;
    }


    public List<Student> listAllStudents() {
        return repository.findAll();
    }

    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);

    }

    @Override
    public Student findStudentById(Long id) {
        Student foundStudent = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found by ID :" + id));
        return foundStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        repository.deleteStudent(student);

    }
}
