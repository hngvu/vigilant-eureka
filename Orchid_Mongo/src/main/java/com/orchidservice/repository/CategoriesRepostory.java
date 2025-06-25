package com.orchidservice.repository;

import com.orchidservice.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriesRepostory extends MongoRepository<Categories, String> {
    // Define any custom query methods if needed
}
