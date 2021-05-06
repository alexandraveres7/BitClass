package com.bitclass.model;

import javax.persistence.*;

@Table(name="professor")
@Entity
public class Professor extends User {

    public Professor(String name, String username, String email, String password) {
        super(name, username, email, password);
    }

    public Professor() {
    }
}
