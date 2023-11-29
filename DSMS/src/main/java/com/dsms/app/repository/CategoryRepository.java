package com.dsms.app.repository;

import com.dsms.app.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {

    @Query("{}")
    List<Category> getAllCategories();

    Category getCategoryById(String id);
}
