package com.serethewind.springbootdemo.repository;

import com.serethewind.springbootdemo.dto.ProductRequest;
import com.serethewind.springbootdemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //    Optional<Product> findById(Long id);
    @Query("select p from Product p where p.name = ?1")
    Product findByName(String name);

    List<Product> findAllByNameContains(String name);

    boolean existsByName(String productName);

    Product findByCreatedAt(LocalDateTime date);

    boolean existsByCreatedAt(LocalDateTime date);

}
