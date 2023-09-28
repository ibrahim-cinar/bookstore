package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent,Long> {
}
