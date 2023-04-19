package com.example.ecommerce.Entity;

import com.example.ecommerce.Enum.CategoryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int quantity;
    int price;
    @Enumerated(EnumType.STRING)
    CategoryType categoryType;
    @ManyToOne
    @JoinColumn
    Seller seller;
}
