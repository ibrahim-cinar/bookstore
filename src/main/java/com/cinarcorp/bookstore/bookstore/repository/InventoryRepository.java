package com.cinarcorp.bookstore.bookstore.repository;

import com.cinarcorp.bookstore.bookstore.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
