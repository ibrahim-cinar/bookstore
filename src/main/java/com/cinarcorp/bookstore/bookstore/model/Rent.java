package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rent")
@Builder
public class Rent {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID")
    private Long id;

    private LocalDateTime rentStartTime;
    private LocalDateTime rentTime;
    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "costumer_id", nullable = false)
    private Costumer costumer;
}
