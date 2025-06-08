package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Since 2025/6/9 by CZ
public class DeliveryCreateDTO {
    @NotNull
    private Long orderId;
    @NotBlank
    private String expressCompany;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }
}
