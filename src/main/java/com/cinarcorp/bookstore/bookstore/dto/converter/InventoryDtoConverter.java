package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.InventoryDto;
import com.cinarcorp.bookstore.bookstore.model.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryDtoConverter {
    public InventoryDto convert(Inventory from){
        return new InventoryDto(from.getStockLevelUsed(), from.getStockLevelNew());
    }
}
