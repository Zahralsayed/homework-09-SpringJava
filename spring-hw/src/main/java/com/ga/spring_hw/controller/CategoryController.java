package com.ga.spring_hw.controller;

import com.ga.spring_hw.model.Category;
import com.ga.spring_hw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryRepository(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject){
        System.out.println("Calling createCategory ==>");
        return categoryService.createCategory(categoryObject);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        System.out.println("Calling getAllCategories ==>");
        return categoryService.getCategories();
    }

    @GetMapping("/categories/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable Long categoryId){
        System.out.println("Calling getCategoryById ==>");
        return categoryService.getCategory(categoryId);
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject){
        System.out.println("Calling updateCategory ==>");
        return categoryService.updateCategory(categoryId, categoryObject);
    }

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId){
        System.out.println("Calling deleteCategory ==>");
        return categoryService.deleteCategory(categoryId);
    }


}
