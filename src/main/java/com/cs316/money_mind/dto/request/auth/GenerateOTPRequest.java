package com.cs316.money_mind.dto.request.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * AuthRequest
 *
 * @author Sainjargal Ishdorj
 **/

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class GenerateOTPRequest {

    @ApiModelProperty(notes = "Email value", name = "value", required = true, value = "Blabla21@gmail.com")
    @NotBlank(message = "{val.not.null}")
    private String value;

}
