package com.cs316.money_mind.exception;

import com.cs316.money_mind.model.ErrorDetail;

import java.util.List;

/**
 * RunTimeException
 *
 * @author Sainjargal Ishdorj
 **/

public class RunTimeException extends Exception{

    public List<ErrorDetail> errorDetails;

    public String reason;

    public RunTimeException() {
    }

    public RunTimeException(String message) {
        super(message);
    }

    public RunTimeException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

}
