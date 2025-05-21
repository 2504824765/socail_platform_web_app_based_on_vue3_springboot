package com.cz.springboot_demo.service;

import com.cz.springboot_demo.pojo.Order;
import com.cz.springboot_demo.pojo.dto.OrderDTO;
import com.cz.springboot_demo.pojo.dto.OrderEditDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {
    Order addOrder(OrderDTO orderDTO);

    void deleteOrder(Long oder_id);

    Order updateOrder(Long order_id, OrderEditDTO orderEditDTO);

    Order getOderById(Long oder_id);

    List<Order> getAllOrder();

    Order updateOrderStatus(Long order_id, String newStatus);

    Long getNumberOfOrder();

    Order getOrderByOrderName(String order_name);
}
