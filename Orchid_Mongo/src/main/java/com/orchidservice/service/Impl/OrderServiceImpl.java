package com.orchidservice.service.Impl;

import com.orchidservice.dto.OrderDTO;
import com.orchidservice.dto.OrderDetailDTO;
import com.orchidservice.entity.Accounts;
import com.orchidservice.entity.Orchids;
import com.orchidservice.entity.OrderDetails;
import com.orchidservice.entity.Orders;
import com.orchidservice.repository.OrchidsRepository;
import com.orchidservice.repository.OrderDetailsRepository;
import com.orchidservice.repository.OrderRepository;
import com.orchidservice.service.JwtService;
import com.orchidservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrchidsRepository orchidsRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Override
    public Orders saveOrder(List<OrderDetailDTO> orderDetailDTOList) {
        Accounts accounts = jwtService.getCurrentAccount();

        Orders orders = new Orders();
        orders.setAccounts(accounts);
        orders.setOrderDate(LocalDate.now());
        orders.setOrderStatus("PENDING");
        orderRepository.save(orders);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        Integer totalAmount = 0;

        for (OrderDetailDTO dto : orderDetailDTOList) {
            // Tìm Orchid theo tên
            Orchids orchid = orchidsRepository.findById(dto.getOrchidId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy orchid với ID: " + dto.getOrchidId()));


            // Tạo OrderDetail từ DTO
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrchids(orchid);
            orderDetail.setOrders(orders); // Gán mối quan hệ 2 chiều
            orderDetail.setQuantity(dto.getQuantity());
            orderDetail.setPrice(dto.getPrice());
            orderDetailsRepository.save(orderDetail);
            totalAmount += dto.getPrice() * dto.getQuantity();
            orderDetailsList.add(orderDetail);

        }

        orders.setOrderDetailsList(orderDetailsList);
        orders.setTotalAmount(totalAmount);
        System.out.println(orders);
        return orderRepository.save(orders); // Cascade sẽ tự lưu orderDetails
    }




    @Override
    public void updateOrder(String orderId, Orders order) {

    }

    @Override
    public void deleteOrder(String orderId) {

    }

    @Override
    public Orders getOrder(String orderId) {
        return null;
    }

    @Override
    public List<OrderDTO> findByAccount() {
        Accounts acc = jwtService.getCurrentAccount();
        List<Orders> ordersList = orderRepository.findByAccounts(acc);
        return ordersList.stream()
                .map(this::convertToDto)
                .toList();
    }
    public OrderDTO convertToDto(Orders order) {
        OrderDTO dto = new OrderDTO();

        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setTotalAmount(order.getTotalAmount());

        if (order.getAccounts() != null) {
            dto.setAccountEmail(order.getAccounts().getEmail());
        }

        List<OrderDetailDTO> detailDtoList = new ArrayList<>();
        if (order.getOrderDetailsList() != null) {
            for (OrderDetails detail : order.getOrderDetailsList()) {
                if (detail == null) continue;

                OrderDetailDTO detailDto = new OrderDetailDTO();
                if (detail.getOrchids() != null) {
                    detailDto.setOrchidName(detail.getOrchids().getOrchidName());
                }
                detailDto.setQuantity(detail.getQuantity());
                detailDto.setPrice(detail.getPrice());

                detailDtoList.add(detailDto);
            }
        }
        dto.setOrderDetails(detailDtoList);

        return dto;
    }


}
