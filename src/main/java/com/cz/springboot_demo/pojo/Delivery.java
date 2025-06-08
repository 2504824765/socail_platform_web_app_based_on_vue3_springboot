package com.cz.springboot_demo.pojo;

import jakarta.persistence.*;

// Since 2025/6/9 by CZ
@Table(name = "tb_delivery")
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @Column(name = "express_company")
    private String expressCompany;
    @Column(name = "shipped_time")
    private String shippedTime;
    @Column(name = "delivered_time")
    private String deliveredTime;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getShippedTime() {
        return shippedTime;
    }

    public void setShippedTime(String shippedTime) {
        this.shippedTime = shippedTime;
    }

    public String getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }
}
