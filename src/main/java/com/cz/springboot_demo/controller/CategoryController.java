package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.pojo.dto.CategoryCreateDTO;
import com.cz.springboot_demo.pojo.dto.CategoryEditDTO;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import com.cz.springboot_demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Since 2025/5/25 by CZ
@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired private CategoryService categoryService;

    @PostMapping
    public ResponseMessage addCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        return ResponseMessage.success("Category added successfully", categoryService.addCategory(categoryCreateDTO));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseMessage deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseMessage.success("Category deleted successfully");
    }

    @PutMapping()
    public ResponseMessage updateCategory(@RequestBody CategoryEditDTO categoryEditDTO) {
        return ResponseMessage.success("Category updated successfully", categoryService.updateCategory(categoryEditDTO));
    }

    @GetMapping("/byCategoryId/{categoryId}")
    public ResponseMessage getCategoryByCategoryId(@PathVariable Long categoryId) {
        return ResponseMessage.success("Get category by categoryId successfully", categoryService.getCategoryByCategoryId(categoryId));
    }

    @GetMapping("/byCategoryName/{categoryName}")
    public ResponseMessage getCategoryByCategoryName(@PathVariable String categoryName) {
        return ResponseMessage.success("Get category by categoryName successfully", categoryService.getCategoryByCategoryName(categoryName));
    }
}
