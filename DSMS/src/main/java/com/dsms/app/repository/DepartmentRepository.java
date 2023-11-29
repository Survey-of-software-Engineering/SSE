package com.dsms.app.repository;

import com.dsms.app.entity.Category;
import com.dsms.app.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, Integer> {

    @Query("{}")
    List<Department> getAllDepartments();

    Department getDepartmentByDepartmentName(String name);

    Department getDepartmentById(String id);

    Department getDepartmentByCategoriesContains(Category category);

}
