package com.bitclass.controller;

import com.bitclass.model.Student;
import com.bitclass.model.Subject;
import com.bitclass.model.dto.StudentDTO;
import com.bitclass.repos.StudentRepository;
import com.bitclass.repos.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1/student")
public class StudentController {
    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public StudentController(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/{id}/courses")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Collection<Subject> getStudentCourses(@PathVariable Long id) {
        log.info("Request to show courses the student is currently enrolled in");
        Optional<Student> student = this.studentRepository.findById(id);
        if (student.isPresent()) {
            Set<Subject> courses = student.get().getSubjects();
//            Subject subject = new Subject("VVS", "Unit & integration testing", 50);
//            Professor professor = new Professor("Adina Rolea", "adinarolea", "adinarolea@gmail.com", "password");
//            subject.setProfessor(professor);
//            courses.add(subject);
            if (courses.size() < 1) {
                log.info("Student has no enrolled courses" );
            }
            return courses;
        }
        return Collections.emptySet();
    }

    @PutMapping("/{id}/enroll")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<?> enrollStudentInCourses(@PathVariable Long id, @RequestBody List<String> subjectsNames){
        log.info("Request to enroll student in courses");

        if (subjectsNames.size() > 4){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Student> student = this.studentRepository.findById(id);
        Set<Subject> subjects = this.subjectRepository.findByNameIn(subjectsNames);

        if (student.isPresent()) {
            Student s = student.get();
            s.setSubjects(subjects);
            for(Subject subject: subjects){
                subject.addStudent(s);
            }
            ModelMapper modelMapper = new ModelMapper();
            StudentDTO studentDTO = modelMapper.map(s, StudentDTO.class);
            return ResponseEntity.ok().body(studentDTO);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
