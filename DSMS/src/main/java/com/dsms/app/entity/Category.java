package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class Category {

    @MongoId
    Integer id;

    String categoryName;

    String categoryDescription;
}
