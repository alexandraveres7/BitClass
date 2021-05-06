package com.bitclass.model;

import javax.persistence.*;
import java.util.*;

@Table(name="student")
@Entity
public class Student extends User{

    //    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "students_subjects",
//            joinColumns = {
//                    @JoinColumn(name = "student_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "course_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)})

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Subject> subjects = new HashSet<>();

    public Student(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student() {

    }

    public Set<Subject> getSubjects() {
        return subjects;
    }
}
