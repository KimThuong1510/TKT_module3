package com.example.exam_test.service;

import com.example.exam_test.model.Category;
import com.example.exam_test.model.Product;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findALl();
    Optional<Category> findById(Long id);
    void saveCat(Category category);
    void deleteCat(Long id) throws Exception;
}
