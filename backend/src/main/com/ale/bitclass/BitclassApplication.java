package com.ale.bitclass;

import com.ale.bitclass.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ale.bitclass.repos.StudentRepository;

@SpringBootApplication()
public class BitclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitclassApplication.class, args);
    }
    @Bean
    public CommandLineRunner mappingDemo(StudentRepository studentRepository) {
        return args -> {
            System.out.println("it's all good");

        };
    }

}
