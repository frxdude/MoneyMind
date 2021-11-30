package com.cs316.money_mind.constant;

/**
 * ExceptionType
 *
 * @author Sainjargal Ishdorj
 **/

public enum  ExceptionType {

    VALIDATION("validation"),

    NOT_FOUND("not found"),

    BUSINESS("business"),

    RUN_TIME("run time"),

    FATAL("fatal");

    private final String value;

    ExceptionType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
