package com.bitclass.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long id;

    @NonNull
    private String name;
    private String description;
    private String assistantName, assistantEmail;
    private int places;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Student> students = new HashSet<>();

    @ManyToOne()
    private Professor professor;

//    public Long getProfessorId() {
//        return professorId;
//    }
//
//    public void setProfessorId(Long professorId) {
//        this.professorId = professorId;
//    }
//
//    private Long professorId;

    public Subject(String name, String description, int places) {
        this.name = name;
        this.description = description;
        this.places = places;
//        this.professorId = professorId;
    }

    public Subject() {

    }
    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getAssistantEmail() {
        return assistantEmail;
    }

    public void setAssistantEmail(String assistantEmail) {
        this.assistantEmail = assistantEmail;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
