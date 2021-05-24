package com.bitclass.dto;

import com.bitclass.model.Student;
import com.bitclass.model.dto.StudentDTO;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.modelmapper.ModelMapper;


public class StudentDtoUnitTest {
    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void convertStudentEntityToStudentDto() {
        String name = RandomString.make(5);
        String username = RandomString.make(6);

        Student student = new Student();
        student.setId(3L);
        student.setName(name);
        student.setUsername(username);

        StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
        Assertions.assertEquals(student.getId(), studentDTO.getId());
        Assertions.assertEquals(student.getName(), studentDTO.getName());
        Assertions.assertEquals(student.getUsername(), studentDTO.getUsername());
    }

    @Test
    public void convertStudentDTOtoStudentEntity() {
        String name = RandomString.make(5);
        String username = RandomString.make(6);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(3L);
        studentDTO.setName(name);
        studentDTO.setUsername(username);

        Student student = modelMapper.map(studentDTO, Student.class);
        Assertions.assertEquals(studentDTO.getId(), student.getId());
        Assertions.assertEquals(studentDTO.getName(), student.getName());
        Assertions.assertEquals(studentDTO.getUsername(), student.getUsername());
    }

}
