package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
