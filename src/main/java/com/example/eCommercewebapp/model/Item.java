package com.example.eCommercewebapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Items")
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "item_sequence",
                        sequenceName = "item_sequence",
    allocationSize=1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "discount",nullable = false)
    private String discount;

    @Column(name="catageory")
    private String catageory;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<Order> Orders;



}
