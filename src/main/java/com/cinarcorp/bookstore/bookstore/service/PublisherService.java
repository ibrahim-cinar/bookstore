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
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);

        if (publisherOptional.isPresent()) {
            Publisher publisher = publisherOptional.get();
            return publisher.getBooks().stream()
                    .map(bookDtoConverter::convert) // Book'ları BookDto'ya dönüştür
                    .collect(Collectors.toList()); // List<BookDto> olarak topla
        } else {
            throw new FirmNotFoundException("Publisher Id not found " + publisherId);
        }
    }
    public List<BookDto> getBookByFirmName(String firmName) {
        Optional<Publisher> firmOptional = publisherRepository.findByFirmName(firmName);
        if (firmOptional.isPresent()) {
            Publisher firm = firmOptional.get();
            return firm.getBooks().stream().map(bookDtoConverter::convert).collect(Collectors.toList());
        } else {
            throw new FirmNotFoundException("Firm not found: " + firmName);
        }
    }

}
