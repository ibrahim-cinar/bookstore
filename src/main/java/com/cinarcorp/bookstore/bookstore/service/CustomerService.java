package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.CustomerDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateCustomerRequest;
import com.cinarcorp.bookstore.bookstore.dto.UpdateCustomerRequest;
import com.cinarcorp.bookstore.bookstore.dto.converter.CustomerDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.*;
import com.cinarcorp.bookstore.bookstore.model.Customer;
import com.cinarcorp.bookstore.bookstore.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public List<CustomerDto> getAllCustomer(){
        return customerDtoConverter.convert(customerRepository.findAll());
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

    public static Customer createCustomerFromRequest(CreateCustomerRequest request) {
        if (isInputValid(request)) {
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
        } else {
            throw new InvalidInputException("Invalid input data");
        }
    }

    private static boolean isInputValid(CreateCustomerRequest request) {
        return request.getFirstName() != null &&
                request.getLastName() != null &&
                request.getEmail() != null &&
                request.getPhoneNumber() != null &&
                request.getPassword() != null;
    }

    public CustomerDto updateCustomer(String email, UpdateCustomerRequest updateCostumerRequest){
        Optional<Customer> customerOptional = findCustomerByEmail(email);
        if (customerOptional.isPresent()) {
        Customer customer= customerOptional.get();
        if(!customer.isActive()){
            logger.warn(String.format("The customer wanted update is not active!, user mail :%s",email));
            throw new IsNotActiveException("The customer wanted update is not active!");
       }
        customer.setFirstName(updateCostumerRequest.getFirstName());
        customer.setLastName(updateCostumerRequest.getLastName());
        customer.setStreetNumber(updateCostumerRequest.getStreetNumber());
        customer.setStreetName(updateCostumerRequest.getStreetName());
        customer.setPostalCode(updateCostumerRequest.getPostalCode());
        customer.setCity(updateCostumerRequest.getCity());
        customer.setCountry(updateCostumerRequest.getCountry());
        customer.setPhoneNumber(updateCostumerRequest.getPhoneNumber());
        Customer updatedCustomer = customerRepository.save(customer);
        return customerDtoConverter.convert(updatedCustomer);
        }
        else{
            throw new EmailNotFoundException("Email not found: " + email);
        }
    }
    public void deactivateCustomer(String email){
            changeActivateCustomer(email,false);
    }
    public void activeCustomer(String email){
        changeActivateCustomer(email,true);
    }
    private  void changeActivateCustomer(String email,Boolean isActive){
        Optional<Customer> customerOptional = findCustomerByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer= customerOptional.get();
            Customer updatedCustomer = new Customer(
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getStreetNumber(),
                    customer.getStreetName(),
                    customer.getPostalCode(),
                    customer.getCity(),
                    customer.getCountry(),
                    customer.getPhoneNumber(),
                    isActive
            );
            customerRepository.save(updatedCustomer);
        }
    }

    public void deleteCustomer(String email){

        if(doesCostumerExist(email)){
            customerRepository.deleteById(email);
            throw new ResponseStatusException(HttpStatus.OK, "customer deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "customer no found");
    }
    private  boolean doesCostumerExist(String email){
        return customerRepository.existsById(email);
    }
}
