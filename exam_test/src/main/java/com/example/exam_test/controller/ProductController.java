package com.example.exam_test.controller;

import com.example.exam_test.model.Product;
import com.example.exam_test.service.ICategoryService;
import com.example.exam_test.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;


    @GetMapping("")
    public String showListCategory( @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "5") int size,
                                    @RequestParam(value = "search", required = false) String search,
                                    @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                    @RequestParam(value = "categoryId", required = false) Long categoryId,
                                    Model model)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        if (search != null && !search.isEmpty()) {
            productPage = iProductService.searchByName(search, pageable);
        } else if (maxPrice != null) {
            productPage = iProductService.searchByPrice(maxPrice, pageable);
        } else if (categoryId != null ) {
            productPage = iProductService.searchByCategory(categoryId, pageable);
        } else {
            productPage = iProductService.findAll(pageable);
        }

        model.addAttribute("list", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("categories", categoryId);
        model.addAttribute("categories", iCategoryService.findALl());
        return "/product/product-list";
    }

    // Tạo danh sách
    @GetMapping("show-create")
    public String showCreate(Model model){
        model.addAttribute("productC", new Product());
        model.addAttribute("listC", iCategoryService.findALl());
        return "/product/create-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("productC") Product product, RedirectAttributes redirectAttributes) {
        iProductService.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm sản phẩm thành công.");
        return "redirect:/";
    }

    @GetMapping("show-form-edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Optional<Product> existingProduct = iProductService.findById(id);
        if (existingProduct.isPresent()) {
            model.addAttribute("productE", existingProduct.get());
            model.addAttribute("listE", iCategoryService.findALl());
        }
        return "/product/update-product";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("productE") Product product, RedirectAttributes redirectAttributes) {
        Optional<Product> existingProduct = iProductService.findById(id);
        if (existingProduct.isPresent()) {
            iProductService.save(product);
            redirectAttributes.addFlashAttribute("edit", "Cập nhật sản phẩm thành công");
        }
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Product> existProduct = iProductService.findById(id);
        if (existProduct.isPresent()) {
            iProductService.delete(id);
            redirectAttributes.addFlashAttribute("del", "Xóa sản phẩm thành công.");
        }
        return "redirect:/";
    }
}
