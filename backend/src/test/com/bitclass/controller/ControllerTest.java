package com.bitclass.controller;

import com.bitclass.model.Subject;
import com.bitclass.repos.SubjectRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {
        this.subjectRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "alexandra", roles = "STUDENT")
    @DisplayName("Get subjects list when there are no subjects available")
    public void testListSubjectsWhenThereAreNoSubjects() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/subjects"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @WithMockUser(username = "alexandra", roles = "STUDENT")
    @DisplayName("Get subjects list when there are subjects available")
    public void testListSubjectsWhenThereAreSubjects() throws Exception {
        Subject testSubject = new Subject("TestName", "TestDescription", 35);
        this.subjectRepository.save(testSubject);
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/subjects"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String actualResult = response.getResponse().getContentAsString();
        String expectedResult = "[{\"id\":1,\"name\":\"TestName\",\"description\":\"TestDescription\"," +
                "\"assistantName\":null,\"assistantEmail\":null,\"places\":35,\"students\":[],\"professor\":null}]";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @WithMockUser(username = "alexandra", roles = "PROFESSOR")
    @DisplayName("Get students enrolled in given subject when no students are enrolled")
    public void testGetStudentsEnrolledToSubjectWhenNoStudentsAreEnrolled() throws Exception{
        Subject testSubject = new Subject("TestName", "TestDescription", 35);
        this.subjectRepository.save(testSubject);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/subject/1/students"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResult = response.getResponse().getContentAsString();
        Assertions.assertEquals("[]", actualResult);
    }

}