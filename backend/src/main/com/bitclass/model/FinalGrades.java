package com.bitclass.model;


import java.util.ArrayList;
import java.util.List;
import com.bitclass.Initializer;

public class FinalGrades {
     private Initializer initiliazer;
     private List<GradesReport> gradesReportList;
     private ArrayList<Integer> grades = new ArrayList<>();

     public long getFinalGrade(Long subject_id, Long student_id){
        this.gradesReportList = this.initiliazer.getGradesReportRepository().findAll();
        for(GradesReport report: gradesReportList ){
            if (report.getStudent().getId().equals(student_id) && report.getSubject().getId().equals(subject_id)){
                this.grades.add(report.getGrade());
            }
        }
         return Math.round(this.calculateAverage(grades));
     }

    private double calculateAverage(List <Integer> grades) {
        Integer sum = 0;
        if(!grades.isEmpty()) {
            for (Integer grade : grades) {
                sum += grade;
            }
            return sum.doubleValue() / grades.size();
        }
        return sum;
    }
}
