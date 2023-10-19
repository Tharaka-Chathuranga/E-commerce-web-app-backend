package com.example.eCommercewebapp.api.model;

import com.example.eCommercewebapp.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderBody {
    private long quantity;
    private Item  item;
    private String status;

}
