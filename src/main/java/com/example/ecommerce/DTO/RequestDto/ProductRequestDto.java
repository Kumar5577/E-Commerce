package com.example.ecommerce.DTO.RequestDto;

import com.example.ecommerce.Enum.CategoryType;
import com.example.ecommerce.Enum.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    int sellerId;
    String productName;
    int quantity;
    int price;

    CategoryType categoryType;
    ProductStatus productStatus;
}
