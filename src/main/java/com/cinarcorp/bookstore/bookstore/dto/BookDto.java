package com.cinarcorp.bookstore.bookstore.dto;

import com.cinarcorp.bookstore.bookstore.model.Author;
import com.cinarcorp.bookstore.bookstore.model.Genre;
import com.cinarcorp.bookstore.bookstore.model.Publisher;
import com.cinarcorp.bookstore.bookstore.model.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String bookName;
    private String title;
    private String ISBN;
    private LocalDateTime publisherYear;
    private int price;
    private List<AuthorDto> authors;
    private List<RentDto> rentList;
    private List<GenreDto> genres;
    private List<PublisherDto> publishers;
    private InventoryDto inventoryDto;
}
