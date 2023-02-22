package com.example.productsservice.service;

import java.util.List;

import com.example.productsservice.entites.Product;

public interface ProductService {
    
    public Product saveProduct(Product product);

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public void deleteProduct(Long id);
}
