package com.cs316.money_mind.dto.request.auth;

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

    @NotNull(message = "{val.not.null}")
    @Length(min = 4, max = 4, message = "{val.length}")
    private String otp;

}
