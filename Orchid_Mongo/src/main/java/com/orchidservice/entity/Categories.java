package com.orchidservice.entity;

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
@Document(collection = "categories")
public class Categories {
    @Id
    private String id;
    private String categoryName;

    @DBRef
    private List<Orchids> orchidsList;
    // Additional methods or validations can be added here if needed
}
