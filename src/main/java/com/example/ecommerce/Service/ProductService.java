package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Enum.CategoryType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto);
    public List<ProductResponseDto> getProductBySellerEmailId(String emailId);

    public String deleteProductById(int sellerId,int productId);

    public List<ProductResponseDto> topFiveCheapestProducts();

    public List<ProductResponseDto> topFiveCostliestProducts();

    public List<ProductResponseDto> outOfStockProducts();
    public List<ProductResponseDto> availableProducts();
    public List<ProductResponseDto> quantityLessThanTen();
    public List<ProductResponseDto> getAllProductsByCategory(CategoryType category);
    public ProductResponseDto getCheapestProductAtAParticularCategory(CategoryType category);

    public ProductResponseDto getCostliestProductAtAParticularCategory(CategoryType category);

}
