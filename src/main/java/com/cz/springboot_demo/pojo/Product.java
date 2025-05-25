package com.cz.springboot_demo.pojo;

import jakarta.persistence.*;

// Since 2025/5/25 by CZ
@Table(name = "tb_product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long productId;
    @Column(name = "product_name")
    String productName;
    @Column(name = "product_description")
    String productDescription;
    @Column(name = "product_price")
    double productPrice;
    @Column(name = "product_stock")
    Long productStock;
    @Column(name = "product_isOnSale")
    Boolean productIsOnSale;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @Column(name = "product_cover_image_url")
    String productCoverImageUrl;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Category getProductCategory() {
        return category;
    }

    public void setProductCategory(Category category) {
        this.category = category;
    }

    public String getProductCoverImageUrl() {
        return productCoverImageUrl;
    }

    public void setProductCoverImageUrl(String productCoverImageUrl) {
        this.productCoverImageUrl = productCoverImageUrl;
    }
}
