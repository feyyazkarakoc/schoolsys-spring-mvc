package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.service.IStudentService;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students") //http:localhost:8080/StudentManagementSystem/students/...
public class StudentController {


    private IStudentService service;

    @Autowired
    public StudentController(IStudentService service) {
        this.service = service;
    }


    //http:localhost:8080/StudentManagementSystem/students/hi + GET
    @GetMapping("/hi")
    public ModelAndView sayHi() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hi");
        mav.addObject("messagebody", "I am a Student Management System");
        mav.setViewName("hi");
        return mav;
    }


    //http:localhost:8080/StudentManagementSystem/students + GET
    @GetMapping
    public ModelAndView getStudents() {
        List<Student> allStudents = service.listAllStudents();
        ModelAndView mav = new ModelAndView();
        mav.addObject("studentList", allStudents);
        mav.setViewName("students");
        return mav;
    }


    //http://localhost:8080/StudentManagementSystem/students/new + GET
    @GetMapping("/new")
    private String sendForm(@ModelAttribute("student") Student student) {
        return "studentForm";
    }


    //http://localhost:8080/StudentManagementSystem/students/saveStudent + POST
    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "studentForm";
        }
        service.addOrUpdateStudent(student);
        return "redirect:/students";//http:localhost:8080/StudentManagementSystem/students + GET
    }


    //http://localhost:8080/StudentManagementSystem/students/update?id=1 + GET
    @GetMapping("update")
    public ModelAndView sendForForUpdate(@RequestParam("id") Long identity) {
        Student foundStudent = service.findStudentById(identity);
        ModelAndView mav = new ModelAndView();
        mav.addObject("student", foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }


    //http://localhost:8080/StudentManagementSystem/students/delete/1
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long identity) {
        service.deleteStudent(identity);
        return "redirect:/students";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.setViewName("notFound");
        return mav;

    }


}
