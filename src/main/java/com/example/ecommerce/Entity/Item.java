package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.core.Ordered;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    Integer requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    OrderList orderList;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Product product;
}
