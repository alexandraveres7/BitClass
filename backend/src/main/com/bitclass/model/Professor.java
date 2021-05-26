package com.bitclass.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="professors")
public class Professor extends User {

    public Professor(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    public Professor() {
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Subject> teachedSubjects = new HashSet<>();

    public Set<Subject> getTeachedSubjects() {
        return teachedSubjects;
    }

    public void setTeachedSubjects(Set<Subject> teachedSubjects) {
        this.teachedSubjects = teachedSubjects;
    }
}
