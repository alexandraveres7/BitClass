package com.bitclass.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "grades_report")
@Data
public class GradesReport {
    @Id
    @Column(name = "grades_report_id")
    private Long gradesReportId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    // JsonBackReference needed to prevent infinite recursion.
    @JsonBackReference
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "grade")
    @Min(1)
    @Max(10)
    private Integer grade;

    public Long getGradesReportId() {
        return gradesReportId;
    }

    public Student getStudent() {
        return student;
    }

    public Subject getSubject() {
        return subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
