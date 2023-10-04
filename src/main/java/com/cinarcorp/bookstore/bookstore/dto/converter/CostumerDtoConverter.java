package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.CostumerDto;
import com.cinarcorp.bookstore.bookstore.model.Costumer;
import org.springframework.stereotype.Component;

@Component
public class CostumerDtoConverter {
    public CostumerDto convert(Costumer from){
        return  new CostumerDto(from.getEmail(),from.getFirstName(),
                from.getLastName(), from.getStreetNumber(),
                from.getStreetName(), from.getPostalCode(),
                from.getCity(), from.getCountry(),
                from.getPhoneNumber(), from.isActive());
    }
}
