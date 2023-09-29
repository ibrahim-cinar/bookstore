package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String  inventoryId;
    private int stockLevelUsed;
    private int stockLevelNew;
    @OneToOne(mappedBy = "inventory",cascade = CascadeType.ALL)
    private Book book;

    public Inventory(int stockLevelUsed, int stockLevelNew) {
        this.stockLevelUsed = stockLevelUsed;
        this.stockLevelNew = stockLevelNew;
    }

    public Inventory(int stockLevelUsed, int stockLevelNew, Book book) {
        this.stockLevelUsed = stockLevelUsed;
        this.stockLevelNew = stockLevelNew;
        this.book = book;
    }
}
