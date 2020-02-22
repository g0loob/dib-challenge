package com.example.dibchallenge.punk_api_lib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApiReturnedUnexpectedResultException extends RuntimeException {

    public ApiReturnedUnexpectedResultException() {
        super();
    }

    public ApiReturnedUnexpectedResultException(String message) {
        super(message);
    }
}
