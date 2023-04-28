package com.example.ecommerce.Transformer;

import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ecommerce.Entity.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {
    public static Card responseToCard(CardRequestDto cardRequestDto){
        Card card = Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cardNo(cardRequestDto.getCardNo())
                .build();
        return card;
    }
    public static CardResponseDto cardToResponse(Card card){
        CardResponseDto cardResponseDto =CardResponseDto.builder()
                .message("Card Added Successfully")
                .cardNo(card.getCardNo())
                .build();
        return cardResponseDto;
    }
}
