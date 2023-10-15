package com.example.eCommercewebapp.service;

import com.example.eCommercewebapp.api.model.ItemBody;
import com.example.eCommercewebapp.exception.ItemAlreadyExistsException;
import com.example.eCommercewebapp.model.Item;
import com.example.eCommercewebapp.model.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemDAO itemDAO;
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
       item.setCatageory(itemBody.getCatageory());
       item.setDetails(itemBody.getDetails());
       item.setDiscount(itemBody.getDiscount());
       item.setPrice(itemBody.getPrice());


       itemDAO.save(item);
       return item;
    }
}
