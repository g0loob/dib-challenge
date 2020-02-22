package com.example.dibchallenge.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException() {
        super();
    }

    public BeerNotFoundException(String message) {
        super(message);
    }
}
