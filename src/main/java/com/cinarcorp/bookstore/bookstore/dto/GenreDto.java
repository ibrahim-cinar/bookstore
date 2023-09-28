package com.cinarcorp.bookstore.bookstore.dto;

import com.cinarcorp.bookstore.bookstore.model.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private String description;
    private List<BookDto> books;
}
