package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.pojo.Order;
import com.cz.springboot_demo.pojo.dto.OrderDTO;
import com.cz.springboot_demo.pojo.dto.OrderEditDTO;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import com.cz.springboot_demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Since 2025/5/21 by CZ
@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    @Operation(summary = "获取所有订单")
    public ResponseMessage getAllOrders() {
        return ResponseMessage.success("Get all Orders successfully", orderService.getAllOrder());
    }

    @GetMapping("/{order_id}")
    @Operation(summary = "通过order_id获取订单")
    public ResponseMessage getOrderById(@PathVariable("order_id") Long order_id) {
        return ResponseMessage.success("Get order by order_id successfully", orderService.getOderById(order_id));
    }

    @PostMapping
    @Operation(summary = "新增订单")
    public ResponseMessage addOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseMessage.success("Add order successfully", orderService.addOrder(orderDTO));
    }

    @PutMapping("/{order_id}")
    @Operation(summary = "修改订单信息")
    public ResponseMessage updateOrder(@PathVariable("order_id") Long order_id, @RequestBody OrderEditDTO orderEditDTO) {
        return ResponseMessage.success("Update order successfully", orderService.updateOrder(order_id, orderEditDTO));
    }

    @PutMapping("/{order_id}/status")
    @Operation(summary = "修改订单状态")
    public ResponseMessage updateOrderStatus(@PathVariable("order_id") Long order_id, @RequestBody String newStatus) {
        return ResponseMessage.success("Update order status successfully", orderService.updateOrderStatus(order_id, newStatus));
    }

    @DeleteMapping("/{order_id}")
    @Operation(summary = "删除订单")
    public ResponseMessage deleteOrder(@PathVariable("order_id") Long order_id) {
        orderService.deleteOrder(order_id);
        return ResponseMessage.success("Delete order successfully");
    }

    @GetMapping("/count")
    @Operation(summary = "获取订单数量")
    public ResponseMessage getOrderCount() {
        return ResponseMessage.success("Get order count successfully", orderService.getNumberOfOrder());
    }

    @GetMapping("/byOrderName/{orderName}")
    @Operation(summary = "通过订单号获得订单")
    public ResponseMessage getOrderByOrderName(@PathVariable("orderName") String orderName) {
        return ResponseMessage.success("Get order by order name successfully", orderService.getOrderByOrderName(orderName));
    }

}
