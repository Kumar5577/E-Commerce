package com.example.ecommerce.DTO.ResponseDto;

import com.example.ecommerce.Enum.CategoryType;
import com.example.ecommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String sellerName;
    String productName;
    int quantity;
    int price;

    CategoryType categoryType;
    ProductStatus productStatus;
}
