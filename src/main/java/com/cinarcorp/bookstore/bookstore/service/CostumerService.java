package com.cinarcorp.bookstore.bookstore.service;

import com.cinarcorp.bookstore.bookstore.dto.CostumerDto;
import com.cinarcorp.bookstore.bookstore.dto.CreateUserRequest;
import com.cinarcorp.bookstore.bookstore.dto.UpdateUserRequest;
import com.cinarcorp.bookstore.bookstore.dto.converter.CostumerDtoConverter;
import com.cinarcorp.bookstore.bookstore.exception.BookNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.CostumerNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.EmailNotFoundException;
import com.cinarcorp.bookstore.bookstore.exception.PhoneNumberNotFoundException;
import com.cinarcorp.bookstore.bookstore.model.Costumer;
import com.cinarcorp.bookstore.bookstore.repository.CostumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {
    private final CostumerRepository costumerRepository;
    private final CostumerDtoConverter costumerDtoConverter;

    public CostumerService(CostumerRepository costumerRepository, CostumerDtoConverter costumerDtoConverter) {
        this.costumerRepository = costumerRepository;
        this.costumerDtoConverter = costumerDtoConverter;
    }

    public List<Costumer> getAllCostumer(){
        return costumerRepository.findAll();
    }
    public CostumerDto getCostumerById(String id){
        var costumer = costumerRepository.findById(id).orElseThrow(()->new CostumerNotFoundException("Costumer not found"));
        return  costumerDtoConverter.convert(costumer);
    }

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

    public CostumerDto createNewCostumer(CreateUserRequest createUserRequest){
        Costumer costumer =new Costumer(
                createUserRequest.getEmail(),createUserRequest.getFirstName(),
                createUserRequest.getLastName(), createUserRequest.getPassword(),
                createUserRequest.getStreetNumber(), createUserRequest.getStreetName(),
                createUserRequest.getPostalCode(), createUserRequest.getCity(),
                createUserRequest.getCountry(), createUserRequest.getPhoneNumber(),
                createUserRequest.isActive()
                );
        return costumerDtoConverter.convert(costumerRepository.save(costumer));
    }
    public CostumerDto updateCostumer(String email,UpdateUserRequest updateUserRequest){
        Optional<Costumer> costumerOptional = findCostumerByEmail(email);
        if (costumerOptional.isPresent()) {
        Costumer costumer = costumerOptional.get();
        costumer.setFirstName(updateUserRequest.getFirstName());
        costumer.setLastName(updateUserRequest.getLastName());
        costumer.setStreetNumber(updateUserRequest.getStreetNumber());
        costumer.setStreetName(updateUserRequest.getStreetName());
        costumer.setPostalCode(updateUserRequest.getPostalCode());
        costumer.setCity(updateUserRequest.getCity());
        costumer.setCountry(updateUserRequest.getCountry());
        costumer.setPhoneNumber(updateUserRequest.getPhoneNumber());
        costumer.setActive(updateUserRequest.isActive());
        Costumer updatedCostumer = costumerRepository.save(costumer);
        return costumerDtoConverter.convert(updatedCostumer);

        }else{
            throw new EmailNotFoundException("email not found"+email);

        }
    }
    public void deleteCostumer(String email){
        costumerRepository.deleteById(email);
    }
}
