package com.orchidservice.repository;

import com.orchidservice.entity.Accounts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Accounts, String> {
    Accounts findByAccountName(String username);
    Optional<Accounts> findByEmail(String email);

}
