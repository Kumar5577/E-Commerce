package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Enum.CategoryType;
import com.example.ecommerce.Service.Implementation.ProductImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductImp productImp;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto){
        return productImp.addProduct(productRequestDto);
}
    //get product by seller emailId
    @GetMapping("/get-product-by-sellerEmailId/{emailId}")
    public List<ProductResponseDto> getProductBySellerEmailId(@PathVariable("emailId") String emailId){
        return productImp.getProductBySellerEmailId(emailId);
    }
   // delete product by sellerId he also tells u the product id
    @DeleteMapping("/delete-product-by-sellerId-and-productId/{sellerId" +
            "}/{productId}")
    public String deleteProductById(@PathVariable("sellerId") int sellerId,@PathVariable("productId") int productId){
        return productImp.deleteProductById(sellerId,productId);
    }
// return top 5 cheapest products
    @GetMapping("/top-five-cheapest-products")
    public List<ProductResponseDto> topFiveCheapestProducts(){
        return productImp.topFiveCheapestProducts();
    }
// return top 5 costliest product
@GetMapping("/top-five-costliest-products")
public List<ProductResponseDto> topFiveCostliestProducts(){
    return productImp.topFiveCostliestProducts();
}
// return all outOfStock products
    @GetMapping("/out-of-stock-products")
    public List<ProductResponseDto> outOfStockProducts(){
        return productImp.outOfStockProducts();
    }
// return all available
@GetMapping("/all-available-products")
public List<ProductResponseDto> availableProducts(){
    return productImp.availableProducts();
}
// return all products quantity which has less than 10
@GetMapping("/products-quantity-less-than-ten")
public List<ProductResponseDto> quantityLessThanTen() {
    return productImp.quantityLessThanTen();
}
//get products of a particular category
// get all products of a particular category
@GetMapping("/get/{category}")
public List<ProductResponseDto> getAllProductsByCategory(@PathVariable("category") String category){
    return productImp.getAllProductsByCategory(category);
}

// return the cheapest product at a particular category
@GetMapping("/get-cheapest-product-at -a particular-category/{category}")
    public ProductResponseDto getCheapestProductAtAParticularCategory(@PathVariable("category")String category){
        return productImp.getCheapestProductAtAParticularCategory(category);
}

//return the costliest product ata particular category
@GetMapping("/get-costliest-product-at -a particular-category/{category}")
public ProductResponseDto getCostliestProductAtAParticularCategory(@PathVariable("category")String category){
    return productImp.getCostliestProductAtAParticularCategory(category);
}

}
