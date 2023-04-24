package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.Entity.Seller;
import com.example.ecommerce.Exception.EmailAlreadyPresentException;
import com.example.ecommerce.Repository.SellerRepository;
import com.example.ecommerce.Service.SellerService;
import com.example.ecommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerImp implements SellerService {

@Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {
//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setAge(sellerRequestDto.getAge());
//        seller.setMobNo(sellerRequestDto.getMobNo());
//        seller.setEmailId(sellerRequestDto.getEmailId());
        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null)
            throw new EmailAlreadyPresentException("Email Id already registered");

        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

        // prepare response Dto
        SellerResponseDto sellerResponseDto = SellerTransformer.sellerToResponse(savedSeller);
        return sellerResponseDto;
    }

    @Override
    public SellerResponseDto getSellerByEmail(String email) {
        Seller seller = sellerRepository.findByEmailId(email);
        return SellerTransformer.sellerToResponse(seller);
    }

    @Override
    public SellerResponseDto getSellerById(int id) {
        Seller seller = sellerRepository.findById(id).get();
        return SellerTransformer.sellerToResponse(seller);
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for (Seller seller:sellers){
            sellerResponseDtos.add(SellerTransformer.sellerToResponse(seller));
        }
        return sellerResponseDtos;
    }

    @Override
    public SellerResponseDto updateSeller(Seller seller) {
        Seller seller1 = sellerRepository.findByEmailId(seller.getEmailId());
        seller1.setAge(seller.getAge());
        seller1.setName(seller.getName());
        seller1.setMobNo(seller.getMobNo());
        sellerRepository.save(seller1);
        return SellerTransformer.sellerToResponse(seller1);
    }
    @Override
    public String deleteSellerById(int id) {
        Seller seller = sellerRepository.findById(id).get();
        String name = seller.getName();

        sellerRepository.deleteById(id);
        return name+" deleted successfully";
    }

    @Override
    public String deleteSellerByEmail(String email) {
        Seller seller = sellerRepository.findByEmailId(email);
        String name = seller.getName();
        sellerRepository.delete(seller);
        return name+" deleted Successfully by Email ";
    }
}
