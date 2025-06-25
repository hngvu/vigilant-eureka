package com.orchidservice.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private String orchidId; // Unique identifier for the order
    private Integer price;
    private Integer quantity;
    private String orchidName;// Reference to the orchid being ordered

}
