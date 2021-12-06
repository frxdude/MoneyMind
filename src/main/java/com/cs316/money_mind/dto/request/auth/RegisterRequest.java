package com.cs316.money_mind.dto.request.auth;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Email", name = "value", required = true, value = "Blabla21@gmail.com")
    @NotBlank(message = "{val.not.null}")
    @Pattern(regexp = "^(.+)@(.+)$", message = "{val.email}")
    private String email;

    @ApiModelProperty(notes = "Password", name = "password", required = true, value = "password321")
    @NotBlank(message = "{val.not.null}")
    private String password;

    @ApiModelProperty(notes = "User age", name = "age", required = true, value = "27")
    @NotNull(message = "{val.not.null}")
    private Integer age;

}
