package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class Department {

    @MongoId
    Integer id;

    String departmentName;

    String departmentDescription;
}
