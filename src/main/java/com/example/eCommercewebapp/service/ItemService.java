package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.EditItemBody;
import com.example.eCommercewebapp.api.model.ItemBody;
import com.example.eCommercewebapp.api.model.ItemSavedBody;
import com.example.eCommercewebapp.exception.ItemAlreadyExistsException;
import com.example.eCommercewebapp.model.FileData;
import com.example.eCommercewebapp.model.Item;
import com.example.eCommercewebapp.model.User;
import com.example.eCommercewebapp.model.dao.FileDataDAO;
import com.example.eCommercewebapp.model.dao.ItemDAO;
import com.example.eCommercewebapp.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemService {

    private final ItemDAO itemDAO;
    private final UserDAO userDAO;
    private  final FileDataDAO fileDataDAO;
    @Autowired
    public ItemService(ItemDAO itemDAO, UserDAO userDAO, FileDataDAO fileDataDAO) {
        this.itemDAO = itemDAO;
        this.userDAO = userDAO;
        this.fileDataDAO = fileDataDAO;
    }

    public List<Item> getItems(){
        return itemDAO.findAll();
    }

    public Item createItem(ItemBody itemBody) throws ItemAlreadyExistsException {
       if (itemDAO.findByNameAndBrand(itemBody.getName(), itemBody.getBrand()).isPresent()) {
            throw new ItemAlreadyExistsException();
        }
       Item item = new Item();
       item.setName(itemBody.getName());
       item.setBrand(itemBody.getBrand());
       item.setCategory(itemBody.getCategory());
       item.setDetails(itemBody.getDetails());
       item.setDiscount(itemBody.getDiscount());
       item.setPrice(itemBody.getPrice());
       item.setQuantity(itemBody.getQuantity());
       itemDAO.save(item);
       return item;
    }

     public List<Item> getCatageoryList( String category ){
       Optional<List<Item>> optionalItems =  itemDAO.findByCategory(category);
       if (optionalItems != null){
           List<Item> categoryList = optionalItems.get();
           return categoryList;
       }
       else{
           return null;
       }
    }
public Item createUserSavedItem(User user, ItemSavedBody itemSavedBody) {
        User exsistingUser = userDAO.findById(user.getId()).get();
    Optional<Item> optionalItem = itemDAO.findById(itemSavedBody.getId());

    if (optionalItem.isPresent()) {
        Item itemToUpdate = optionalItem.get();
        Set<User> userSet = itemToUpdate.getSavedByUsers();
        Set<Item> itemSet = exsistingUser.getSavedItems();
        itemSet.add(itemToUpdate);
        userSet.add(exsistingUser);
        exsistingUser.setSavedItems(itemSet);
        userDAO.save(exsistingUser);

        itemToUpdate.setSavedByUsers(userSet);
        itemDAO.save(itemToUpdate);
        System.out.println("hiiiiiii"+userSet);
        return itemToUpdate;
    }

    return null;
}

public Item deleteUserSavedItem(User user, ItemSavedBody itemSavedBody) {
        User exsistingUser = userDAO.findById(user.getId()).get();
    Optional<Item> optionalItem = itemDAO.findById(itemSavedBody.getId());

    if (optionalItem.isPresent()) {
        Item itemToUpdate = optionalItem.get();
        Set<User> userSet = itemToUpdate.getSavedByUsers();
        Set<Item> itemSet = exsistingUser.getSavedItems();
        itemSet.remove(itemToUpdate);
        userSet.remove(exsistingUser);
        exsistingUser.setSavedItems(itemSet);
        userDAO.save(exsistingUser);
        itemToUpdate.setSavedByUsers(userSet);
        itemDAO.save(itemToUpdate);
        System.out.println("hiiiiiii"+userSet);
        return itemToUpdate;
    }

    return null;
}

    public Item deleteItem(Long id){
        Item item = itemDAO.findById(id).get();
        if(item != null){
            FileData fileData = fileDataDAO.findById(item.getFileData().getId()).get();

            if(fileData != null){
                fileDataDAO.delete(fileData);
            }
            itemDAO.delete(item);
            return item;
        }
        return null;

    }

    public Item editItem(EditItemBody editItemBody){
        Item existingItem  = itemDAO.findById(editItemBody.getId()).get();
        if(existingItem != null){

            existingItem.setName(editItemBody.getName());
            existingItem.setBrand(editItemBody.getBrand());
            existingItem.setPrice(editItemBody.getPrice());
            existingItem.setDetails(editItemBody.getDetails());
            existingItem.setDiscount(editItemBody.getDiscount());
            existingItem.setCategory(editItemBody.getCategory());
            existingItem.setQuantity(editItemBody.getQuantity());
            itemDAO.save(existingItem);

            return existingItem;

        }

        return null;
    }


}
