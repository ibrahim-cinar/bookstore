package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.CostumerDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateUserRequest;
import com.cinarcorp.bookstore.bookstore.dto.UpdateUserRequest;
import com.cinarcorp.bookstore.bookstore.model.Costumer;
import com.cinarcorp.bookstore.bookstore.service.CostumerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/costumer")
public class CostumerController {
    private final CostumerService costumerService;

    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }
    @GetMapping
    public ResponseEntity<List<Costumer>> getAllCostumer(){
        return ResponseEntity.ok(costumerService.getAllCostumer());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CostumerDto> getCostumerById(@PathVariable String id){
        return ResponseEntity.ok(costumerService.getCostumerById(id));
    }
    @GetMapping("/{email}")
    public ResponseEntity<CostumerDto> getCostumerByEmail(@PathVariable String email){
        return ResponseEntity.ok(costumerService.getCostumerByEmail(email));
    }
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CostumerDto> getCostumerPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(costumerService.getCostumerPhoneNumber(phoneNumber));
    }
    @PostMapping
    public ResponseEntity<CostumerDto> createNewCostumer(@RequestBody CreateUserRequest createUserRequest){
        return  ResponseEntity.ok(costumerService.createNewCostumer(createUserRequest));
    }
    @PutMapping("/{email}")
    public ResponseEntity<CostumerDto> updateCostumer(@PathVariable String email,@RequestBody UpdateUserRequest updateUserRequest){
        return  ResponseEntity.ok(costumerService.updateCostumer(email,updateUserRequest));
    }
    @DeleteMapping("/{email}")
    public  ResponseEntity<CostumerDto> deleteCostumer(@PathVariable String email){
        costumerService.deleteCostumer(email);
        return ResponseEntity.ok().build();
    }

}
