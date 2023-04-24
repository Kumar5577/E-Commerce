package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ecommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ecommerce.Entity.Seller;
import com.example.ecommerce.Exception.EmailAlreadyPresentException;
import com.example.ecommerce.Service.Implementation.SellerImp;
import com.example.ecommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerImp sellerImp;
    @PostMapping("/add")
    public SellerResponseDto addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {
   return sellerImp.addSeller(sellerRequestDto);
    }

    // get a seller by email
    @GetMapping("/get/{email}")
    public SellerResponseDto getSellerByEmail(@PathVariable("email")String email){
        return sellerImp.getSellerByEmail(email);
    }
    //get by id
    @GetMapping("/get/{id}")
    public SellerResponseDto getSellerById(@PathVariable("id")int id) {
        return sellerImp.getSellerById(id);
    }



    //update seller info based on email
    @PutMapping("/update-seller")
    public SellerResponseDto updateSeller(@RequestBody Seller seller){
        return sellerImp.updateSeller(seller);
    }
    //delete seller by id
    @DeleteMapping("/delete")
    public String deleteSellerById(@RequestParam int id){
        return sellerImp.deleteSellerById(id);
    }
    //get all sellers
    @GetMapping("/sellers")
    public List<SellerResponseDto> getAllSellers(){
        return sellerImp.getAllSellers();
    }



}
