package com.example.ecommerce.Repository;

import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Entity.Card;
import com.example.ecommerce.Enum.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    @Query(value = "select c from Card c where c.cardType = :cardType")
    public List<Card> getAllCustomersVisa(CardType cardType);
    public List<Card> findByCardType(CardType cardType);
    Card findByCardNo(String cardNo);
}
