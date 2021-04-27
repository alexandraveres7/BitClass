package com.ale.bitclass.repos;

import com.ale.bitclass.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
