package com.cs316.money_mind.helper.specification;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private Object value2;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, String operation, Object value, Object value2) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.value2 = value2;
    }
}