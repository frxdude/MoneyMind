package com.cs316.money_mind.exception;

public class UserAlreadyExistException extends RunTimeException{
    String message;
    public UserAlreadyExistException(String message){
            this.message = message;
    }
}
