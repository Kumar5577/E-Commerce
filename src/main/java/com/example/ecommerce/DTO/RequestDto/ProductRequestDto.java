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
    String email;
    String productName;
    int quantity;
    int price;

    CategoryType categoryType;
    ProductStatus productStatus;
}
