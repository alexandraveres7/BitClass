package com.bitclass.repos;

import com.bitclass.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, Long> {
    Lab findLabById(Long id);

    Lab findLabBySubjectId(Long id);
}
