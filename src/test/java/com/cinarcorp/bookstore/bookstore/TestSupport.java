package com.cinarcorp.bookstore.bookstore;

import com.cinarcorp.bookstore.bookstore.dto.CustomerDto;
import com.cinarcorp.bookstore.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    private static String customerId = "a85s4das2131sa231da5s13";
    public static List<Customer> generateCustomers() {

        return IntStream.range(0, 5).mapToObj(i ->
                new Customer(
                    i + "@cinarcorp.net" + i,
                    "firstName" + i,
                    "lastName" + i,
                    "streetNumber" + i,
                    "streetName" + i,
                    "postalCode" + i,
                    "city" + i,
                    "country" + i,
                    "phoneNumber" + i,
                    new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }
    public static List<CustomerDto> generateCustomerDtoList(List<Customer> costumerList){

        return costumerList.stream().map(from->new CustomerDto(from.getEmail()
                ,from.getFirstName(),
                from.getLastName(), from.getStreetNumber(),
                from.getStreetName(), from.getPostalCode(),
                from.getCity(), from.getCountry(),
                from.getPhoneNumber(), from.isActive())).collect(Collectors.toList());
    }

    public  static Customer generateCustomer(String email){
        return   new Customer(
                customerId + "@cinarcorp.net" + customerId,
                "firstName" + customerId,
                "lastName" + customerId,
                "streetNumber" + customerId,
                "streetName" + customerId,
                "postalCode" + customerId,
                "city" + customerId,
                "country" + customerId,
                "phoneNumber" + customerId,
                true);
    }
    public  static CustomerDto generateCustomerDto(String email){

         return   new CustomerDto(
                customerId + "@cinarcorp.net" + customerId,
                "firstName" + customerId,
                "lastName" + customerId,
                "streetNumber" + customerId,
                "streetName" + customerId,
                "postalCode" + customerId,
                "city" + customerId,
                "country" + customerId,
                "phoneNumber" + customerId,
                true);
    }
}
