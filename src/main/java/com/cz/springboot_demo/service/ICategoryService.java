package com.cz.springboot_demo.service;

import com.cz.springboot_demo.pojo.Category;
import com.cz.springboot_demo.pojo.dto.CategoryCreateDTO;
import com.cz.springboot_demo.pojo.dto.CategoryEditDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService {
    Category addCategory(CategoryCreateDTO categoryCreateDTO);

    void deleteCategory(Long categoryId);

    Category updateCategory(CategoryEditDTO categoryEditDTO);

    Category getCategoryByCategoryId(Long categoryId);

    Category getCategoryByCategoryName(String categoryName);
}
