package com.orchidservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "orders")
public class Orders {
    @Id
    private String id;
    private LocalDate orderDate;
    private String orderStatus; // e.g., "Pending", "Shipped", "Delivered", etc.
    private Integer totalAmount;

    @DBRef
    private Accounts accounts;

    @DBRef
    private List<OrderDetails> orderDetailsList;

}
