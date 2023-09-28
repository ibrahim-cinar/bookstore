package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "costumer")
public class Costumer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name ="UUID")
    private Long id;
    @OneToMany(mappedBy = "costumer", fetch = FetchType.LAZY)
    private List<Rent> rent;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private boolean isActive;

}
