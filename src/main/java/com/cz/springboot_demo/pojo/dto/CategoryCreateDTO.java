package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;

// Since 2025/5/25 by CZ
public class CategoryCreateDTO {
    @NotBlank
    String categoryName;
    @NotBlank
    Long categoryParentId;
    @NotBlank
    Integer categoryLevel;
    @NotBlank
    Integer categorySortOrder;

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
