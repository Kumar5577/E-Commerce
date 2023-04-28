package com.example.ecommerce.Transformer;

import com.example.ecommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ecommerce.Entity.Item;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }
}
