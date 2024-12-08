package com.github.learning_spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.learning_spring.entity.Department;

@DataJpaTest
public class TestDepartmentRepository {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Long departmentId;

    @BeforeEach
    void setUp() {
        // Create and persist a new Department
        Department department = Department.builder()
                .departmentName("ME")
                .departmentAddress("sdasdsad")
                .departmentCode("IT-06")
                .build();

        // Persist and get the generated ID
        departmentId = entityManager.persistAndGetId(department, Long.class);
    }

    @Test
    public void testFindByDepartmentId() {
        // Find the department by its ID
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));

        // Assert that the department name is "ME"
        assertEquals("ME", department.getDepartmentName());
    }
}
