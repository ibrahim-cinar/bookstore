package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers",
        uniqueConstraints = {@UniqueConstraint(name = " emailId_unique", columnNames = "email")
                , @UniqueConstraint(name = " phoneNumber_unique", columnNames = "phoneNumber")}

)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;
    @Column(unique = true)
    private String email;
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
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Rent> rent;

    public Customer(String email, String firstName, String lastName,
                    String password, String streetNumber, String streetName,
                    String postalCode, String city, String country,
                    String phoneNumber, boolean isActive, List<Rent> rent) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.rent = rent;
    }
    public Customer(String email, String firstName, String lastName,
                    String password, String streetNumber, String streetName,
                    String postalCode, String city, String country,
                    String phoneNumber, boolean isActive) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }
    public Customer(String firstName, String lastName,
                    String password, String streetNumber, String streetName,
                    String postalCode, String city, String country,
                    String phoneNumber, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }
}
