package com.example.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finance.Student;
import com.example.finance.StudentDao;

@RestController
public class MainController {


    // Endpoint for the home page
    @GetMapping("/")
    public String home() {
        return "Welcome to the Spring Boot Application!";
    }

    // Example of another endpoint
    @GetMapping("/greeting")
    public String getGreeting() {
        return "Hello, World!";
    }
    
    @GetMapping("/createStudent")
    public String createUserNew() {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("Student1", "Student2", "student@javaguides.com");
        studentDao.saveStudent(student);
        return "success";
    }


    // You can add more endpoints here
}
