package com.bitclass.repos;

import com.bitclass.model.GradesReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesReportRepository extends JpaRepository<GradesReport, Long> {
    GradesReport findByGradesReportId(Long id);
}
