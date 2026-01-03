package com.ga.spring_hw.repository;

import com.ga.spring_hw.model.Category;
import com.ga.spring_hw.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByName(String ItemName);
    void deleteById(Long id);
//    List<Item> findAllItemsByCategoryId(Long categoryId);
//    List<Item> findByCategory(Category category);
////
//    Optional<Item> findByCategoryAndItemId(Category category,Long id);
//    void deleteByCategoryAndItemId(Category category);
//

}
