package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.RentDto;
import com.cinarcorp.bookstore.bookstore.model.Rent;
import org.springframework.stereotype.Component;

@Component
public class RentDtoConverter {
    private final CustomerDtoConverter converter;

    public RentDtoConverter(CustomerDtoConverter converter) {
        this.converter = converter;
    }

    public RentDto convert(Rent from){
        return new RentDto(converter.convert(from.getCustomer()), from.getRentStartTime(),from.getRentTime(),from.isDone());
    }
}
