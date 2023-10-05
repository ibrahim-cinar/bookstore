package com.cinarcorp.bookstore.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private CustomerDto costumer;
    private LocalDateTime rentStartTime;
    private LocalDateTime rentTime;
    private boolean isDone;
}
