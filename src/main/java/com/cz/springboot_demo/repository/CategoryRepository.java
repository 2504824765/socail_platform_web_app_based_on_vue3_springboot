package com.cz.springboot_demo.repository;

import com.cz.springboot_demo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
    Optional<Category> findByCategoryId(Long categoryId);
    List<Category> findByCategoryLevel(Integer categoryLevel);
    List<Category> findByCategoryParentId(Long parentId);
}
