package com.github.learning_spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.github.learning_spring.entity.Department;
import com.github.learning_spring.errors.DepartmentNotFound;
import com.github.learning_spring.repository.DepartmentRepository;

@SpringBootTest
public class TestDepartmentService {

    @Autowired
    private DepartmentService departmentService;

    @MockitoBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();

        Mockito.when(departmentRepository.findByDepartmentName("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void testGetDepartmentByName() throws DepartmentNotFound {
        String departmentName = "IT";
        Department found = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());

    }

    @Test
    @DisplayName("Get Data based on Invalid Department Name")
    public void testGetDepartmentByNameNotFound() {
        String departmentName = "CE";
        assertThrows(DepartmentNotFound.class, () -> {
            departmentService.getDepartmentByName(departmentName);
        });
    }


}
