package com.bitclass;

import com.bitclass.repos.GradesReportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bitclass.repos.StudentRepository;


@Component
public
class Initializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final GradesReportRepository gradesReportRepository;


    public Initializer(StudentRepository studentRepository, GradesReportRepository gradesReportRepository) {
        this.studentRepository = studentRepository;
        this.gradesReportRepository = gradesReportRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public GradesReportRepository getGradesReportRepository() {
        return gradesReportRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("App running");
    }
}
