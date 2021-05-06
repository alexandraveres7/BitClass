package com.bitclass.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class TestAuthApiController {
    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public String studentAccess() {
        return ">>> Student Contents!";
    }

    @GetMapping("/professor")
    @PreAuthorize("hasRole('PROFESSOR')")
    public String professorAccess() {
        return ">>> Professor/admin contents";
    }
}
