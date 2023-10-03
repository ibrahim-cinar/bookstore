package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.dto.converter.BookDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.AuthorNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Author;
import com.cinarcorp.bookstore.bookstore.model.Book;
import com.cinarcorp.bookstore.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final BookDtoConverter bookDtoConverter;
    private final AuthorRepository authorRepository;

    public AuthorService(BookDtoConverter bookDtoConverter, AuthorRepository authorRepository) {
        this.bookDtoConverter = bookDtoConverter;
        this.authorRepository = authorRepository;
    }

    public List<BookDto> getBooksByAuthorId(String authorId) {
        return authorRepository.findById(authorId)
                .map(Author::getBooks)
                .orElseThrow(()->new AuthorNotFoundException("Author Id not found " + authorId))
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());

    }
        public List<BookDto> getBookByAuthorName(String firstName, String lastName) {
        return authorRepository.findByFirstnameAndLastname(firstName,lastName)
                .map(Author::getBooks)
                .orElseThrow(()->new AuthorNotFoundException("Author name not found"+firstName+" "+lastName))
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());

    }
}
