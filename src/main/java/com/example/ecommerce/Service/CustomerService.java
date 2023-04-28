package com.example.ecommerce.Service;

import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Exception.MobileAlreadyPresent;
import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileAlreadyPresent;
    public List<CustomerResponseDto> getAllCustomers();
    public CustomerResponseDto updateCustomer(Customer customer) throws InvalidCustomerException;
    public List<CustomerResponseDto> getAllCustomerGreaterThan(int age);
    public List<CustomerResponseDto> getAllCustomersOCardType(CardType cardType);
    public CustomerResponseDto deleteCustomer(String mobile);
}
