package com.github.learning_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.learning_spring.entity.Department;
import com.github.learning_spring.errors.DepartmentNotFound;
import com.github.learning_spring.service.DepartmentService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    private final Logger LOGGER = Logger.getLogger(DepartmentController.class.getName());
    
    @PostMapping("/saveDepartment")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/getAllDepartments")
    public List<Department> getAllDepartments() {
        LOGGER.info("Getting all departments");
        return departmentService.getAllDepartments();
    }

    @GetMapping("/getDepartmentById/{id}")
    public Department getDepartmentById(@PathVariable String id) throws DepartmentNotFound {
        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/updateDepartment/{id}")
    public String updateDepartmentById(@PathVariable("id") Long departmentId,@Valid @RequestBody Department entity) {
        return departmentService.updateDepartment(departmentId, entity);
    }
    
    @DeleteMapping("/deleteDepartment/{id}")
    public String deleteMethodName(@Valid @PathVariable String id) {
        
        return departmentService.deleteDepartment(id);
    }

    @GetMapping("/getDepartmentByName/{name}")
    public Department getDepartmentByName(@PathVariable String name) throws DepartmentNotFound{
        return departmentService.getDepartmentByName(name);
    }


    
}
