package com.ga.spring_hw.service;

import com.ga.spring_hw.model.Category;
import com.ga.spring_hw.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category categoryObject){
        System.out.println("Service Calling createCategory ==>");

        Category category = categoryRepository.findByName(categoryObject.getName());

        if (category != null){
            throw new RuntimeException("Category with name " +category.getName() + " already exist.");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }


    public List<Category> getCategories(){
        System.out.println("Service Calling getCategories ==>");
        return  categoryRepository.findAll();
    }

    public Optional<Category> getCategory(Long categoryId){
        System.out.println("Service Calling getCategory ==>");
        Optional<Category> category =  categoryRepository.findById(categoryId);
        if (category.isPresent()){
            return category;
        }
        else  {
            throw new RuntimeException("Category with id " +categoryId + " not found.");
        }
    }

    public Category updateCategory(Long categoryId, Category categoryObject){
        System.out.println("Service Calling updateCategory ==>");
        Optional<Category> category =  categoryRepository.findById(categoryId);
        if (category.isPresent()){
            if (categoryObject.getName().equals(category.get().getName())) {
                System.out.println("Same");
                throw new RuntimeException("category " + category.get().getName() + " is already exists");
            } else {
                Category updateCategory = categoryRepository.findById(categoryId).get();
                updateCategory.setName(categoryObject.getName());
                updateCategory.setDescription(categoryObject.getDescription());
                updateCategory.setUpdatedAt(LocalDateTime.now());
                return categoryRepository.save(updateCategory);
            }
        } else {
            throw new RuntimeException("category with id " + categoryId + " not found");
        }
    }

    public String deleteCategory(Long categoryId){
        System.out.println("Service Calling deleteCategory ==>");
        Optional<Category> category =  categoryRepository.findById(categoryId);
        if (category.isPresent()){
            categoryRepository.deleteById(categoryId);
            return "Category with id " + categoryId + " deleted.";
        } else  {
            throw new RuntimeException("category with id " + categoryId + " not found");
        }
    }

}

