package com.cs316.money_mind.dto.request.user;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * ResetPasswordRequest
 *
 * @author Sainjargal Ishdorj
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResetPasswordRequest {

    @NotBlank(message = "{val.not.null}")
    @Length(max = 20, min = 4, message = "{val.length}")
    private String password;

}
