package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

public class Department {

    @MongoId
    Integer id;

    String departmentName;

    String departmentDescription;

    List<Category> categories;
}
