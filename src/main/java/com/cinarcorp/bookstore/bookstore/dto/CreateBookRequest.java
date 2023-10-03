package com.cinarcorp.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String bookName;
    private String title;
    private String ISBN;
    private LocalDateTime publisherYear;
    private int price;
    private List<AuthorDto> authors= new ArrayList<>();
    private List<GenreDto> genres= new ArrayList<>();
    private List<PublisherDto> publishers= new ArrayList<>();
    private InventoryDto inventory = new InventoryDto();
}
