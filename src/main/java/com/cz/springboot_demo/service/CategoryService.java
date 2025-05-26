package com.cz.springboot_demo.service;

import com.cz.springboot_demo.exception.CategoryAlreadyExistException;
import com.cz.springboot_demo.exception.CategoryNotFoundException;
import com.cz.springboot_demo.exception.ProductNotFoundException;
import com.cz.springboot_demo.pojo.Category;
import com.cz.springboot_demo.pojo.dto.CategoryCreateDTO;
import com.cz.springboot_demo.pojo.dto.CategoryEditDTO;
import com.cz.springboot_demo.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Since 2025/5/25 by CZ
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(CategoryCreateDTO categoryCreateDTO) {
        if (categoryRepository.findByCategoryName(categoryCreateDTO.getCategoryName()).isPresent()) {
            throw new CategoryAlreadyExistException("Category already exist");
        } else {
            Category category = new Category();
            BeanUtils.copyProperties(categoryCreateDTO, category);
            return categoryRepository.save(category);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if (categoryRepository.findById(categoryId).isPresent()) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new CategoryNotFoundException("Category not exist");
        }
    }

    @Override
    public Category updateCategory(CategoryEditDTO categoryEditDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryEditDTO.getCategoryName());
        if (!optionalCategory.isPresent()) {
            throw new ProductNotFoundException("Product Not Found");
        }

        Category category = optionalCategory.get();

        // ✅ 更新除 id 之外的属性（如果你不想覆盖某些字段，可以手动更新）
        BeanUtils.copyProperties(categoryEditDTO, category, "id");

        // ✅ 保存更新后的 product
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not exist"));
    }

    @Override
    public Category getCategoryByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).orElseThrow(() -> new CategoryNotFoundException("Category not exist"));
    }

    @Override
    public List<Category> getFirstClassCategory() {
        return categoryRepository.findByCategoryLevel(0);
    }

    @Override
    public List<Category> getSecondClassCategory() {
        return categoryRepository.findByCategoryLevel(1);
    }

    @Override
    public List<Category> getThirdClassCategory() {
        return categoryRepository.findByCategoryLevel(2);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
