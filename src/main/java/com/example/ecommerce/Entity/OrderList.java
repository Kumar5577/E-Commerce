package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orderList")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderNo;

    int totalValue;
@CreationTimestamp
    Date orderDate;

    String cardUsed;

    @OneToMany(mappedBy = "orderList",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Customer customer;
}
