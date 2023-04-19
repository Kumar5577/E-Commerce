package com.example.ecommerce.Entity;

import com.example.ecommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String cardNo;

    int cvv;

    Date expiryDate;

    @Enumerated(EnumType.STRING)

    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;

}