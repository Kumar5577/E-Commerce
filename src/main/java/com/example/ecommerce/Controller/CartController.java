package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.RequestDto.CartRequestDto;
import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Service.CartService;
import com.example.ecommerce.Service.Implementation.CartServiceImp;
import com.example.ecommerce.Service.Implementation.ItemServiceImp;
import com.example.ecommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemServiceImp itemService;

    @Autowired
    CartServiceImp cartService;

    @PostMapping("/add")
    public CartResponseDto addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try{
            Item itemSaved = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),itemSaved);
            return cartResponseDto;
        }
        catch (Exception e){
            throw  new Exception("Invalid Cart or Item Or CustomerId");
        }
    }
}
