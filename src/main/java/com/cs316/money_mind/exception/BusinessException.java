package com.cs316.money_mind.exception;

import com.cs316.money_mind.model.ErrorDetail;

import java.util.List;

/**
 * BusinessException
 *
 * @author Sainjargal Ishdorj
 **/

public class BusinessException extends Exception {

    public List<ErrorDetail> errorDetails;

    public String reason;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

}
