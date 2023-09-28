package com.cinarcorp.bookstore.bookstore.dto;

import com.cinarcorp.bookstore.bookstore.model.Costumer;
import jakarta.persistence.Entity;
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

    private CostumerDto costumer;
    private LocalDateTime rentStartTime;
    private LocalDateTime rentTime;
    private boolean isDone;
}
