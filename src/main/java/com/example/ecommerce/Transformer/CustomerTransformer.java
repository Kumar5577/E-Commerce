package com.example.ecommerce.Transformer;

import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Entity.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {
    public static Customer requestToCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = Customer.builder()
                .age(customerRequestDto.getAge())
                .emailId(customerRequestDto.getEmailId())
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .build();
        return customer;
    }
    public static CustomerResponseDto customerToResponse(Customer customer){
        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                .message("Customer Added Successfully")
                .mobile(customer.getMobNo())
                .name(customer.getName())
                .build();
        return customerResponseDto;
    }
}
