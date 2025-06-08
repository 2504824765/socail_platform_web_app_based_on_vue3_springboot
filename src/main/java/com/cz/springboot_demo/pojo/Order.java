package com.cz.springboot_demo.pojo;

import jakarta.persistence.*;

// Since 2025/5/21 by CZ
@Table(name = "tb_order")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 默认自增生成方式
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "order_name")
    private String orderName;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "good_name")
    private String goodName;
    @Column(name = "good_quantity")
    private Integer goodQuantity;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getGoodQuantity() {
        return goodQuantity;
    }

    public void setGoodQuantity(Integer goodQuantity) {
        this.goodQuantity = goodQuantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
