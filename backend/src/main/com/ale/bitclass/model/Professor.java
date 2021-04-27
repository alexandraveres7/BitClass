package com.ale.bitclass.model;

import lombok.Data;
import javax.persistence.Entity;

@Data
@Entity
public class Professor extends User {
    public Professor(String name, String email) {
        super(name, email);
    }

    public Professor() {
    }
}
