package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.dto.converter.BookDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.GenreIdNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Book;
import com.cinarcorp.bookstore.bookstore.model.Genre;
import com.cinarcorp.bookstore.bookstore.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final BookDtoConverter bookDtoConverter;

    public GenreService(GenreRepository genreRepository, BookDtoConverter bookDtoConverter) {
        this.genreRepository = genreRepository;
        this.bookDtoConverter = bookDtoConverter;
    }

    public List<BookDto> getBooksByGenreId(String genreId) {
        return genreRepository.findById(genreId)
                .map(Genre::getBooks)
                .orElseThrow(()->new GenreIdNotFoundException("Genre Id not found " + genreId))
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());


    }

}
