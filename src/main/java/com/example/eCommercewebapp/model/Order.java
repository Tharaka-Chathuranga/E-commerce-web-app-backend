package com.example.eCommercewebapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}

