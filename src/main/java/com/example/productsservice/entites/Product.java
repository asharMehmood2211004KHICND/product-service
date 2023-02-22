package com.example.productsservice.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ITEM #
// NAME
// SHORT DESCRIPTION
// LONG DESCRIPTION
// IMAGE LINK
// PRICE

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String longDescription;
    @Column(nullable = false)
    private String imageLink;
    @Column(nullable = false)
    private String price;                   

}
