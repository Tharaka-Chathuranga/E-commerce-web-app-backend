package com.example.eCommercewebapp.api.model;

import com.example.eCommercewebapp.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemBody {

    private String name;
    private String brand;
    private String price;
    private String discount;
    private String catageory;
    private String details;
    private long quantity;



}
