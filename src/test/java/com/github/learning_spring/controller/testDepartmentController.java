package com.github.learning_spring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.learning_spring.entity.Department;
import com.github.learning_spring.service.DepartmentService;
import java.util.Collections;

@ActiveProfiles("test")
@WebMvcTest(DepartmentController.class)
public class testDepartmentController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("CS")
                .departmentAddress("Bangalore")
                .departmentCode("IT-09")
                .departmentId(1L)
                .build();
    }

    @Test
    public void testSaveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("CS")
                .departmentAddress("Bangalore")
                .departmentCode("IT-09")
                .build();

        // Mock the service to return the saved department
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);

        // Perform the POST request and verify the result
        mockMvc.perform(MockMvcRequestBuilders.post("/saveDepartment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\": \"CS\",\n" +
                        "    \"departmentAddress\": \"Bangalore\",\n" +
                        "    \"departmentCode\": \"IT-09\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value("CS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentAddress").value("Bangalore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentCode").value("IT-09"));
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        // Mock the service to return a list of departments
        Mockito.when(departmentService.getAllDepartments()).thenReturn(Collections.singletonList(department));

        mockMvc.perform(MockMvcRequestBuilders.get("/getAllDepartments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName").value("CS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentAddress").value("Bangalore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentCode").value("IT-09"));
    }
}
