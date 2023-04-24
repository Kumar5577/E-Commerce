package com.example.ecommerce.Entity;

import com.example.ecommerce.Enum.CategoryType;
import com.example.ecommerce.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int quantity;
    int price;
    @Enumerated(EnumType.STRING)
    CategoryType categoryType;
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;
    @ManyToOne
    @JoinColumn
    Seller seller;
}
