package com.dsms.app.entity;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class Item {

    @MongoId
    Integer itemId;

    String itemName;

    String itemDescription;

    String itemUrl;

    Float itemPrice;
}
