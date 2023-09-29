package com.cinarcorp.bookstore.bookstore.dto;

import com.cinarcorp.bookstore.bookstore.model.Rent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CostumerDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private boolean isActive;
}
