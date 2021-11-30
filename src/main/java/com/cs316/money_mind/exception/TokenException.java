package com.cs316.money_mind.exception;

import org.springframework.http.HttpStatus;

/**
 * TokenException
 *
 * @author Sainjargal Ishdorj
 **/

public class TokenException extends RunTimeException {

    public String message;

    public HttpStatus status;

    public TokenException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

}
