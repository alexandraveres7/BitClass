package com.bitclass.controller;

import com.bitclass.model.Professor;
import com.bitclass.model.Student;
import com.bitclass.model.Subject;
import com.bitclass.model.dto.ProfessorDTO;
import com.bitclass.model.dto.SubjectDTO;
import com.bitclass.repos.ProfessorRepository;
import com.bitclass.repos.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1")
public class SubjectController {
    private final Logger log = LoggerFactory.getLogger(SubjectController.class);

    private final SubjectRepository subjectRepository;

    private final ProfessorRepository professorRepository;

    public SubjectController(SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")
    @GetMapping("/subjects")
    Collection<SubjectDTO> subjects() {
        List<Subject> subjects = subjectRepository.findAll();
        ModelMapper modelMapp = new ModelMapper();
        List<SubjectDTO> subjectsDTOList = new ArrayList<>();
        for (Subject subj: subjects){
            String name = subj.getProfessor().getName();
            ProfessorDTO professorDTO = new ProfessorDTO(name);
            SubjectDTO subDTO = modelMapp.map(subj, SubjectDTO.class);
            subDTO.setProfessor(professorDTO);
            subjectsDTOList.add(subDTO);
        }
        System.out.println(subjectsDTOList);
        return subjectsDTOList;
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @GetMapping("subjects/{professor_id}")
    Collection<SubjectDTO> getProfessorsSubjects(@PathVariable Long professor_id) {
         Optional<Professor> professor = this.professorRepository.findById(professor_id);
         if (professor.isPresent()) {
             Set<Subject> subjects = subjectRepository.findAllByProfessor(professor.get());
             Set<SubjectDTO> subjectsDTOs = new HashSet<>();
             ModelMapper modelMapper = new ModelMapper();
             modelMapper.addMappings(subjectDTOPropertyMap);
             for (Subject subj: subjects){
                 SubjectDTO subjectDTO = modelMapper.map(subj, SubjectDTO.class);
                 subjectsDTOs.add(subjectDTO);
             }
             System.out.println(subjectsDTOs);
             return subjectsDTOs;
         }
         else{
             return Collections.emptyList();
         }
    }

    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_PROFESSOR')")
    @GetMapping("/subject/{id}")
    ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        Long professorID = subject.get().getProfessor().getId();
        if (subject.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.addMappings(subjectDTOPropertyMap);
            SubjectDTO subjectDTO = modelMapper.map(subject.get(), SubjectDTO.class);
            subjectDTO.setProfessorid(professorID);
            return ResponseEntity.ok().body(subjectDTO);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    ResponseEntity<String> createSubject(@RequestBody SubjectDTO subjectDTO) throws URISyntaxException {
        log.info("Request to create Uni subject: {}", subjectDTO);
        Long id = subjectDTO.getProfessorid();
        Optional<Professor> professor = this.professorRepository.findById(id);
        if (professor.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.addMappings(subjectDTOPropertyMap);
            Subject subject = modelMapper.map(subjectDTO, Subject.class);
            subject.setProfessor(professor.get());
            //professor.get().addTeachedSubject(subject);
            //this.professorRepository.save(professor.get());
            subjectRepository.save(subject);
            return ResponseEntity.ok().body(subject.getName()+ " successuflly created!");
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    PropertyMap<Subject, SubjectDTO> subjectDTOPropertyMap = new PropertyMap<>(){
        @Override
        protected void configure() {
            skip(destination.getProfessorid(), destination.getProfessor());
        }
    };

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @PutMapping("subject/{id}")
    ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO){
        log.info("Request to update Uni subject: {}", subjectDTO);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(subjectDTOPropertyMap);
        Long id = subjectDTO.getProfessorid();
        Optional<Professor> prof = this.professorRepository.findById(id);
        Subject updatedSubject = modelMapper.map(subjectDTO, Subject.class);
        updatedSubject.setProfessor(prof.get());
        subjectRepository.save(updatedSubject);
        return ResponseEntity.ok().body(subjectDTO);
    }

    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    @DeleteMapping("/subject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        log.info("Request to delete subject: {}", id);
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
