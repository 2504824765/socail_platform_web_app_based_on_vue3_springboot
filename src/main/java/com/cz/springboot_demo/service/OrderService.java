package com.cz.springboot_demo.service;

import com.cz.springboot_demo.exception.OrderAlreadyExistException;
import com.cz.springboot_demo.exception.OrderNotFoundException;
import com.cz.springboot_demo.exception.UserNotFoundException;
import com.cz.springboot_demo.pojo.Order;
import com.cz.springboot_demo.pojo.dto.OrderDTO;
import com.cz.springboot_demo.pojo.dto.OrderEditDTO;
import com.cz.springboot_demo.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Since 2025/5/21 by CZ
@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order addOrder(OrderDTO oderDTO) {
        if (orderRepository.findByOrderName(oderDTO.getOrderName()).isPresent()) {
            throw new OrderAlreadyExistException("Order already exist");
        }
        Order order = new Order();
        BeanUtils.copyProperties(oderDTO, order);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long oder_id) {
        orderRepository.deleteById(oder_id);
    }

    @Override
    public Order updateOrder(Long order_id, OrderEditDTO orderEditDTO) {
        Optional<Order> optionalOder = orderRepository.findById(order_id);
        if (optionalOder.isPresent()) {
            Order existingOder = optionalOder.get();

            // 更新属性
            BeanUtils.copyProperties(orderEditDTO, existingOder, "id"); // 避免 ID 被覆盖

            return orderRepository.save(existingOder); // 此时有 ID，是更新操作
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

    @Override
    public Order getOderById(Long oder_id) {
        if (orderRepository.findById(oder_id).isPresent()) {
            return orderRepository.findById(oder_id).get();
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<>();
        orderRepository.findAll().forEach(orderList::add);
        return orderList;
    }

    @Override
    public Order updateOrderStatus(Long order_id, String newStatus) {
        Optional<Order> optionalOder = orderRepository.findById(order_id);
        if (optionalOder.isPresent()) {
            optionalOder.get().setOrderStatus(newStatus);
            Order existingOrder = optionalOder.get();
            BeanUtils.copyProperties(optionalOder, existingOrder, "id");
            return orderRepository.save(existingOrder);
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }

    @Override
    public Long getNumberOfOrder() {
        return orderRepository.count();
    }

    @Override
    public Order getOrderByOrderName(String orderName) {
        if (orderRepository.findByOrderName(orderName).isPresent()) {
            return orderRepository.findByOrderName(orderName).get();
        } else {
            throw new OrderNotFoundException("Order not found");
        }
    }
}
