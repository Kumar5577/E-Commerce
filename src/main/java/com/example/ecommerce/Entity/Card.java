package com.example.ecommerce.Entity;

import com.example.ecommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String cardNo;

    int cvv;
    @CreationTimestamp
    Date expiryDate;

    @Enumerated(EnumType.STRING)

    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;

}
