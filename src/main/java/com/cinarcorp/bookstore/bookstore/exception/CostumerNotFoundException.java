package com.cinarcorp.bookstore.bookstore.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CostumerNotFoundException extends RuntimeException {

    public CostumerNotFoundException(String message) {
        super(message);
    }

}
