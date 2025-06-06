package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// Since 2025/5/25 by CZ
public class ProductCreateDTO {
    @NotBlank
    private String productName;
    @NotBlank
    private String productDescription;
    @NotBlank
    @Min(0)
    private Double productPrice;
    @NotBlank
    @Min(0)
    private Long productStock;
    @NotBlank
    private Long productCategoryId;
    @NotBlank
    private Boolean productIsOnSale;
    @NotBlank
    private String productCoverImageUrl;
    @NotBlank
    private double productScore;

    public double getProductScore() {
        return productScore;
    }

    public void setProductScore(double productScore) {
        this.productScore = productScore;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductStock() {
        return productStock;
    }

    public void setProductStock(Long productStock) {
        this.productStock = productStock;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Boolean getProductIsOnSale() {
        return productIsOnSale;
    }

    public void setProductIsOnSale(Boolean productIsOnSale) {
        this.productIsOnSale = productIsOnSale;
    }

    public String getProductCoverImageUrl() {
        return productCoverImageUrl;
    }

    public void setProductCoverImageUrl(String productCoverImageUrl) {
        this.productCoverImageUrl = productCoverImageUrl;
    }
}
