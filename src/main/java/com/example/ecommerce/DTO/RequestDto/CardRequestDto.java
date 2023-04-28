package com.example.ecommerce.DTO.RequestDto;

import com.example.ecommerce.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String mobNo;

    String cardNo;

    int cvv;

    CardType cardType;

}
