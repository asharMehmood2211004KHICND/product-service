package com.example.productsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.productsservice.entites.Product;
import com.example.productsservice.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;




import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.hibernate.annotations.UpdateTimestamp;


import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;

import org.springframework.boot.test.json.JacksonTester;

import  org.springframework.http.MediaType;

import org.springframework.test.web.servlet.ResultActions;



@WebMvcTest
public class ProductControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postNewProduct() throws Exception{
        Product cut1 = Product.builder().id(1L)
		.name("myName")
		.shortDescription("myShortDescription")
		.longDescription("myLongDescription")
		.imageLink("myImageLink")
		.price("myPrice")
		.build();

        given(productService.saveProduct(any(Product.class))).willAnswer((invocation) -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(post("/product/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cut1)));
        response.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is(cut1.getName())));

    }

    @Test
    public void getAllTheProducts() throws Exception{
        Product cut1 = Product.builder().id(1L)
		.name("myName")
		.shortDescription("myShortDescription")
		.longDescription("myLongDescription")
		.imageLink("myImageLink")
		.price("myPrice")
		.build();
         
        Product cut2 = Product.builder().id(1L)
		.name("myName2")
		.shortDescription("myShortDescription2")
		.longDescription("myLongDescription2")
		.imageLink("myImageLink2")
		.price("myPrice2")
		.build();

        
        List<Product> products = List.of(cut1,cut2);
        given(productService.getAllProducts()).willReturn(products);

        ResultActions response = mockMvc.perform(get("/product/all"));
        
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(products.size())));
    }

    @Test
    public void getOneProductById() throws Exception{
       // Long thingId = 1L;

       Product product = Product.builder().id(1L)
       .name("myName")
       .shortDescription("myShortDescription")
       .longDescription("myLongDescription")
       .imageLink("myImageLink")
       .price("myPrice")
       .build();
     
      

        given(productService.getProductById(1L)).willReturn(product);

        ResultActions response = mockMvc.perform(get("/product/{id}", product.getId()));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(product.getName())));
                // .andExpect(jsonPath("$.description", is(thing.getDescription())));
    }


    @Test
    public void canDeleteAProduct() throws Exception{
        Long productId = 1L;
        willDoNothing().given(productService).deleteProduct(productId);

        ResultActions response = mockMvc.perform(delete("/product/delete/{id}", productId));

        response.andExpect(status().isOk()).andDo(print());
        verify(productService,times(1)).deleteProduct(productId);
    }

}
