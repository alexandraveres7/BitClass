package com.ale.bitclass.repos;

import com.ale.bitclass.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByName(String name);
}
