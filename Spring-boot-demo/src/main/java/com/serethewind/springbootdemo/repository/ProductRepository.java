package com.serethewind.springbootdemo.repository;

import com.serethewind.springbootdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    Optional<Product> findById(Long id);
    Product findByName(String name);

    List<Product> findAllByNameContains(String name);

    boolean existsByName( String productName);
}
