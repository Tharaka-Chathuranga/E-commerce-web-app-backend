package com.example.eCommercewebapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;



@Entity
@Table(name = "orders")
@RequiredArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;



}
