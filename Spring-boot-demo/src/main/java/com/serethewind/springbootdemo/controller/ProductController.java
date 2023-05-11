package com.serethewind.springbootdemo.controller;

import com.serethewind.springbootdemo.dto.ProductRequest;
import com.serethewind.springbootdemo.entity.Product;
import com.serethewind.springbootdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductRequest>> getAllProducts(){
//        return new ResponseEntity<>(productService.fetchAll(), HttpStatus.OK);
        return ResponseEntity.of(Optional.ofNullable(productService.fetchAll()));
    }

    @PostMapping
    public ProductRequest saveProduct(@RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ProductRequest> findSingleProduct(@PathVariable("id") Long id){
//        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public Optional<Product> fetchProductById(@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @GetMapping("/name")
    public Product fetchProductByName(@RequestParam("name") String name){
        return productService.findByName(name);
    }


    @DeleteMapping("/{id}")
    public String deleteSingleProduct(@PathVariable("id") Long id){
        productService.deleteSingleProduct(id);
        return "Product deleted successfully.";
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRequest> updateSingleProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.updateSingleProduct(id, productRequest), HttpStatus.OK);
    }

    @GetMapping("names")
    public List<Product> findAllByName(@RequestParam("name") String name){
        return productService.findAllByName(name);
    }
//    @PostMapping
//    public ResponseEntity<Product> saveProducts(@RequestBody ProductRequest productRequest){
//        return new ResponseEntity<>(productService.addProduct(productRequest), HttpStatus.OK);
//    }

    @GetMapping("date")
    public ProductRequest findByDateCreated(@RequestParam("created") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date){
        return productService.findByCreatedAt(date);
    }
}
