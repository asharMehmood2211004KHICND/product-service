package com.example.productsservice.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productsservice.entites.Product;
import com.example.productsservice.repository.ProductRepository;
import com.example.productsservice.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // @Autowired
    // private ProductRepository productRepository;


    @PostMapping("/save")
    public Product saveProduct(@RequestBody Product product ){

    // try{
    //     System.out.println("save hotel");
    //     Product savedProduct = productService.saveProduct(product);
    //     return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);

    // }catch(Exception e){
    //     return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    // }
        
    // }
            return productService.saveProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long hotelId) {
        try {
            productService.deleteProduct(hotelId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/all") 
        public ResponseEntity<List<Product>> getAllProducts(){
            try{
                List<Product> allProducts = productService.getAllProducts();
                return new ResponseEntity<>(allProducts,HttpStatus.OK);

            }catch(Exception e){
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        try{
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
