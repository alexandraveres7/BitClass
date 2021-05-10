package com.bitclass.authentication.message.request;
import com.bitclass.model.Role;
import com.bitclass.model.RoleName;
import org.springframework.security.authentication.BadCredentialsException;


import javax.validation.constraints.*;
public class RegisterDTO {
    @NotBlank
    @Size(min=3, max=40)
    private String name;

    @NotBlank
    @Size(min=3, max=20)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    private Role role;

    @NotBlank
    @Size(min=8, max = 50)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Role getRole() {
        return this.role;
    }

    public void setRole() {
        if (this.email.endsWith("@student.upt.ro")){
            this.role = new Role(RoleName.ROLE_STUDENT);
        }
        else if (this.email.endsWith("@cs.upt.ro")){
            this.role = new Role(RoleName.ROLE_PROFESSOR);
        }
        else throw new BadCredentialsException("Invalid email domain!");
    }
}
