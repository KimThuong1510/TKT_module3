package com.example.exam_test.service.impl;

import com.example.exam_test.model.Product;
import com.example.exam_test.repository.IProductRepository;
import com.example.exam_test.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository iProductRepository;


//    @Override
//    public List<Product> findALl() {
//        return iProductRepository.findAll();
//    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchByName(String name, Pageable pageable) {
        return iProductRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Product> searchByPrice(double price, Pageable pageable) {
        return iProductRepository.findByPriceContaining(price, pageable);
    }

    @Override
    public Page<Product> searchByCategory(Long id, Pageable pageable) {
        return iProductRepository.findByCategory_id(id, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        iProductRepository.deleteById(id);
    }
}
