package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ecommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Entity.Seller;
import com.example.ecommerce.Enum.CategoryType;
import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Repository.SellerRepository;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductImp implements ProductService {
@Autowired
    ProductRepository productRepository;
@Autowired
    SellerRepository sellerRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
    Product product = ProductTransformer.requestToProduct(productRequestDto);
    Seller seller = sellerRepository.findByEmailId(productRequestDto.getEmail());
    seller.getProducts().add(product);
    product.setSeller(seller);
        sellerRepository.save(seller);
    ProductResponseDto productResponseDto = ProductTransformer.productToResponse(product);
        productResponseDto.setSellerName(seller.getName());
        sellerRepository.save(seller);
    return productResponseDto;

    }

    @Override
    public List<ProductResponseDto> getProductBySellerEmailId(String emailId) {
        
        Seller seller = sellerRepository.findByEmailId(emailId);
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        List<Product>products = seller.getProducts();

       for(Product product:products){
           productResponseDtoList.add(ProductTransformer.productToResponse(product));

       }
       return productResponseDtoList;

    }

    @Override
    public String deleteProductById(int sellerId, int productId) {
        Seller seller = sellerRepository.findById(sellerId).get();
        List<Product> productList = seller.getProducts();
        String message = "";
        for (Product product : productList) {
            if ((product.getId()==productId)) {
                message+=product.getName();
                productList.remove(product);
                productRepository.delete(product);
            }
        }
        return message+" Deleted Successfully";
    }

    @Override
    public List<ProductResponseDto> topFiveCheapestProducts() {
        List<Product>products = productRepository.findAll();
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> id = new ArrayList<>();
        for (Product product:products){
            map.put(product.getId(), product.getPrice());
        }
        for(int price: map.values()){
            id.add(price);
        }
        List<Product> productList = new ArrayList<>();
        Collections.sort(id);
        for (int k : id){
            int count = 0;
        for (int e: map.keySet()) {
            if(map.get(e)==k){
                productList.add(productRepository.findById(e).get());
                count++;
            }
            if(count==5)
                break;
        }
        }
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product producte:productList){
            productResponseDtos.add(ProductTransformer.productToResponse(producte));
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> topFiveCostliestProducts() {
        List<Product>products = productRepository.findAll();
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> id = new ArrayList<>();
        for (Product product:products){
            map.put(product.getId(), product.getPrice());
        }
        for(int price: map.values()){
            id.add(price);
        }
        List<Product> productList = new ArrayList<>();
        Collections.sort(id);
        for (int i= id.size()-1;i>0;i--){
            int count = 0;
            for (int e: map.keySet()) {
                if(map.get(e)== id.get(e)){
                    productList.add(productRepository.findById(e).get());
                    count++;
                }
                if(count==5)
                    break;
            }
        }
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product producte:productList){
            productResponseDtos.add(ProductTransformer.productToResponse(producte));
        }
        return productResponseDtos;

    }

    @Override
    public List<ProductResponseDto> outOfStockProducts() {
        List<Product>products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product:products){
            if(product.getProductStatus()==ProductStatus.OUTOFSTOCK)
                productResponseDtos.add(ProductTransformer.productToResponse(product));
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> availableProducts() {
        List<Product>products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product:products){
            if(product.getProductStatus()==ProductStatus.AVAILABLE)
                productResponseDtos.add(ProductTransformer.productToResponse(product));
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> quantityLessThanTen() {
        List<Product>products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();
        for (Product product:products){
            if(product.getQuantity()<10){
                productResponseDtos.add(ProductTransformer.productToResponse(product));
            }
        }
        return productResponseDtos;
    }
    @Override
    public List<ProductResponseDto> getAllProductsByCategory(String category){
        List<Product> products = productRepository.findByCategoryType(CategoryType.valueOf(category));

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product: products) {
            productResponseDtos.add(ProductTransformer.productToResponse(product));
        }
        return productResponseDtos;
    }
    @Override
    public ProductResponseDto getCheapestProductAtAParticularCategory(String category) {
        List<ProductResponseDto> productResponseDtos = getAllProductsByCategory(String.valueOf(CategoryType.valueOf(category)));
        int maxPrice = Integer.MAX_VALUE;
        int minPrice =0;
        for(ProductResponseDto productResponseDto:productResponseDtos){
            if(productResponseDto.getPrice()<maxPrice) minPrice = productResponseDto.getPrice();
        }
        Product product=productRepository.findByPrice(minPrice);
        return ProductTransformer.productToResponse(product);
    }

    @Override
    public ProductResponseDto getCostliestProductAtAParticularCategory(String category) {
        List<ProductResponseDto> productResponseDtos = getAllProductsByCategory(String.valueOf(CategoryType.valueOf(category)));
        int minPrice = Integer.MIN_VALUE;
        int  maxPrice=0;
        for(ProductResponseDto productResponseDto:productResponseDtos){
            if(productResponseDto.getPrice()>minPrice) maxPrice = productResponseDto.getPrice();
        }
        Product product=productRepository.findByPrice(maxPrice);
        return ProductTransformer.productToResponse(product);
    }
    public void decreaseProductQuantity(Item item) throws Exception {

        Product product = item.getProduct();
        int quantity = item.getRequiredQuantity();
        int currentQuantity = product.getQuantity();
        if(quantity>currentQuantity){
            throw new Exception("Out of stock");
        }
        product.setQuantity(currentQuantity-quantity);
        if(product.getQuantity()==0){
            product.setProductStatus(ProductStatus.OUTOFSTOCK);
        }
    }



}
