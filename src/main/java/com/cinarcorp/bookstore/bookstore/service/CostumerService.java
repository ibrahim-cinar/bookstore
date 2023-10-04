package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.CostumerDto;
import com.cinarcorp.bookstore.bookstore.dto.converter.CostumerDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.BookNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.CostumerNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.EmailNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.PhoneNumberNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Costumer;
import com.cinarcorp.bookstore.bookstore.repository.CostumerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService {
    private final CostumerRepository costumerRepository;
    private final CostumerDtoConverter costumerDtoConverter;

    public CostumerService(CostumerRepository costumerRepository, CostumerDtoConverter costumerDtoConverter) {
        this.costumerRepository = costumerRepository;
        this.costumerDtoConverter = costumerDtoConverter;
    }
    /*                                     Username                                                    */
    protected Optional<Costumer> findCostumerByUsername(String username) {
        return costumerRepository.findCostumerByUsername(username);
    }
    public CostumerDto getCostumerByUsername(String username){
        var costumerUsername = findCostumerByUsername(username).orElseThrow(()->new CostumerNotFoundException("costumer not found"+username));
        return costumerDtoConverter.convert(costumerUsername);
    }
    /*                                     Username                                                    */


    /*                                     email                                                    */

    protected Optional<Costumer> findCostumerByEmail(String email) {
        return costumerRepository.findCostumerByEmail(email);
    }
    public CostumerDto getCostumerByEmail(String email){
        var costumerEmail = findCostumerByEmail(email).orElseThrow(()->new EmailNotFoundException("email not found"+email));
        return costumerDtoConverter.convert(costumerEmail);
    }
    /*                                     email                                                    */


    /*                                     phoneNumber                                                    */

    protected Optional<Costumer> findCostumerByPhoneNumber(String phoneNumber) {
        return costumerRepository.findCostumerByPhoneNumber(phoneNumber);
    }
    public CostumerDto getCostumerPhoneNumber(String phoneNumber){
        var costumerPhoneNumber = findCostumerByPhoneNumber(phoneNumber).orElseThrow(()->new PhoneNumberNotFoundException("phoneNumber not found"+phoneNumber));
        return costumerDtoConverter.convert(costumerPhoneNumber);
    }
    /*                                     phoneNumber                                                    */

}
