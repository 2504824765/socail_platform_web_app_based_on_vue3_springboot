package com.cz.springboot_demo.pojo.dto;

import com.cz.springboot_demo.pojo.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// Since 2025/5/25 by CZ
public class ProductEditDTO {
    @NotBlank
    String productName;
    @NotBlank
    String productDescription;
    @NotBlank
    @Min(0)
    double productPrice;
    @NotBlank
    @Min(0)
    Long productStock;
    @NotBlank
    Boolean productIsOnSale;
    @NotBlank
    Long categoryId;
    @NotBlank
    String productCoverImageUrl;
    @NotBlank
    double productScore;

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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductStock() {
        return productStock;
    }

    public void setProductStock(Long productStock) {
        this.productStock = productStock;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
