package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.CustomerDto;
import com.cinarcorp.bookstore.bookstore.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto convert(Customer from){
        return  new CustomerDto(from.getEmail(),from.getFirstName(),
                from.getLastName(), from.getStreetNumber(),
                from.getStreetName(), from.getPostalCode(),
                from.getCity(), from.getCountry(),
                from.getPhoneNumber(), from.isActive());
    }
}
