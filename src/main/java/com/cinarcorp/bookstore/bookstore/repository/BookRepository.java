package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
