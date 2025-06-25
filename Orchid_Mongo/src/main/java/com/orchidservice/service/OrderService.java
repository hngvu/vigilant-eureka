package com.orchidservice.service;

import com.orchidservice.dto.OrderDTO;
import com.orchidservice.dto.OrderDetailDTO;
import com.orchidservice.entity.Orders;

import java.util.List;

public interface OrderService {
    public Orders saveOrder( List<OrderDetailDTO> orderDetailDTO);
    public void updateOrder(String orderId, Orders order);
    public void deleteOrder(String orderId);
    public Orders getOrder(String orderId);
    List<OrderDTO> findByAccount();

}
