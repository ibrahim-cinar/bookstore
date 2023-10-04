package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostumerRepository extends JpaRepository<Costumer,String> {
    Optional<Costumer> findCostumerByUsername(String username);
    Optional<Costumer> findCostumerByEmail(String username);

    Optional<Costumer> findCostumerByPhoneNumber(String username);

}
