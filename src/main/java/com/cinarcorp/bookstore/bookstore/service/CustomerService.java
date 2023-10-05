package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.CustomerDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateCustomerRequest;
import com.cinarcorp.bookstore.bookstore.dto.UpdateCustomerRequest;
import com.cinarcorp.bookstore.bookstore.dto.converter.CustomerDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.CustomerNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.EmailAlreadyExistsException;
import com.cinarcorp.bookstore.bookstore.exception.EmailNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.PhoneNumberNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Customer;
import com.cinarcorp.bookstore.bookstore.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }
    public CustomerDto getCustomerById(String id){
        var costumer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        return  customerDtoConverter.convert(costumer);
    }

    /*                                     email                                                    */

    protected Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
    public CustomerDto getCustomerByEmail(String email){
        var customerEmail = findCustomerByEmail(email).orElseThrow(()->new EmailNotFoundException("email not found"+email));
        return customerDtoConverter.convert(customerEmail);
    }
    /*                                     email                                                    */


    /*                                     phoneNumber                                                    */

    protected Optional<Customer> findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumber(phoneNumber);
    }
    public CustomerDto getCustomerPhoneNumber(String phoneNumber){
        var costumerPhoneNumber = findCustomerByPhoneNumber(phoneNumber).orElseThrow(()->new PhoneNumberNotFoundException("phoneNumber not found"+phoneNumber));
        return customerDtoConverter.convert(costumerPhoneNumber);
    }
    /*                                     phoneNumber                                                    */

    public CustomerDto createNewCustomer(CreateCustomerRequest createCustomerRequest) {
        if (isEmailUnique(createCustomerRequest.getEmail())) {
            Customer customer = createCustomerFromRequest(createCustomerRequest);
            return customerDtoConverter.convert(customerRepository.save(customer));
        } else {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    private boolean isEmailUnique(String email) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerByEmail(email);
        return existingCustomer.isEmpty();
    }

    private Customer createCustomerFromRequest(CreateCustomerRequest request) {
        return new Customer(
                request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getStreetNumber(),
                request.getStreetName(),
                request.getPostalCode(),
                request.getCity(),
                request.getCountry(),
                request.getPhoneNumber(),
                true
        );
    }

    public CustomerDto updateCustomer(String email, UpdateCustomerRequest updateCostumerRequest){
        Optional<Customer> customerOptional = findCustomerByEmail(email);
        if (customerOptional.isPresent()) {
        Customer customer= customerOptional.get();
            customer.setFirstName(updateCostumerRequest.getFirstName());
            customer.setLastName(updateCostumerRequest.getLastName());
            customer.setStreetNumber(updateCostumerRequest.getStreetNumber());
            customer.setStreetName(updateCostumerRequest.getStreetName());
            customer.setPostalCode(updateCostumerRequest.getPostalCode());
            customer.setCity(updateCostumerRequest.getCity());
            customer.setCountry(updateCostumerRequest.getCountry());
            customer.setPhoneNumber(updateCostumerRequest.getPhoneNumber());
            customer.setActive(updateCostumerRequest.isActive());
        Customer updatedCustomer = customerRepository.save(customer);
        return customerDtoConverter.convert(updatedCustomer);

        }else{
            throw new EmailNotFoundException("email not found"+email);

        }
    }
    public void deleteCustomer(String email){
        Optional<Customer> optionalCustomer =findCustomerByEmail(email);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            customerRepository.delete(customer);
            throw new ResponseStatusException(HttpStatus.OK, "customer deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer no found");


    }
}
