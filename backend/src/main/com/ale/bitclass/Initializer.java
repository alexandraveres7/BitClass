package com.ale.bitclass;

import com.ale.bitclass.repos.ProfessorRepository;
import com.ale.bitclass.repos.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class Initializer implements CommandLineRunner {
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    public Initializer(SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public void run(String... args) {
          System.out.println("App running");
//        Professor p = new Professor("Adina", "adina@gmail.com");
//        Subject s1 = new Subject("PCBE", "");
//        subjectRepository.save(s1);
//
//        Subject materie = subjectRepository.findByName("PCBE");
//        materie.setProfessor(p);
//
//        subjectRepository.save(materie);
//
//        subjectRepository.findAll().forEach(System.out::println);
    }
}
