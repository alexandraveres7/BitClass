package com.bitclass.repos;

import com.bitclass.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor findByName(String name);

    Optional<Professor> findById(Long id);
}
