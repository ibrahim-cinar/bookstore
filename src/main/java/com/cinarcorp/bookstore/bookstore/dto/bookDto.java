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
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class bookDto {
    private Long id;
    private String bookName;
    private String title;
    private int ISBN;
    private LocalDateTime publisherYear;
    private int price;
    private List<Author> authors;
    private List<Rent> rentList;
    private List<Genre> genres;
    private List<Publisher> publishers;
}
