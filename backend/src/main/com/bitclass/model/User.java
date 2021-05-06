package com.bitclass.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @Size(min=3, max=40)
    private String name;

    @NotBlank
    @Column(unique = true)
    @Size(min=3, max=20)
    private String username;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @NotBlank
    @Email
    @Size(max = 40)
    private String email;

    @NotBlank
    @Size(min=8, max = 50)
    private String password;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
