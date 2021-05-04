package com.ale.bitclass.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable{

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
