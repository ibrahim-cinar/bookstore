package com.cinarcorp.bookstore.bookstore.controller;

import com.cinarcorp.bookstore.bookstore.dto.CostumerDto;
import com.cinarcorp.bookstore.bookstore.service.CostumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/costumer")
public class CostumerController {
    private final CostumerService costumerService;

    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }
    @GetMapping("/{username}")
    public ResponseEntity<CostumerDto> getCostumerByUsername(@PathVariable String username){
        return ResponseEntity.ok(costumerService.getCostumerByUsername(username));
    }
    @GetMapping("/{email}")
    public ResponseEntity<CostumerDto> getCostumerByEmail(@PathVariable String email){
        return ResponseEntity.ok(costumerService.getCostumerByEmail(email));
    }
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CostumerDto> getCostumerPhoneNumber(@PathVariable String phoneNumber){
        return ResponseEntity.ok(costumerService.getCostumerPhoneNumber(phoneNumber));
    }
}
