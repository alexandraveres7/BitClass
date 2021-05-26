package com.bitclass.model.dto;


public class SubjectDTO {
    private Long id;
    private String name;
    private String description;
    private String assistantName, assistantEmail;
    private int places;
    private Long professorid;

    public Long getId() {
        return id;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public String getAssistantEmail() {
        return assistantEmail;
    }

    public void setAssistantEmail(String assistantEmail) {
        this.assistantEmail = assistantEmail;
    }

    public Long getProfessorid(){
        return this.professorid;
    }

    public void setProfessorid(Long professorid){
        this.professorid = professorid;
    }
}
