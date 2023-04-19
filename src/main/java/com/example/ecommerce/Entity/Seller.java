package com.example.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String emailId;
    String mobNo;
    Integer age;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product>products = new ArrayList<>();

}
