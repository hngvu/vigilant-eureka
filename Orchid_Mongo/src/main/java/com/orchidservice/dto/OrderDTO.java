package com.orchidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;
    private LocalDate orderDate;
    private String orderStatus;
    private Integer totalAmount;
    private String accountEmail;
    private List<OrderDetailDTO> orderDetails;
}
