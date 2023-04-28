package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
    public List<CardResponseDto> getCards(CardType cardType);

}
