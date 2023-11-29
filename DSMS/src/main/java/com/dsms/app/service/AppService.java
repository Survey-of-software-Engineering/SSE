package com.dsms.app.service;

import com.dsms.app.entity.Category;
import com.dsms.app.entity.Department;
import com.dsms.app.models.DepartmentsResponse;
import com.dsms.app.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<DepartmentsResponse> getDepartmentsResponse() {
        List<Department> departments = departmentRepository.getAllDepartments();
        return departments.stream().map(
                department -> new DepartmentsResponse(department, department.getCategories().stream().map(Category::getItems).flatMap(List::stream).collect(Collectors.toList()))).collect(Collectors.toList());
    }
}
