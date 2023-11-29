package com.dsms.app.repository;

import com.dsms.app.entity.Category;
import com.dsms.app.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, Integer> {

    @Query("{}")
    List<Item> getAllItems();

    Item getItemByItemId(String id);
}
