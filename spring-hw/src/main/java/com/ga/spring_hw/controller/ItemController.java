package com.ga.spring_hw.controller;

import com.ga.spring_hw.model.Item;
import com.ga.spring_hw.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/categories")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/{categoryId}/items")
    public Item createItem(@PathVariable Long categoryId, @RequestBody Item item) {
        System.out.println("Calling createItem ==>");
        return itemService.createItem(categoryId, item);
    }

    @GetMapping("/{categoryId}/items")
    public List<Item> getItems(@PathVariable Long categoryId) {
        System.out.println("Calling getItems ==>");
        return itemService.getAllItemsByCategoryId(categoryId);
    }

    @GetMapping("{categoryId}/items/{itemId}")
    public Optional<Item> getItem(@PathVariable Long categoryId, @PathVariable Long itemId){
        System.out.println("Calling getItem ==>");
        return itemService.getItem(categoryId, itemId);
    }

    @PostMapping("{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable Long categoryId, @PathVariable Long itemId ,@RequestBody Item item){
        System.out.println("Calling updateItem ==>");
        return itemService.updateItem(categoryId, itemId, item);
    }

    @DeleteMapping("{categoryId}/items/{itemId}")
    public void deleteItem(@PathVariable Long categoryId, @PathVariable Long itemId){
        System.out.println("Calling deleteItem ==>");
        itemService.deleteItem(categoryId, itemId);
    }

}
