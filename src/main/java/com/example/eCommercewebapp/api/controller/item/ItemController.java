package com.example.eCommercewebapp.api.controller.item;

import com.example.eCommercewebapp.api.model.EditItemBody;
import com.example.eCommercewebapp.api.model.ItemBody;
import com.example.eCommercewebapp.api.model.ItemSavedBody;
import com.example.eCommercewebapp.exception.ItemAlreadyExistsException;
import com.example.eCommercewebapp.model.Item;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin( "http://localhost:5173")
public class ItemController {

    private final ItemService itemService;

    public  ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/createItem")
    public ResponseEntity<Item> createItem(@AuthenticationPrincipal User user, @Valid @RequestBody ItemBody itemBody){
        System.out.println(user.getRole());
        if (user.getRole().equalsIgnoreCase("admin")){
            try {

                Item item =itemService.createItem(itemBody);

                return ResponseEntity.status(HttpStatus.OK).body(item);

                }
            catch(ItemAlreadyExistsException ex){
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }

        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @GetMapping("/categeory/{categoryName}")
    public List<Item> getCatageoryList(@PathVariable String categoryName){
        return itemService.getCatageoryList(categoryName);
    }

    @GetMapping("/getAllItems")
    public List<Item> getAllItems(){

        return itemService.getItems();
    }

    @PutMapping("/createSavedItem")
    public Item createSavedItem(@AuthenticationPrincipal User user,@Valid @RequestBody ItemSavedBody itemSavedBody){
        return itemService.createUserSavedItem(user, itemSavedBody);
    }


    @PutMapping("/deleteSavedItem")
    public Item deleteSavedItem(@AuthenticationPrincipal User user,@Valid @RequestBody ItemSavedBody itemSavedBody){
        return itemService.deleteUserSavedItem(user, itemSavedBody);
    }

    @DeleteMapping("/deleteItem/{id}")
    public void deleteItem(@AuthenticationPrincipal User user, @PathVariable Long id) {
        System.out.println(user.getRole()+"jiii");
        if ("admin".equals(user.getRole())) {
           Item item =itemService.deleteItem(id);
        }


    }

    @PutMapping("/editItem")
    public Item editItem(@AuthenticationPrincipal User user,@Valid @RequestBody EditItemBody edititemBody){
         if ("admin".equals(user.getRole())) {
             return itemService.editItem(edititemBody);}

         return null;
    }

}

