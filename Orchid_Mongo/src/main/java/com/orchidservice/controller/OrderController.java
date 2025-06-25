package com.orchidservice.controller;

import com.orchidservice.dto.OrderDTO;
import com.orchidservice.dto.OrderDetailDTO;
import com.orchidservice.dto.response.ApiResponse;
import com.orchidservice.entity.Orders;
import com.orchidservice.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Orders>>createOrder(@RequestBody List<OrderDetailDTO> orderDetailDTO) {
        Orders order = orderService.saveOrder(orderDetailDTO);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Order created successfully",
                200,
                order
        ));
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrderByUser(){
        List<OrderDTO> ordersList = orderService.findByAccount();
        return ResponseEntity.ok(ApiResponse.builder()
                        .status(true)
                        .code(200)
                        .message("List Order Success")
                        .result(ordersList)
                        .build());
    }
}
