package com.serethewind.springbootdemo.service;

import com.serethewind.springbootdemo.dto.ProductRequest;
import com.serethewind.springbootdemo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    ProductRequest addProduct(ProductRequest productRequest);

    List<ProductRequest> fetchAll();

    Optional<Product> findById(Long id);

    Product findByName (String productName);

    List<Product> findAllByName(String name);

    void deleteSingleProduct(Long id);

    ProductRequest updateSingleProduct(Long id, ProductRequest productRequest);



}
