package com.cz.springboot_demo.service;

import com.cz.springboot_demo.pojo.Product;
import com.cz.springboot_demo.pojo.dto.ProductCreateDTO;
import com.cz.springboot_demo.pojo.dto.ProductEditDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    Product addProduct(ProductCreateDTO productCreateDTO);

    void deleteProduct(Long id);

    Product updateProduct(Long productId, ProductEditDTO productEditDTO);

    Product getProductByProductId(Long productId);

    Product getProductByProductName(String productName);

    Long getProductCount();

    List<Product> getAllProducts();
}
