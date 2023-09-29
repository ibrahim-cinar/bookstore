package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.GenreDto;
import com.cinarcorp.bookstore.bookstore.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoConverter {
    public GenreDto convert(Genre from){
        return new GenreDto(from.getDescription());
    }
}
