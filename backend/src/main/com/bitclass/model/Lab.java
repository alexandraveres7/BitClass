package com.bitclass.model;

import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Table(name="labs")
public class Lab implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int startHour, endHour;

    @OneToOne
    private Subject subject;

    private String day;

    public Lab(@NonNull Subject subject,  @NonNull int startHour, @NonNull int endHour) {
        this.name = subject.getName() + " Laboratory";
        this.subject = subject;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = setDate();
    }

    public Lab() {

    }

    public String getDay() {
        return day;
    }

    public String setDate() {
        LocalDate date = LocalDate.now();
        Locale locale = new Locale.Builder().setLanguage("ro").setRegion("RO").build();
        DayOfWeek day = date.getDayOfWeek();
        return day.getDisplayName(TextStyle.FULL, locale);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
