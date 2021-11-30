package com.cs316.money_mind.dto.request.user;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * LoginRequest
 *
 * @author Sainjargal Ishdorj
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginRequest {

    @NotBlank(message = "{val.not.null}")
    @Pattern(regexp = "^(.+)@(.+)$", message = "{val.email}")
    private String email;

    private String password;

}
