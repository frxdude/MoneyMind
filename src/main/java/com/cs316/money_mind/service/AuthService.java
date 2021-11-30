package com.cs316.money_mind.service;

import com.cs316.money_mind.dto.request.auth.ConfirmOTPRequest;
import com.cs316.money_mind.dto.request.auth.GenerateOTPRequest;
import com.cs316.money_mind.dto.request.user.LoginRequest;
import com.cs316.money_mind.dto.response.auth.AuthResponse;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.exception.TokenException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * AuthService
 *
 * @author Sainjargal Ishdorj
 **/

public interface AuthService {

    AuthResponse login (LoginRequest loginRequest, HttpServletRequest req) throws BusinessException;

    AuthResponse exchangeToken (String refreshToken, HttpServletRequest req) throws TokenException;

    HashMap<String, String> sendOtp(GenerateOTPRequest otpRequest, HttpServletRequest req) throws BusinessException, UnsupportedEncodingException;

    HashMap<String, String> confirmOTP(ConfirmOTPRequest otpRequest, HttpServletRequest req) throws BusinessException;

}
