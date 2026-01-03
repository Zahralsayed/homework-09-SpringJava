package com.ga.spring_hw.repository;

import com.ga.spring_hw.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(String categoryName);
    List<Category> findAll();

    Optional<Category> findById(Long id) ;
    void deleteById(Long id);
}
