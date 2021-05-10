package com.bitclass.controller;

import com.bitclass.authentication.message.request.LoginDTO;
import com.bitclass.authentication.message.request.RegisterDTO;
import com.bitclass.authentication.security.jwt.JwtProvider;
import com.bitclass.authentication.security.services.UserInformation;
import com.bitclass.model.*;
import com.bitclass.repos.ProfessorRepository;
import com.bitclass.repos.StudentRepository;
import com.bitclass.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserInformation userDetails = (UserInformation) authentication.getPrincipal();

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", jwt);
        response.put("id", userDetails.getId().toString());
        response.put("username", userDetails.getUsername());
        response.put("email", userDetails.getEmail());
        response.put("role", userDetails.getAuthorities().toString());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        User user;
        try{
            signUpRequest.setRole();
        } catch (BadCredentialsException e){
            System.out.println("Set role error");
        }

        Role role = signUpRequest.getRole();
        RoleName roleName = role.getName();

        switch (roleName) {
            case ROLE_STUDENT:
                user = new Student(signUpRequest.getName(), signUpRequest.getUsername(),
                        signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
                user.setRole(role);
                studentRepository.save((Student) user);
                break;
            case ROLE_PROFESSOR:
                user = new Professor(signUpRequest.getName(), signUpRequest.getUsername(),
                        signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
                user.setRole(role);
                professorRepository.save((Professor) user);
                break;
            default:
                System.out.println("Unknown user role");
                throw new BadCredentialsException("User role invalid");
        }
        return ResponseEntity.ok().body("Registered successfully!");
    }

}
