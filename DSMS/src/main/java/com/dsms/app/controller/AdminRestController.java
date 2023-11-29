package com.dsms.app.controller;

import com.dsms.app.entity.Department;
import com.dsms.app.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/")
public class AdminRestController {

    @Autowired
    AdminService adminService;

    @GetMapping("/get_category_ids/{id}/")
    public ResponseEntity getCategories(@PathVariable("id") String departmentId) {
        Department department = adminService.getDepartmentById(departmentId);
        if(department.getCategories() == null) {
            return ResponseEntity.ok(Arrays.asList(Arrays.asList("NO DATA", "NO DEPARTMENTS")));
        }
        return ResponseEntity.ok(department.getCategories().stream().map(category -> Arrays.asList(category.getId(), category.getCategoryName())).collect(Collectors.toList()));
    }
}
