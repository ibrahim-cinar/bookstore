package com.cinarcorp.bookstore.bookstore.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreIdNotFoundException extends RuntimeException {

    public GenreIdNotFoundException(String message) {
        super(message);
    }
}
