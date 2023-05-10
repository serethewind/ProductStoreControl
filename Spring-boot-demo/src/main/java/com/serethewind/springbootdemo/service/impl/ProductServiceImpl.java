package com.serethewind.springbootdemo.service.impl;

import com.serethewind.springbootdemo.dto.ProductRequest;
import com.serethewind.springbootdemo.entity.Product;
import com.serethewind.springbootdemo.repository.ProductRepository;
import com.serethewind.springbootdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public ProductRequest addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setSkuCode(productRequest.getSkuCode());
        product.setDescription(productRequest.getDescription());

        Product savedProduct = productRepository.save(product);

        return mapToDto(savedProduct);
    }

    @Override
    public List<ProductRequest> fetchAll() {

       List<Product> allProducts = productRepository.findAll();

       List<ProductRequest> allProductRequests = new ArrayList<>();
        for (int i = 0; i < allProducts.size(); i++){
            ProductRequest productRequest = new ProductRequest();
            productRequest.setName(allProducts.get(i).getName());
            productRequest.setQuantity(allProducts.get(i).getQuantity());
            productRequest.setSkuCode(allProducts.get(i).getSkuCode());
            productRequest.setDescription(allProducts.get(i).getDescription());
            allProductRequests.add(productRequest);
        }
        return allProductRequests;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product findByName(String productName) {

        boolean isProductExist = productRepository.existsByName(productName);
        if (isProductExist){
            return productRepository.findByName(productName);
        }
        else {
            return null;
        }
//        return productRepository.findByName(productName).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByNameContains(name);
    }

//    @Override
//    public ProductRequest findById(Long id) {
//        Product product =  productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        return mapToDto(product);
//    }

    @Override
    public void deleteSingleProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductRequest updateSingleProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setSkuCode(productRequest.getSkuCode());
        product.setDescription(productRequest.getDescription());

        Product updatedProduct = productRepository.save(product);

        return mapToDto(updatedProduct);
    }

    private ProductRequest mapToDto(Product product){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(product.getName());
        productRequest.setQuantity(product.getQuantity());
        productRequest.setSkuCode(product.getSkuCode());
        productRequest.setDescription(product.getDescription());
        return productRequest;
    }
}
