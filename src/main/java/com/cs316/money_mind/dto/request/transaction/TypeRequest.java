package com.cs316.money_mind.dto.request.transaction;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * TypeRequest
 *
 * @author Sainjargal Ishdorj
 **/

@Getter
@Setter
public class TypeRequest {
    @Pattern(regexp = "^revenue$|^expense$", message = "{val.type}")
    private String type;
}
