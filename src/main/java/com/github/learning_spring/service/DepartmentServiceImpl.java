package com.github.learning_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.learning_spring.entity.Department;
import com.github.learning_spring.errors.DepartmentNotFound;
import com.github.learning_spring.repository.DepartmentRepository;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    public Department saveDepartment(Department department) {
        return  departmentRepository.save(department);

    }
    

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(String id) throws DepartmentNotFound {
        Optional<Department> department = departmentRepository.findById(Long.parseLong(id));
        if(department.isPresent())
            return department.get();
        else
            throw new  DepartmentNotFound("Department not found");
    }


    @Override
    public String updateDepartment(Long id, Department entity) {
        Department department = departmentRepository.findById(id).get();
        if(department == null) {
            return "Department not found";
        }
        System.out.println(entity.getDepartmentName());
        if(entity.getDepartmentName() != null && !entity.getDepartmentName().isEmpty())
            department.setDepartmentName(entity.getDepartmentName());
        if(entity.getDepartmentAddress() != null && !entity.getDepartmentAddress().isEmpty())
            department.setDepartmentAddress(entity.getDepartmentAddress());
        if(entity.getDepartmentCode() != null && !entity.getDepartmentCode().isEmpty())
            department.setDepartmentCode(entity.getDepartmentCode());

        departmentRepository.save(department);
        return "Department updated";
    }

    @Override
    public String deleteDepartment(String id) {
        departmentRepository.deleteById(Long.parseLong(id));
        return "Department deleted";
    }

    @Override
    public Department getDepartmentByName(String name) throws DepartmentNotFound {
        Optional<Department> department = Optional.ofNullable(departmentRepository.findByDepartmentName(name));
        
        if(department.isPresent())
            return department.get();
        else
            throw new DepartmentNotFound("Department not found");
    }

}
