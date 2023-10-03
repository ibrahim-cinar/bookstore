package com.cinarcorp.bookstore.bookstore.model;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.*;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bookId;
    private String bookName;
    private String title;
    private String ISBN;
    private LocalDateTime publisherYear;
    private int price;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "author_id",referencedColumnName = "authorId"))
    private List<Author> authors=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "genre_book",
            joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "genre_id",referencedColumnName = "genreId"))
    private List<Genre> genres=new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Column(name = "rent_list",nullable = false)
    private List<Rent> rentList=new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "publisher_book",
            joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id",referencedColumnName = "publisherId"))
    private List<Publisher> publisher=new ArrayList<>();



    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", referencedColumnName = "inventory_id")
    private Inventory inventory;


    public Book(String bookName, String title, String ISBN,
                LocalDateTime publisherYear, int price,
                List<Author> authors, List<Genre> genres,
                List<Publisher> publisher, Inventory inventory) {
        this.bookName = bookName;
        this.title = title;
        this.ISBN = ISBN;
        this.publisherYear = publisherYear;
        this.price = price;
        this.authors = authors;
        this.genres = genres;
        this.publisher = publisher;
        this.inventory = inventory;
    }

    public Book(String bookName, String title, String ISBN, LocalDateTime publisherYear, int price) {
        this.bookName = bookName;
        this.title = title;
        this.ISBN = ISBN;
        this.publisherYear = publisherYear;
        this.price = price;
    }
    public void addAuthor(Author author1){
        if(authors==null) authors = new ArrayList<>();
        authors.add(author1);
    }
        public void addGenres(Genre genre1){
        if(genres==null) genres = new ArrayList<>();
        genres.add(genre1);
    }
    public void addPublisher(Publisher publisher1){
        if(publisher==null) publisher = new ArrayList<>();
        publisher.add(publisher1);
    }
    public List<Author> getAuthors() {
        return Objects.requireNonNullElse(authors, Collections.emptyList());
    }
    public List<Rent> getRentList(){
        return Objects.requireNonNullElse(rentList,Collections.emptyList());
    }
    public List<Genre> getGenres(){
        return Objects.requireNonNullElse(genres,Collections.emptyList());
    }
    public List<Publisher> getPublisher(){
        return Objects.requireNonNullElse(publisher,Collections.emptyList());
    }
    public Inventory getInventory(){
        return Objects.requireNonNullElse(inventory, new Inventory());
    }

}
