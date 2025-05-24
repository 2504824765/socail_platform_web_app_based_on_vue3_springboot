package com.cz.springboot_demo.pojo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

// Since 2025/5/21 by CZ
public class OrderEditDTO {
    @NotBlank
    private Double totalAmount;
    @NotBlank
    private String orderStatus;
    @NotBlank
    private String paymentMethod;
    @NotBlank
    private String createTime;
    @NotBlank
    private String goodName;
    @NotBlank
    private Integer goodQuantity;

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
