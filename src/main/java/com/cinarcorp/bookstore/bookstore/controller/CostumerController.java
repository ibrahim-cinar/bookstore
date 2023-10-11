package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.CustomerDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateCustomerRequest;
import com.cinarcorp.bookstore.bookstore.dto.UpdateCustomerRequest;
import com.cinarcorp.bookstore.bookstore.model.Customer;
import com.cinarcorp.bookstore.bookstore.service.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/customer")
public class CostumerController {
    private final CustomerService customerService;

    public CostumerController(CustomerService costumerService) {
        this.customerService = costumerService;
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCostumer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCostumerById(@PathVariable String id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email){
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<CustomerDto> getCustomerPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(customerService.getCustomerPhoneNumber(phoneNumber));
    }
    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return  ResponseEntity.ok(customerService.createNewCustomer(createCustomerRequest));
    }
    @PutMapping("/{email}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String email, @RequestBody UpdateCustomerRequest updateCostumerRequest){
        return  ResponseEntity.ok(customerService.updateCustomer(email,updateCostumerRequest));
    }
    @PatchMapping("/{email}")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable("email") String email){
        customerService.deactivateCustomer(email);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{email}/active")
    public ResponseEntity<Void> activeCustomer(@PathVariable("email") String email){
        customerService.activeCustomer(email);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{email}")
    public  ResponseEntity<CustomerDto> deleteCustomer(@PathVariable String email){
        customerService.deleteCustomer(email);
        return ResponseEntity.ok().build();
    }

}
