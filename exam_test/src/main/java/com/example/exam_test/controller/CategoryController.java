package com.example.exam_test.controller;

import com.example.exam_test.model.Category;
import com.example.exam_test.model.Product;
import com.example.exam_test.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("")
    public String showListCategory(Model model){
        model.addAttribute("list", iCategoryService.findALl());
        return "/category/list_cat";
    }
    @GetMapping("show-form-create-cat")
    public String showFormCreate(Model model){
        model.addAttribute("categories", new Category());
        return "/category/create_cat";
    }
    @PostMapping("add-category")
    public String addCategory(@ModelAttribute("categories") Category category){
        iCategoryService.saveCat(category);
        return "redirect:/category";
    }

    @PostMapping("delete-cat/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Category> existCategory = iCategoryService.findById(id);
            if (existCategory.isPresent()) {
                iCategoryService.deleteCat(id);
                redirectAttributes.addFlashAttribute("del", "Xóa thành công.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/category";
    }
}
