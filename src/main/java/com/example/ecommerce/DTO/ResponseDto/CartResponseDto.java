package com.example.ecommerce.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    String name;

    Integer totalCost;

    Integer numberOfItems;
    List<ItemResponseDto> items;
}
