package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;

// Since 2025/6/9 by CZ
public class DeliveryUpdateDTO {
    @NotBlank
    private String expressCompany;
    private String shippedTime;
    private String deliveredTime;

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
