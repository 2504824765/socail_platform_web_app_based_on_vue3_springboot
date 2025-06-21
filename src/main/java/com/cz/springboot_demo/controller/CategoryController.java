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

    @PutMapping("/{categoryId}")
    public ResponseMessage updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryEditDTO categoryEditDTO) {
        System.err.println("updateCategory");
        return ResponseMessage.success("Category updated successfully", categoryService.updateCategory(categoryId, categoryEditDTO));
    }

    @GetMapping("/byCategoryId/{categoryId}")
    public ResponseMessage getCategoryByCategoryId(@PathVariable Long categoryId) {
        return ResponseMessage.success("Get category by categoryId successfully", categoryService.getCategoryByCategoryId(categoryId));
    }

    @GetMapping("/byCategoryName/{categoryName}")
    public ResponseMessage getCategoryByCategoryName(@PathVariable String categoryName) {
        return ResponseMessage.success("Get category by categoryName successfully", categoryService.getCategoryByCategoryName(categoryName));
    }

    @GetMapping("/firstClass")
    public ResponseMessage getFirstClassCategory() {
        return ResponseMessage.success("Get first class category successfully", categoryService.getFirstClassCategory());
    }

    @GetMapping("/secondClass")
    public ResponseMessage getSecondClassCategory() {
        return ResponseMessage.success("Get second class category successfully", categoryService.getSecondClassCategory());
    }

    @GetMapping("/thirdClass")
    public ResponseMessage getThirdClassCategory() {
        return ResponseMessage.success("Get third class category successfully", categoryService.getThirdClassCategory());
    }

    @GetMapping("/all")
    public ResponseMessage getAllCategory() {
        return ResponseMessage.success("Get all category successfully", categoryService.getAllCategories());
    }

    @GetMapping("/getChildById/{categoryId}")
    public ResponseMessage getChildById(@PathVariable Long categoryId) {
        return ResponseMessage.success("Get child of category \"" + categoryId.toString() + "\" successfully", categoryService.getChildById(categoryId));
    }
}
