package com.example.productsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productsservice.entites.Product;
import com.example.productsservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        // TODO Auto-generated method stub
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
            return productRepository.findAll();        
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO Auto-generated method stub
        productRepository.deleteById(id);
    }
    
}
