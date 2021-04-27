package com.ale.bitclass.model;

import javax.persistence.Entity;

@Entity
public class Student extends User{
    public Student(String name, String grade) {
        super(name, grade);
    }

    public Student() {

    }
}
