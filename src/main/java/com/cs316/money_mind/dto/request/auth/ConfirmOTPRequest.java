package com.cs316.money_mind.dto.request.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * ConfirmOTPRequest
 *
 * @author Ishdorj Sainjargal
 **/

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ConfirmOTPRequest extends GenerateOTPRequest {

    @ApiModelProperty(notes = "One time password that sent to your email | 4 digit", name = "otp", required = true, value = "8731")
    @NotNull(message = "{val.not.null}")
    @Length(min = 4, max = 4, message = "{val.length}")
    private String otp;

}
