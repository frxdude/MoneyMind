package com.cs316.money_mind.dto.request.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * RegisterRequest
 *
 * @author Sainjargal Ishdorj
 **/

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "{val.not.null}")
    @Pattern(regexp = "^(.+)@(.+)$", message = "{val.email}")
    private String email;

    @NotBlank(message = "{val.not.null}")
    private String password;

    @NotNull(message = "{val.not.null}")
    private Integer age;

}
