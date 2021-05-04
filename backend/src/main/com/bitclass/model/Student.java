package com.bitclass.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student extends User{
    private static final Role role = Role.STUDENT;

    //    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "students_subjects",
//            joinColumns = {
//                    @JoinColumn(name = "student_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "course_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)})

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<GradesReport> gradesReports;

    //Set to be deleted after gradesReport implementation is finished
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

    public static Role getRole() {
        return role;
    }
}
