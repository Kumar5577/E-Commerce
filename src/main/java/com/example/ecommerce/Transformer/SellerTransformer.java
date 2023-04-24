package com.example.ecommerce.Transformer;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.Entity.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {
    public static Seller sellerRequestToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .age(sellerRequestDto.getAge())
                .mobNo(sellerRequestDto.getMobNo())
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .build();
    }
    public static SellerResponseDto sellerToResponse(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .message("Seller Added")
                .build();
    }
}
