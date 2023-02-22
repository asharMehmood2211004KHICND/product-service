package com.example.productsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productsservice.entites.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    
}
