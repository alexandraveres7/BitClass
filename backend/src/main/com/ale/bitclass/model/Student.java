package com.ale.bitclass.model;

import javax.persistence.*;

@Entity
//@Table(catalog = "students")
public class Student extends com.ale.bitclass.model.User {
    private String grade;
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "students_subjects",
//            joinColumns = {
//                    @JoinColumn(name = "student_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "course_id", referencedColumnName = "id",
//                            nullable = false, updatable = false)})
//    private Set<Subject> subjects = new HashSet<>();

    public Student(String name, String email, String grade) {
        super(name, email);
        this.grade = grade;
    }

//    public Set<Subject> getSubjects() {
//        return subjects;
//    }

    public Student() {

    }
}
