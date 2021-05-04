package com.ale.bitclass.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "grades_report")
@Data
public class GradesReport {
    @Id
    @Column(name = "grades_report_id")
    private Long gradesReportId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    // JsonBackReference needed to prevent infinite recursion.
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "grades")
    private List<Integer> grade;
}
