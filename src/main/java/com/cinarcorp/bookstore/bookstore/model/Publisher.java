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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String  publisherId;
    private String country;
    @Column(name = "firm_name",nullable = false)
    private String firmName;
    @ManyToMany(mappedBy = "publisher",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> books;
}
