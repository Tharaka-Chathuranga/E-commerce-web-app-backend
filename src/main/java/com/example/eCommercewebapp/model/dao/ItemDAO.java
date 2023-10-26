package com.example.eCommercewebapp.model.dao;

import com.example.eCommercewebapp.model.Item;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemDAO extends ListCrudRepository<Item, Long> {
    Optional<Item> findByNameAndBrand(String name,String brand);
    Optional<List<Item>> findByCategory(String category);


}

