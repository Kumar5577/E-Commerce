package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.DTO.RequestDto.OrderRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ecommerce.DTO.ResponseDto.OrderResponseDto;
import com.example.ecommerce.Entity.*;
import com.example.ecommerce.Exception.InvalidCardException;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Repository.CardRepository;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Repository.OrderListRepository;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ProductImp productImp;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    CardRepository cardRepository;
    public OrderList placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();

        OrderList order = new OrderList();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item: cart.getItems()){
            try{
                productImp.decreaseProductQuantity(item);
                orderedItems.add(item);
            } catch (Exception e) {
                throw new Exception("Product Out of stock");
            }
        }
        order.setItems(orderedItems);
        for(Item item: orderedItems)
            item.setOrderList(order);
        order.setTotalValue(cart.getTotalCost());
        return order;
    }

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new Exception("Product doesn't exist");
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new InvalidCardException("Your card is not valid!!");
        }

        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        try{
            productImp.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

        OrderList order = new OrderList();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrders().add(order);
        item.setOrderList(order);
        product.getItemList().add(item);

        OrderList savedOrder = orderListRepository.save(order); // order and item

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
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acciotestingbackend@gmail.com");
        message.setTo("satishkumar259731@gmail.com");
        message.setSubject("Book Issued");
        message.setText(customer.getName()+ " You have Successfully Booked The Order Having the Total Price "+order.getTotalValue());
        javaMailSender.send(message);


        orderResponseDto.setItems(items);
        return orderResponseDto;

    }

    public String generateMaskedCard(String cardNo){
        String maskedCardNo = "";
        for(int i = 0;i<cardNo.length()-4;i++)
            maskedCardNo += 'X';
        maskedCardNo += cardNo.substring(cardNo.length()-4);
        return maskedCardNo;

    }
}
