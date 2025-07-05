package com.cz.springboot_demo.service;

import com.cz.springboot_demo.exception.CategoryNotFoundException;
import com.cz.springboot_demo.exception.ProductAlreadyExistException;
import com.cz.springboot_demo.exception.ProductNotFoundException;
import com.cz.springboot_demo.pojo.Category;
import com.cz.springboot_demo.pojo.Product;
import com.cz.springboot_demo.pojo.dto.ProductCreateDTO;
import com.cz.springboot_demo.pojo.dto.ProductEditDTO;
import com.cz.springboot_demo.repository.CategoryRepository;
import com.cz.springboot_demo.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Since 2025/5/25 by CZ
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @CacheEvict(value = {"products", "productCount", "product"}, allEntries = true)
    public Product addProduct(ProductCreateDTO productCreateDTO) {
        if (productRepository.findByProductName(productCreateDTO.getProductName()).isPresent()) {
            throw new ProductAlreadyExistException("Product already exist");
        } else {
            Product product = new Product();
            BeanUtils.copyProperties(productCreateDTO, product);
            if (!categoryRepository.findByCategoryId(productCreateDTO.getProductCategoryId()).isPresent()) {
                throw new CategoryNotFoundException("Category Not Found");
            } else {
                product.setProductCategory(categoryRepository.findByCategoryId(productCreateDTO.getProductCategoryId()).get());
            }
            return productRepository.save(product);
        }
    }

    @Override
    @CacheEvict(value = {"products", "productCount", "product"}, allEntries = true)
    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException("Product Not Found");
        }
    }

    @Override
    @CacheEvict(value = {"products", "productCount", "product"}, allEntries = true)
    public Product updateProduct(Long productId, ProductEditDTO productEditDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product Not Found");
        }

        Optional<Category> optionalCategory = categoryRepository.findByCategoryId(productEditDTO.getCategoryId());
        if (!optionalCategory.isPresent()) {
            throw new CategoryNotFoundException("Category Not Found");
        }

        // ✅ 获取已有的 product 实例
        Product product = optionalProduct.get();

        // ✅ 更新除 id 之外的属性（如果你不想覆盖某些字段，可以手动更新）
        BeanUtils.copyProperties(productEditDTO, product, "id");

        // ✅ 设置新分类
        product.setProductCategory(optionalCategory.get());

        // ✅ 保存更新后的 product
        return productRepository.save(product);
    }

    @Override
    @Cacheable(value = "product", key = "#productId")
    public Product getProductByProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    @Cacheable(value = "product", key = "'name:' + #productName")
    public Product getProductByProductName(String productName) {
        return productRepository.findByProductName(productName).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    @Cacheable(value = "productCount", key = "'count'")
    public Long getProductCount() {
        return productRepository.count();
    }

    @Override
    @Cacheable(value = "products", key = "'all'")
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return productList;
    }

}
