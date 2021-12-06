package com.cs316.money_mind.controller;

import com.cs316.money_mind.dto.request.auth.RegisterRequest;
import com.cs316.money_mind.dto.request.user.ResetPasswordRequest;
import com.cs316.money_mind.dto.response.ErrorResponse;
import com.cs316.money_mind.dto.response.auth.AuthResponse;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

/**
 * UserController
 *
 * @author Sainjargal Ishdorj
 **/

@Api(tags = "User")
@RestController
@RequestMapping("users")
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ApiOperation(value = "Хэрэглэгч нууц үгээ сэргээх", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = AuthResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "{} Object буцна"),
    })
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEMP')")
    public ResponseEntity<Object> resetPassword(@Valid @RequestBody ResetPasswordRequest resetRequest,
                                                HttpServletRequest req) throws BusinessException {
        service.resetPassword(resetRequest, req);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Хэрэглэгч OTP баталгаажуулсны дараах ирсэн Token-г ашиглан бүртгүүлэх | TEMP", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = AuthResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "{} Object буцна"),
    })
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEMP')")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest registerRequest,
                                           HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.register(registerRequest, req));
    }
}

