package com.bitclass.repos;

import com.bitclass.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);

    Optional<Subject> findById(Long id);
}
