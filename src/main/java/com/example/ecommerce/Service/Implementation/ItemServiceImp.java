package com.example.ecommerce.Service.Implementation;

import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.Entity.Customer;
import com.example.ecommerce.Entity.Item;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Enum.ProductStatus;
import com.example.ecommerce.Exception.InvalidCustomerException;
import com.example.ecommerce.Repository.CustomerRepository;
import com.example.ecommerce.Repository.ItemRepository;
import com.example.ecommerce.Repository.ProductRepository;
import com.example.ecommerce.Service.ItemService;
import com.example.ecommerce.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProductRepository productRepository;

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is invalid !!");
        }

        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch(Exception e){
            throw new Exception("Product doesn't exist");
        }

        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
            throw new Exception("Product out of Stock");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItemList().add(item);
        Product savedProduct = productRepository.save(product);

        // Think - only saving child will also work here ?????????

        int size = product.getItemList().size();
        return savedProduct.getItemList().get(size-1);
    }
}
