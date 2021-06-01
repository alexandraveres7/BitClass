package com.bitclass.model.dto;

import com.bitclass.model.Professor;

public class ProfessorDTO {
    private String name;

    public ProfessorDTO(String name) {
        this.name = name;
    }

    public ProfessorDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
