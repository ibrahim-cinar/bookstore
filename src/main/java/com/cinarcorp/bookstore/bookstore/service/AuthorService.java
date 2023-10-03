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
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return author.getBooks().stream()
                    .map(bookDtoConverter::convert) // Book'ları BookDto'ya dönüştür
                    .collect(Collectors.toList()); // List<BookDto> olarak topla
        } else {
            throw new AuthorNotFoundException("Author Id not found " + authorId);
        }
    }
        public List<BookDto> getBookByAuthorName(String firstName, String lastName) {
            Optional<Author> authorOptional = authorRepository.findByFirstnameAndLastname(firstName, lastName);
            if (authorOptional.isPresent()) {
                Author author = authorOptional.get();
                return author.getBooks().stream()
                        .map(bookDtoConverter::convert) // Book'ları BookDto'ya dönüştür
                        .collect(Collectors.toList()); // List<BookDto> olarak topla
            } else {
                throw new AuthorNotFoundException("Author not found: " + firstName + " " + lastName);
            }
    }
}
