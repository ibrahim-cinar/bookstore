package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.PublisherDto;
import com.cinarcorp.bookstore.bookstore.model.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherDtoConverter {
    public PublisherDto convert(Publisher from){
        return new PublisherDto(from.getCountry(), from.getFirmName());
    }
}
