package com.orchidservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "orchids")
public class Orchids {
    @Id
    private String id;
    private String orchidName;
    private boolean isNatural;
    private String orchidDescription;
    private String orchidUrl;
    private String bloomingSeason;
    private String origin;
    private String difficultyLevel;
    private Integer price;

    @DBRef
    private List<OrderDetails> orderDetailsList;

    @DBRef
    private Categories categories;
    // Additional methods or validations can be added here if needed
}