package com.cinarcorp.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {
    private String bookName;
    private String title;
    private LocalDateTime publisherYear;
    private int price;
}
