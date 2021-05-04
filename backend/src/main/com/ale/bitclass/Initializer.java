package com.ale.bitclass;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ale.bitclass.model.Student;
import com.ale.bitclass.repos.StudentRepository;


@Component
class Initializer implements CommandLineRunner {

    private final StudentRepository studentRepository;


    public Initializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("App running");
        Student student = new Student("Alexandra Veres", "alexandraveres7@gmail.com", "10");

        studentRepository.save(student);
    }
}
