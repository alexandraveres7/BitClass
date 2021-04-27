package com.ale.bitclass.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
//@Table(name = "subject")
public class Subject{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;
    private String description;
    private String assistantName, assistantEmail;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

//    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    private Professor professor = new Professor();

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Subject() {

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}