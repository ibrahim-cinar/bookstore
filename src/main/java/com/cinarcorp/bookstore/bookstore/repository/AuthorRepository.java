package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Author;
import com.cinarcorp.bookstore.bookstore.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,String> {

    @Query("SELECT a FROM Author a WHERE a.firstName = :firstName AND a.lastName = :lastName")
    Optional<Author> findByFirstnameAndLastname(@Param("firstName") String firstName, @Param("lastName") String lastName);


}
