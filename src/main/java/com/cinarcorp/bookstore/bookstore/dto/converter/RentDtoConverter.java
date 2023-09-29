package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.RentDto;
import com.cinarcorp.bookstore.bookstore.model.Rent;
import org.springframework.stereotype.Component;

@Component
public class RentDtoConverter {
    private final CostumerDtoConverter converter;

    public RentDtoConverter(CostumerDtoConverter converter) {
        this.converter = converter;
    }

    public RentDto convert(Rent from){
        return new RentDto(converter.convert(from.getCostumer()), from.getRentStartTime(),from.getRentTime(),from.isDone());
    }
}
