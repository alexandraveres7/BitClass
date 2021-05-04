package com.ale.bitclass.controller;

import com.ale.bitclass.model.Subject;
import com.ale.bitclass.repos.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1")
public class SubjectController {
    private final Logger log = LoggerFactory.getLogger(SubjectController.class);

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/subjects")
    Collection<Subject> subjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/subject/{id}")
    ResponseEntity<?> getSubject(@PathVariable Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
//    @GetMapping("/subject/{id}/students")
//    Collection<Student> getSubjectStudents(@PathVariable Long id) {
//        Optional<Subject> subject = subjectRepository.findById(id);
//        log.info("Request to show students of subject {}", subject);
//        System.out.println("AICI");
//        if (subject.isPresent()) {
//            Set<Student> students = subject.get().getStudents();
//            System.out.println("Studenti" + students);
//            if (students.size() < 1) {
//                System.out.println("no enrolled students");
//            }
//            return students;
//        }
//        return Collections.emptySet();
//    }

    @PostMapping("/subject")
    ResponseEntity<Subject> createSubject(@RequestBody Subject subject) throws URISyntaxException {
        log.info("Request to create Uni subject: {}", subject);
        Subject result = subjectRepository.save(subject);
        return ResponseEntity.created(new URI("/v1/subject/" + result.getId())).body(result);
    }

    @PutMapping("subject/{id}")
    ResponseEntity<Subject> updateSubject(@RequestBody Subject subject){
        log.info("Request to update Uni subject: {}", subject);
        Subject result = subjectRepository.save(subject);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        log.info("Request to delete group: {}", id);
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
