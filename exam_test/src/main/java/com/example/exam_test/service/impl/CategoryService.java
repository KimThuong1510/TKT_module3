package com.example.exam_test.service.impl;

import com.example.exam_test.model.Category;
import com.example.exam_test.model.Product;
import com.example.exam_test.repository.ICategoryRepository;
import com.example.exam_test.repository.IProductRepository;
import com.example.exam_test.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepos;

    @Autowired
    private IProductRepository iProductRepos;

    @Override
    public List<Category> findALl() {
        return iCategoryRepos.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepos.findById(id);
    }

    @Override
    public void saveCat(Category category) {
        iCategoryRepos.save(category);
    }

    @Override
    public void deleteCat(Long id) throws Exception {
        if(iCategoryRepos.existsById(id)){
            throw new Exception("Không thể xóa mục này");
        }
        iCategoryRepos.deleteById(id);
    }
}
