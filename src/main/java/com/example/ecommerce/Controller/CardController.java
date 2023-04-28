package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Service.Implementation.CardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    // add card
    @Autowired
    CardServiceImp cardServiceImp;
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/add")
    public CardResponseDto addCard(@RequestBody CardRequestDto cardRequestDto) throws InvalidCustomerException {
        return cardServiceImp.addCard(cardRequestDto);
    }
    // get all Visa cards
    @GetMapping("/get-all-cards/{cardType}")
    public List<CardResponseDto> getCards(@PathVariable("cardType")CardType cardType){
        return cardServiceImp.getCards(cardType);
    }
    // get all master cards whose expiry date is greater than 01.01.2025
    // return the cardType which has the maximum number of that cards
    // return the cardType which has the min number of that cards

}
