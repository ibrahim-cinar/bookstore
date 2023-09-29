package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.AuthorDto;
import com.cinarcorp.bookstore.bookstore.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter {
    public AuthorDto convert(Author from){
        return new AuthorDto(from.getFirstName(), from.getLastName());
    }
}
