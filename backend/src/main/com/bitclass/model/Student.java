package com.bitclass.model;

import javax.persistence.*;
import java.util.*;

@Table(name="student")
@Entity
public class Student extends User{
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Set<Subject> subjects = new HashSet<>();

    public Student(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    public boolean checkSubject(Subject subject){
        boolean isPresent = false;
        for(Subject sbj: this.subjects){
            if (sbj.getName().equals(subject.getName())) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public void addSubject(Subject subject){
        if(!this.checkSubject(subject)){
            this.subjects.add(subject);
        }
        else{
            throw new EntityExistsException();
        }
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
