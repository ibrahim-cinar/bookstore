package com.cinarcorp.bookstore.bookstore.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsNotActiveException extends RuntimeException {

    public IsNotActiveException(String message) {
        super(message);
    }

}