package com.orchidservice.repository;

import com.orchidservice.entity.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Roles, String> {
    Roles findByRoleName(String roleName);
}
