package com.example.ecommerce.Repository;


import com.example.ecommerce.Entity.OrderList;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList,Integer> {
}
