package com.orchidservice.repository;

import com.orchidservice.entity.Orchids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrchidsRepository extends MongoRepository<Orchids, String> {
    // Define any custom query methods if needed
}
