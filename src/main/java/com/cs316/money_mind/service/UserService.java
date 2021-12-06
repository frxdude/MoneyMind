package com.cs316.money_mind.service;

import com.cs316.money_mind.dto.request.auth.RegisterRequest;
import com.cs316.money_mind.dto.request.user.ResetPasswordRequest;
import com.cs316.money_mind.dto.response.auth.AuthResponse;
import com.cs316.money_mind.entity.User;
import com.cs316.money_mind.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * UserService
 *
 * @author Sainjargal Ishdorj
 **/

public interface UserService {

    void resetPassword(ResetPasswordRequest resetRequest, HttpServletRequest req) throws BusinessException;

    AuthResponse register(RegisterRequest authRequest, HttpServletRequest req) throws BusinessException;

    User findUser(HttpServletRequest req) throws BusinessException;
}
