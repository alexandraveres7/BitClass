package com.bitclass.model;

import javax.persistence.*;

@Entity
public class Professor extends User {
    private static final Role role = Role.PROFESSOR;

    public Professor(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    public Professor() {
    }
}
