package com.example.eCommercewebapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "quantity", nullable = false, unique = true)
    private long quantity;

//    @OneToOne
//    @JoinColumn(name = "item_id", nullable = false)
//    private Item item;


}
