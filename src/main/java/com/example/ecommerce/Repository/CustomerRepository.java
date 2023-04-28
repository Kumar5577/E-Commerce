package com.example.ecommerce.Repository;

import com.example.ecommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ecommerce.Entity.Card;
import com.example.ecommerce.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobNo(String mobNo);
    @Query(value = "select * from Customer c where c.age > :age",nativeQuery = true)
    List<Customer> getAllCustomerGreaterThan(int age);

}
