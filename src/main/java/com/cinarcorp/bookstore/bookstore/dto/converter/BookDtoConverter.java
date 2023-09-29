package com.cinarcorp.bookstore.bookstore.dto.converter;

import com.cinarcorp.bookstore.bookstore.dto.BookDto;
import com.cinarcorp.bookstore.bookstore.model.Book;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookDtoConverter {
    private final AuthorDtoConverter authorDtoConverter;
    private final RentDtoConverter rentDtoConverter;
    private final  GenreDtoConverter genreDtoConverter;
    private final PublisherDtoConverter publisherDtoConverter;
    private final InventoryDtoConverter inventoryDtoConverter;

    public BookDtoConverter(AuthorDtoConverter authorDtoConverter, RentDtoConverter rentDtoConverter, GenreDtoConverter genreDtoConverter, PublisherDtoConverter publisherDtoConverter, InventoryDtoConverter inventoryDtoConverter) {
        this.authorDtoConverter = authorDtoConverter;
        this.rentDtoConverter = rentDtoConverter;
        this.genreDtoConverter = genreDtoConverter;
        this.publisherDtoConverter = publisherDtoConverter;
        this.inventoryDtoConverter = inventoryDtoConverter;
    }

    public BookDto convert(Book from){
        return new BookDto(
                from.getBookName()
                ,from.getTitle(), from.getISBN()
                ,from.getPublisherYear(),from.getPrice()
                ,from.getAuthors().stream().map(authorDtoConverter::convert).collect(Collectors.toList())
                ,from.getRentList().stream().map(rentDtoConverter::convert).collect(Collectors.toList())
                ,from.getGenres().stream().map(genreDtoConverter::convert).collect(Collectors.toList())
                ,from.getPublisher().stream().map(publisherDtoConverter::convert).collect(Collectors.toList()),
                inventoryDtoConverter.convert(from.getInventory()));
    }
}
