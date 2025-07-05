package com.cz.springboot_demo.pojo;

import jakarta.persistence.*;
import java.io.Serializable;

// Since 2025/5/25 by CZ
@Table(name = "tb_category")
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Long categoryId;
    @Column(name = "category_name")
    String categoryName;
    @Column(name = "category_parent_id")
    Long categoryParentId;
    @Column(name = "category_level")
    Integer categoryLevel;
    @Column(name = "category_sort_order")
    Integer categorySortOrder;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategorySortOrder() {
        return categorySortOrder;
    }

    public void setCategorySortOrder(Integer categorySortOrder) {
        this.categorySortOrder = categorySortOrder;
    }
}
