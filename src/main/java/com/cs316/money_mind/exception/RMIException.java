package com.cs316.money_mind.exception;

import com.cs316.money_mind.model.ErrorDetail;

import java.util.List;

/**
 * RMIException
 *
 * @author Sainjargal Ishdorj
 **/

public class RMIException extends Exception {

    public List<ErrorDetail> errorDetails;

    public String reason;

    public RMIException() {
    }

    public RMIException(String message) {
        super(message);
    }

    public RMIException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

}
