package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.DTO.RequestDto.CardRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ecommerce.Entity.Card;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Repository.CardRepository;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Service.CardService;
import com.example.ecommerce.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImp implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if(customer==null){
            throw new InvalidCustomerException("Sorry! The customer doesn't exists");
        }

        Card card = CardTransformer.responseToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);

        // response dto
        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();
    }

    @Override
    public List<CardResponseDto> getCards(CardType cardType) {
        List<Card>cards = cardRepository.findByCardType(cardType);
        List<CardResponseDto>cardResponseDtos = new ArrayList<>();
        for(Card card:cards)
            cardResponseDtos.add(CardTransformer.cardToResponse(card));
        return cardResponseDtos;
    }
}
