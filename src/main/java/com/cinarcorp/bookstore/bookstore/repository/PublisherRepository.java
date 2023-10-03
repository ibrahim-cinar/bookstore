package com.cinarcorp.bookstore.bookstore.repository;
import com.cinarcorp.bookstore.bookstore.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface PublisherRepository extends JpaRepository<Publisher,String> {
    Optional<Publisher> findByFirmName(String firmName);
    Optional<Publisher> findByCountry(String country);



}
