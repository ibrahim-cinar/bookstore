package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Book;
import com.cinarcorp.bookstore.bookstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,String> {
    //Optional<Book> getBookFromGenreId(String id);


}
