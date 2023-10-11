package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.CustomerDto;
import com.cinarcorp.bookstore.bookstore.dto.converter.CustomerDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.EmailNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Customer;
import com.cinarcorp.bookstore.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static com.cinarcorp.bookstore.bookstore.TestSupport.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    private CustomerDtoConverter customerDtoConverter;
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    public void setUp(){
        customerDtoConverter = mock(CustomerDtoConverter.class);
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository,customerDtoConverter);
    }
    @Test
    public void testGetAllCustomer_itShouldReturnCostumerDtoList(){
        List<Customer> customerList = generateCustomers();
        List<CustomerDto> customerDtoList = generateCustomerDtoList(customerList);

        when(customerRepository.findAll()).thenReturn(customerList);
        when(customerDtoConverter.convert(customerList)).thenReturn(customerDtoList);

        List<CustomerDto> result = customerService.getAllCustomer();
        assertEquals(customerDtoList,result);
        //verify(customerRepository.findAll());
        //verify(customerDtoConverter).convert(customerList);
    }
    @Test
    public void testGetUserByEmailCustomer_whenCustomerMailExist_ItShouldReturnCostumerDto(){
        String email ="mail@cinarcorp.com";
        Customer customer = generateCustomer(email);
        CustomerDto customerDto = generateCustomerDto(email);

        when(customerRepository.findCustomerByEmail(email)).thenReturn(Optional.of(customer));
        when(customerDtoConverter.convert(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerByEmail(email);
        assertEquals(customerDto,result);
        //verify(customerRepository.findAll());
        //verify(customerDtoConverter).convert(customerList);
    }
    @Test
    public void testGetUserByEmailCustomer_whenCustomerMailDoesNotExist_ItShouldThrowEmailNotFoundException(){
        String email ="mail@cinarcorp.com";
        Customer customer = generateCustomer(email);
        CustomerDto customerDto = generateCustomerDto(email);

        when(customerRepository.findCustomerByEmail(email)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EmailNotFoundException.class,()->customerService.getCustomerByEmail(email));
        //verify(customerRepository.findAll());
        //verify(customerDtoConverter).convert(customerList);
    }

}