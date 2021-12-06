package com.cs316.money_mind.controller;

import com.cs316.money_mind.dto.request.auth.ConfirmOTPRequest;
import com.cs316.money_mind.dto.request.auth.GenerateOTPRequest;
import com.cs316.money_mind.dto.request.user.LoginRequest;
import com.cs316.money_mind.dto.response.ErrorResponse;
import com.cs316.money_mind.dto.response.auth.AuthResponse;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.exception.TokenException;
import com.cs316.money_mind.service.AuthService;
import com.cs316.money_mind.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * AuthController
 *
 * @author Sainjargal Ishdorj
 **/

@Api(tags = "Auth")
@RestController
@RequestMapping("auth")
public class AuthController {

    AuthService service;
    UserService userService;

    @Autowired
    public AuthController(AuthService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @ApiOperation(value = "Нэвтрэх", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = AuthResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "{} Object буцна"),
    })
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest req) throws
            BusinessException {
        return ResponseEntity.ok(service.login(loginRequest, req));
    }

    @ApiOperation(value = "Refresh token-оор Access token авах", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = AuthResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "{} Object буцна"),
    })
    @RequestMapping(value = "exchange", method = RequestMethod.GET)
    public ResponseEntity<Object> exchangeToken(@RequestParam String refreshToken, HttpServletRequest req) throws
            TokenException {
        return ResponseEntity.ok(service.exchangeToken(refreshToken, req));
    }

    @ApiOperation(value = "Хэрэглэгч email-ээ оруулан бүртгүүлэх үеийн нэг удаагийн код авах", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = HashMap.class, message = "{} Object буцна"),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "{} Object буцна"),
    })
    @RequestMapping(value = "otp/send", method = RequestMethod.POST)
    public ResponseEntity<Object> sendOTP(@Valid @RequestBody GenerateOTPRequest otpRequest,
                                          @RequestParam(value = "forgot", required = false) boolean forgot,
                                          HttpServletRequest req) throws BusinessException, UnsupportedEncodingException {
        return ResponseEntity.ok(service.sendOtp(otpRequest, forgot, req));
    }

    @ApiOperation(value = "Хэрэглэгч ирсэн нэг удаагийн кодоо баталгаажуулан бүртгүүлэх үед хэрэглэгдэх Token авах", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = HashMap.class, message = "{} Object буцна"),
            @ApiResponse(code = 400, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 401, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 403, response = ErrorResponse.class, message = "{} Object буцна"),
            @ApiResponse(code = 500, response = ErrorResponse.class, message = "{} Object буцна"),
    })
    @RequestMapping(value = "otp/confirm", method = RequestMethod.POST)
    public ResponseEntity<Object> confirmOTP(@Valid @RequestBody ConfirmOTPRequest otpRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.confirmOTP(otpRequest, req));
    }
}
