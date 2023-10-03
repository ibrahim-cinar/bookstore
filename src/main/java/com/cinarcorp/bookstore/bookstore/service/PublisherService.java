package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.dto.converter.BookDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.AuthorNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.FirmNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Author;
import com.cinarcorp.bookstore.bookstore.model.Book;
import com.cinarcorp.bookstore.bookstore.model.Publisher;
import com.cinarcorp.bookstore.bookstore.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookDtoConverter bookDtoConverter;

    public PublisherService(PublisherRepository publisherRepository, BookDtoConverter bookDtoConverter) {
        this.publisherRepository = publisherRepository;
        this.bookDtoConverter = bookDtoConverter;
    }
    public List<BookDto> getBooksByPublisherId(String publisherId) {
        return publisherRepository.findById(publisherId)
                .map(Publisher::getBooks)
                .orElseThrow(() -> new FirmNotFoundException("Publisher Id not found " + publisherId))
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());
    }

    public List<BookDto> getBookByFirmName(String firmName) {
        return publisherRepository.findByFirmName(firmName)
                .map(Publisher::getBooks)
                .orElseThrow(()->new FirmNotFoundException("Firm not found: " + firmName))
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());

    }
    public List<BookDto> getBookByCountry(String country) {
        return publisherRepository.findByCountry(country)
                .map(Publisher::getBooks)
                .orElseThrow(()->new FirmNotFoundException("Country not found: " + country))
                .stream()
                .map(bookDtoConverter::convert)
                .collect(Collectors.toList());

    }


}
