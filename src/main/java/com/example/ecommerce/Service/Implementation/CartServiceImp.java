package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.DTO.RequestDto.CheckOutCartRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ecommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ecommerce.DTO.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Entity.*;
import com.example.ecommerce.Exception.InvalidCardException;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Repository.CardRepository;
import com.example.ecommerce.Repository.CartRepository;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Repository.OrderListRepository;
import com.example.ecommerce.Service.CartService;
import com.example.ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
     CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderServiceImp orderServiceImp;
    @Autowired
    OrderListRepository orderListRepository;

    public CartResponseDto saveCart(Integer customerId, Item item){

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost() + item.getRequiredQuantity()*item.getProduct().getPrice();
        cart.setTotalCost(newTotal);
        cart.getItems().add(item);

        cart.setNumberOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .totalCost(cart.getTotalCost())
                .name(customer.getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item itemEntity: cart.getItems()){

            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setProductPrice(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setRequiredQuantity(itemEntity.getRequiredQuantity());

            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);
        return cartResponseDto;
    }
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkoutCartRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card==null || card.getCvv()!=checkoutCartRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is empty!!");
        }

        try{
            OrderList orderList = orderServiceImp.placeOrder(customer,card);  // throw exception if product goes out of stock
            customer.getOrders().add(orderList);
            cart.setTotalCost(0);
            cart.setNumberOfItems(0);
            cart.setItems(new ArrayList<>());
            //  customerRepository.save(customer);
            OrderList savedOrder = orderListRepository.save(orderList);



            //prepare response dto
            // prepare response dto
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(savedOrder.getOrderDate());
            orderResponseDto.setCardUsed(savedOrder.getCardUsed());
            orderResponseDto.setCustomerName(customer.getName());
            orderResponseDto.setOrderNo(savedOrder.getOrderNo());
            orderResponseDto.setTotalValue(savedOrder.getTotalValue());

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item itemEntity: savedOrder.getItems()){
                ItemResponseDto itemResponseDto = new ItemResponseDto();
                itemResponseDto.setProductPrice(itemEntity.getProduct().getPrice());
                itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity()*itemEntity.getProduct().getPrice());
                itemResponseDto.setProductName(itemEntity.getProduct().getName());
                itemResponseDto.setRequiredQuantity(itemEntity.getRequiredQuantity());

                items.add(itemResponseDto);
            }

            orderResponseDto.setItems(items);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
