package com.example.exam_test.repository;

import com.example.exam_test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContaining(String name, Pageable pageable);
    Page<Product> findByPriceContaining(double price, Pageable pageable);

//    @Query("SELECT p FROM Product p WHERE p.category.name LIKE %:categoryName%")
    Page<Product> findByCategory_id(Long id, Pageable pageable);
}
