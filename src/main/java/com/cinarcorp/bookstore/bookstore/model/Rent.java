package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rents")
@Builder
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String rentId;

    private LocalDateTime rentStartTime;
    private LocalDateTime rentTime;
    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
