package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID")
    private Long id;

    @ManyToMany
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Rent> rentList;


    @ManyToMany
    @JoinTable(name = "genre_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "publisher_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private List<Publisher> publisher;

    private String bookName;
    private String title;
    private int ISBN;
    private LocalDateTime publisherYear;
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private Inventory inventory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ISBN == book.ISBN && price == book.price && Objects.equals(id, book.id) && Objects.equals(authors, book.authors) && Objects.equals(rentList, book.rentList) && Objects.equals(genres, book.genres) && Objects.equals(publisher, book.publisher) && Objects.equals(bookName, book.bookName) && Objects.equals(title, book.title) && Objects.equals(publisherYear, book.publisherYear) && Objects.equals(inventory, book.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authors, rentList, genres, publisher, bookName, title, ISBN, publisherYear, price, inventory);
    }
}
