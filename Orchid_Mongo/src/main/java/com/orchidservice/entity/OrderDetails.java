package com.orchidservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "order_details")
public class OrderDetails {
    @Id
    private String id;
    private Integer quantity;
    private Integer price;
    @DBRef
    private Orchids orchids;// List of orchids in the order details
    @DBRef
    private Orders orders; // Reference to the associated order
    // Additional methods or validations can be added here if needed
}
