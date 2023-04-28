package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.Entity.Card;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Exception.MobileAlreadyPresent;
import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Entity.Cart;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Repository.CardRepository;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Service.CustomerService;
import com.example.ecommerce.Transformer.CustomerTransformer;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Builder

public class CustomerImp implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileAlreadyPresent {
        Customer savedCustomer = CustomerTransformer.requestToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .customer(savedCustomer)
                .numberOfItems(0)
                .totalCost(0)
                .build();

            if(customerRepository.findByMobNo(customerRequestDto.getMobNo())!=null)
                throw new MobileAlreadyPresent("Mobile Number Already present");
            customerRepository.save(savedCustomer);
          CustomerResponseDto customerResponseDto = CustomerTransformer.customerToResponse(savedCustomer);
          return customerResponseDto;




    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDto>customerResponseDtos = new ArrayList<>();
        for (Customer customer : customerList){
            customerResponseDtos.add(CustomerTransformer.customerToResponse(customer));
        }
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto updateCustomer(Customer customer) throws InvalidCustomerException {
        Customer customer1= customerRepository.findByMobNo(customer.getMobNo());
        if(customer1 ==null){
            throw new InvalidCustomerException("Customer Not Found");
        }
        else {
            customer1.setAge(customer.getAge());
            customer1.setEmailId(customer.getEmailId());
            customer1.setMobNo(customer.getMobNo());
            customer1.setName(customer.getName());
        }
        CustomerResponseDto customerResponseDto = CustomerTransformer.customerToResponse(customer1);
            customerRepository.save(customer1);

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomerGreaterThan(int age) {
        List<Customer>customerList = customerRepository.getAllCustomerGreaterThan(age);
        List<CustomerResponseDto>customerResponseDtos = new ArrayList<>();
        for(Customer customer:customerList){
            customerResponseDtos.add(CustomerTransformer.customerToResponse(customer));
        }
        return customerResponseDtos;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomersOCardType(CardType cardType) {
        List<Card>cards = cardRepository.findByCardType(cardType);
        List<Customer>customers = new ArrayList<>();
        for(Card card:cards)
            customers.add(card.getCustomer());
        List<CustomerResponseDto>customerResponseDtos = new ArrayList<>();
        for(Customer customer:customers)
            customerResponseDtos.add(CustomerTransformer.customerToResponse(customer));
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto deleteCustomer(String mobile) {
        Customer customer = customerRepository.findByMobNo(mobile);
        CustomerResponseDto customerResponseDto = CustomerTransformer.customerToResponse(customer);
        customerRepository.delete(customer);
        return customerResponseDto;
    }
}
