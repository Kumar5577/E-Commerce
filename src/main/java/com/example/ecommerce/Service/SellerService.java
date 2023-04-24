package com.example.ecommerce.Service;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.Entity.Seller;
import com.example.ecommerce.Exception.EmailAlreadyPresentException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SellerService {


    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException;
    public SellerResponseDto getSellerByEmail(String email);
    public SellerResponseDto getSellerById(int id);

    public List<SellerResponseDto> getAllSellers();
    public SellerResponseDto updateSeller(Seller seller);
    public String deleteSellerById(int id);

    public String deleteSellerByEmail(String email);

}
