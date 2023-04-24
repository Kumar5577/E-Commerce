package com.example.ecommerce.Transformer;

import com.example.ecommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.ProductStatus;
import lombok.experimental.UtilityClass;

@UtilityClass

public class ProductTransformer {
    public static Product requestToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .price(productRequestDto.getPrice())
                .name(productRequestDto.getProductName())
                .categoryType(productRequestDto.getCategoryType())
                .productStatus(ProductStatus.AVAILABLE)
                .quantity(productRequestDto.getQuantity())

                .build();
    }
    public static ProductResponseDto productToResponse(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .categoryType(product.getCategoryType())
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

}
