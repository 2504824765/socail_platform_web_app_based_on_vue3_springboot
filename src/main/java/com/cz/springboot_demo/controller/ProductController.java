package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.pojo.dto.ProductCreateDTO;
import com.cz.springboot_demo.pojo.dto.ProductEditDTO;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import com.cz.springboot_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Since 2025/5/25 by CZ
@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseMessage addProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseMessage.success("Add product successfully", productService.addProduct(productCreateDTO));
    }

    @DeleteMapping("/{productId}")
    public ResponseMessage deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseMessage.success("Delete product successfully");
    }

    @PutMapping()
    public ResponseMessage updateProduct(@RequestBody ProductEditDTO productEditDTO) {
        return ResponseMessage.success("Update product successfully", productService.updateProduct(productEditDTO));
    }

    @GetMapping("/byProductId/{productId}")
    public ResponseMessage getProduct(@PathVariable Long productId) {
        return ResponseMessage.success("Get product by productId successfully", productService.getProductByProductId(productId));
    }

    @GetMapping("/byProductName/{productName}")
    public ResponseMessage getProductByName(@PathVariable String productName) {
        return ResponseMessage.success("Get product by productName successfully", productService.getProductByProductName(productName));
    }
}
