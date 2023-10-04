package com.cinarcorp.bookstore.bookstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.UUID;
@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "costumers",
        uniqueConstraints = {@UniqueConstraint(name = " emailId_unique", columnNames = "email")
                , @UniqueConstraint(name = " username_unique", columnNames = "username")
                , @UniqueConstraint(name = " phoneNumber_unique", columnNames = "phoneNumber")}

)
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String costumerId;
    @OneToMany(mappedBy = "costumer", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Rent> rent;
    private String username;
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

}
