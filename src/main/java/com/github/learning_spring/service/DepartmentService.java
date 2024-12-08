package com.github.learning_spring.service;

import java.util.List;

import com.github.learning_spring.entity.Department;
import com.github.learning_spring.errors.DepartmentNotFound;

public interface DepartmentService {

    public String saveDepartment(Department department);

    public List<Department> getAllDepartments();

    public String updateDepartment(Long id, Department entity);

    public Department getDepartmentById(String id) throws DepartmentNotFound;

    public String deleteDepartment(String id);

    public Department getDepartmentByName(String name) throws DepartmentNotFound;


}
