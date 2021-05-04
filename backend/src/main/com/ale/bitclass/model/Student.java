package com.ale.bitclass.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Student extends com.ale.bitclass.model.User {
    private String grade;
    private static final Role role = Role.STUDENT;

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

    public Student(String name, String username, String email, String password, String grade) {
        super(name, username, email, password);
        this.grade = grade;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student() {

    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public static Role getRole() {
        return role;
    }
}
