package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

public class Category {

    @MongoId
    Integer id;

    String categoryName;

    String categoryDescription;

    List<Item> items;
}
