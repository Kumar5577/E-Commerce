package com.example.ecommerce.DTO.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartRequestDto {
    int customerId;

    Integer totalCost;

    Integer numberOfItems;
}
