package com.example.eCommercewebapp.api.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSavedBody {
    private Long id;
    private String name;
    private String price;
    private String discount;

}
