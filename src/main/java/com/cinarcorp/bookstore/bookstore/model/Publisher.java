package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID")
    private Long id;
    private String country;
    private String firmName;
    @ManyToMany(mappedBy = "publisher")
    private List<Book> books;
}
