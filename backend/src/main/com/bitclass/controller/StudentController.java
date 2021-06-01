package com.bitclass.controller;

import com.bitclass.model.Lab;
import com.bitclass.model.Student;
import com.bitclass.model.Subject;
import com.bitclass.model.dto.StudentDTO;
import com.bitclass.model.dto.SubjectDTO;
import com.bitclass.repos.LabRepository;
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

    private final LabRepository labRepository;

    public StudentController(StudentRepository studentRepository, SubjectRepository subjectRepository, LabRepository labRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.labRepository = labRepository;
    }

    @GetMapping("/{id}/courses")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Collection<SubjectDTO> getStudentCourses(@PathVariable Long id) {
        log.info("Request to show courses the student is currently enrolled in");
        Optional<Student> student = this.studentRepository.findById(id);
        if (student.isPresent()) {
            Set<Subject> courses = student.get().getSubjects();
            if (courses.size() < 1) {
                log.info("Student has no enrolled courses" );
            }
            Set<SubjectDTO> coursesDTO = new HashSet<>();
            ModelMapper modelM = new ModelMapper();
            for (Subject s: courses){
                SubjectDTO sDTO = modelM.map(s, SubjectDTO.class);
                coursesDTO.add(sDTO);
            }
            return coursesDTO;
        }
        return Collections.emptySet();
    }

    @GetMapping("/{id}/courses_ids")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Collection<Long> getStudentCoursesIDs(@PathVariable Long id) {
        log.info("Request to get id's for courses the student is currently enrolled in");
        Optional<Student> student = this.studentRepository.findById(id);
        if (student.isPresent()) {
            Set<Subject> courses = student.get().getSubjects();
            if (courses.size() < 1) {
                log.info("Student has no enrolled courses" );
            }
            ArrayList<Long> ids = new ArrayList<>();
            for (Subject s: courses){
                ids.add(s.getId());
            }
            return ids;
        }
        return Collections.emptyList();
    }

    @GetMapping("/labs")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Collection<Lab> getAllLabsForCoursesStudentIsEnrolledIn(@RequestBody List<Long> subjectIDs){
        log.info("Request to get all labs available for courses student is enrolled in");
        Set<Lab> labs = new HashSet<>();

        for (Long id: subjectIDs){
          Lab lab = this.labRepository.findLabBySubjectId(id);
            labs.add(lab);
        }
        return labs;
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
              this.studentRepository.save(s);
//            this.subjectRepository.saveAll(subjects);
            ModelMapper modelMapper = new ModelMapper();
            StudentDTO studentDTO = modelMapper.map(s, StudentDTO.class);
            return ResponseEntity.ok().body(studentDTO);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/subjectsnr")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public int getNumberOfSubjectsStudentIsEnrolledIn(@PathVariable Long id){
        Optional<Student> student = this.studentRepository.findById(id);
        int number = student.get().getSubjects().size();

        return number;
    }
}
