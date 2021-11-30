package com.cs316.money_mind.controller;

import com.cs316.money_mind.dto.request.auth.ConfirmOTPRequest;
import com.cs316.money_mind.dto.request.auth.GenerateOTPRequest;
import com.cs316.money_mind.dto.request.user.LoginRequest;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.exception.TokenException;
import com.cs316.money_mind.service.AuthService;
import com.cs316.money_mind.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

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

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest req) throws
            BusinessException {
        return ResponseEntity.ok(service.login(loginRequest, req));
    }

    @RequestMapping(value = "exchange", method = RequestMethod.GET)
    public ResponseEntity<Object> exchangeToken(@RequestParam String refreshToken, HttpServletRequest req) throws
            TokenException {
        return ResponseEntity.ok(service.exchangeToken(refreshToken, req));
    }

    @RequestMapping(value = "otp/send", method = RequestMethod.POST)
    public ResponseEntity<Object> sendOTP(@Valid @RequestBody GenerateOTPRequest otpRequest,
                                          HttpServletRequest req) throws BusinessException, UnsupportedEncodingException {
        return ResponseEntity.ok(service.sendOtp(otpRequest, req));
    }

    @RequestMapping(value = "otp/confirm", method = RequestMethod.POST)
    public ResponseEntity<Object> confirmOTP(@Valid @RequestBody ConfirmOTPRequest otpRequest, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.confirmOTP(otpRequest, req));
    }
}
