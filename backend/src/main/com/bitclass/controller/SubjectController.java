package com.bitclass.controller;

import com.bitclass.model.Student;
import com.bitclass.model.Subject;
import com.bitclass.repos.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1")
public class SubjectController {
    private final Logger log = LoggerFactory.getLogger(SubjectController.class);

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")
    @GetMapping("/subjects")
    Collection<Subject> subjects() {
        Subject subject1 = new Subject("PCBE", "Concurrent programming", 50);
        Subject subject2 = new Subject("SO", "Processes, threads, operating systems related..", 100);
        Subject subject3 = new Subject("VVS", "Unit and integration testing", 70);
        subjectRepository.deleteAll();
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        subjectRepository.save(subject3);
        return subjectRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")
    @GetMapping("/subject/{id}")
    ResponseEntity<?> getSubject(@PathVariable Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @GetMapping("/subject/{id}/students")
    Collection<Student> getSubjectStudents(@PathVariable Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        log.info("Request to show students of subject {}", subject);
        if (subject.isPresent()) {
            Set<Student> students = subject.get().getStudents();
            if (students.size() < 1) {
                System.out.println("no enrolled students");
            }
            return students;
        }
        return Collections.emptySet();
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @PostMapping("/subject")
    ResponseEntity<Subject> createSubject(@RequestBody Subject subject) throws URISyntaxException {
        log.info("Request to create Uni subject: {}", subject);
        Subject result = subjectRepository.save(subject);
        return ResponseEntity.created(new URI("/v1/subject/" + result.getId())).body(result);
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @PutMapping("subject/{id}")
    ResponseEntity<Subject> updateSubject(@RequestBody Subject subject){
        log.info("Request to update Uni subject: {}", subject);
        Subject result = subjectRepository.save(subject);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @DeleteMapping("/subject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        log.info("Request to delete group: {}", id);
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
