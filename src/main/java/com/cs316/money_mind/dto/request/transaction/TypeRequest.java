package com.cs316.money_mind.dto.request.transaction;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Transaction type", name = "type", required = true, value = "revenue")
    @Pattern(regexp = "^revenue$|^expense$", message = "{val.type}")
    private String type;
}
