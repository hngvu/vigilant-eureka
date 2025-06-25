package com.orchidservice.repository;

import com.orchidservice.entity.Accounts;
import com.orchidservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Orders, String> {
    List<Orders> findByAccounts(Accounts accounts);
}
