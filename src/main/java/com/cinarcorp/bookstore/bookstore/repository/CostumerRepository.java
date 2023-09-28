package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer,Long> {
}
