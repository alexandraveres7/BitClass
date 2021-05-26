package com.bitclass.repos;

import com.bitclass.model.Professor;
import com.bitclass.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);

    Optional<Subject> findById(Long id);

    Set<Subject> findByNameIn(List<String> subjectsNames);

    Set<Subject> findAllByProfessor(Professor professor);
}
