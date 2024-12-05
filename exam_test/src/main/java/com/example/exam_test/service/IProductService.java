package com.example.exam_test.service;
import com.example.exam_test.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    Page<Product> searchByName(String name, Pageable pageable);
    Page<Product> searchByPrice(double price, Pageable pageable);
    Page<Product> searchByCategory(Long id, Pageable pageable);
//    List<Product> findALl();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);
}
