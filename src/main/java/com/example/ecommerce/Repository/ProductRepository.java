package com.example.ecommerce.Repository;

import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryType(CategoryType productCategory);
    Product findByPrice(int price);
}
