package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.ItemBody;
import com.example.eCommercewebapp.exception.ItemAlreadyExistsException;
import com.example.eCommercewebapp.model.Item;
import com.example.eCommercewebapp.model.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemDAO itemDAO;
    @Autowired
    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
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

}
