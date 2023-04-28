package com.example.ecommerce.Controller;

import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Enum.CardType;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Exception.MobileAlreadyPresent;
import com.example.ecommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Service.CustomerService;
import com.example.ecommerce.Service.Implementation.CustomerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerImp customerImp;
    @PostMapping("/add")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileAlreadyPresent {
   return customerImp.addCustomer(customerRequestDto);
    }


//     View all customers
    @GetMapping("/get-all-customers")
    public List<CustomerResponseDto> getAllCustomers(){
        return customerImp.getAllCustomers();
    }
//     get customer by mobile and update
    @PutMapping("'update-customer/{email}")
    public CustomerResponseDto updateCustomer(@RequestBody Customer customer) throws InvalidCustomerException {
        return customerImp.updateCustomer(customer);
    }
//     get all customers whose age is greater than 25
    @GetMapping("/get-all-customers-age-greater-than/{age}")
    public List<CustomerResponseDto> getAllCustomerGreaterThan(@PathVariable("age")int age){
        return customerImp.getAllCustomerGreaterThan(age);
    }
//     get all customers who use Visa card
    @GetMapping("/get-all-the-customers-who-use-visa-cards/{cardType}")
        public List<CustomerResponseDto> getAllCustomersVisa(CardType cardType){
            return customerImp.getAllCustomersOCardType(cardType);
    }

//     delete a customer by mobNo/email
   @DeleteMapping("/delete/{mobile}")
    public CustomerResponseDto deleteCustomer(@PathVariable("mobile")String mobile){
        return customerImp.deleteCustomer(mobile);
   }

}
