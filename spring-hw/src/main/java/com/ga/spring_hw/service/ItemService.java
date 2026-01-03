package com.ga.spring_hw.service;

import com.ga.spring_hw.model.Category;
import com.ga.spring_hw.model.Item;
import com.ga.spring_hw.repository.CategoryRepository;
import com.ga.spring_hw.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Item createItem(Long categoryId, Item itemObject){
        System.out.println("Service Calling createItem ==>");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " not found"));

        itemObject.setCategory(category);
        return itemRepository.save(itemObject);
    }



    public List<Item> getAllItemsByCategoryId(Long categoryId){
        System.out.println("Service Calling getAllItemsByCategoryId ==>");
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category with id " + categoryId + " not found"));

        List<Item> items = category.getItemList();
        if(items != null) {
            return items;
        } else {
            throw new RuntimeException("No items found for this category");
        }
    }

//    public Optional<Item> getItemByIdAndCategoryId(Long categoryId, Long itemId){
//        System.out.println("Service Calling getItemByIdAndCategoryId ==>");
//
//
//
//    }

    public Optional<Item> getItem(Long categoryId, Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(!category.isPresent()) {
            throw new RuntimeException("Category with id " + categoryId + " not found");
        } else
        if (item.isPresent()) {
            if (!item.get().getCategory().getId().equals(categoryId)) {
                throw new RuntimeException("Item " + itemId + " does not belong to Category " + categoryId);
            }
            else return item;
        } else {
            throw new RuntimeException("Item with id " + itemId + " not found");
        }
    }

    public String deleteItem(Long categoryId, Long itemId) {
        System.out.println("Service Calling deleteItem ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Item> item = itemRepository.findById(itemId);
        if(!category.isPresent()) {
            throw new RuntimeException("Category with id " + categoryId + " not found");
        } else if(!item.get().getCategory().getId().equals(categoryId)) {
            throw new RuntimeException("Item " + itemId + " does not belong to Category " + categoryId);
        } else {
            itemRepository.deleteById(itemId);
            return "Item with id " + itemId + " has been deleted";
        }
    }

    public Item updateItem(Long categoryId, Long itemId, Item itemObject){
        System.out.println("Service Calling updateItem ==>");
        Optional<Category> category = categoryRepository.findById(categoryId);
        Optional<Item> item = itemRepository.findById(itemId);

        if(!category.isPresent()) {
            throw new RuntimeException("Category with id " + categoryId + " not found");
        }
        if (!item.isPresent()){
            throw new RuntimeException("Item with id " + itemId + " not found");
        }
        if(!item.get().getCategory().getId().equals(categoryId)) {
            throw new RuntimeException("Item " + itemId + " does not belong to Category " + categoryId);
        }

        if(itemObject.getName().equals(item.get().getName())){
            System.out.println("Same");
            throw new RuntimeException("Item with name " + itemObject.getName() + " already exists");
        } else {
            Item updatedItem = itemRepository.findById(itemId).get();
            updatedItem.setName(itemObject.getName());
            updatedItem.setDescription(itemObject.getDescription());
            updatedItem.setDueDate(itemObject.getDueDate());
            return itemRepository.save(updatedItem);
        }

    }

}
