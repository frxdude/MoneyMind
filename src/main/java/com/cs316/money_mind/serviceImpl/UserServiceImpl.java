package com.cs316.money_mind.serviceImpl;

import com.cs316.money_mind.dto.request.auth.RegisterRequest;
import com.cs316.money_mind.dto.request.user.ResetPasswordRequest;
import com.cs316.money_mind.entity.User;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.jwt.JwtTokenProvider;
import com.cs316.money_mind.service.UserService;
import com.cs316.money_mind.dto.response.auth.AuthResponse;
import com.cs316.money_mind.entity.Role;
import com.cs316.money_mind.helper.Localization;
import com.cs316.money_mind.repository.UserRepository;
import com.cs316.money_mind.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;

/**
 * UserServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class UserServiceImpl implements UserService {

    PasswordEncoder encoder;
    UserRepository repository;
    JwtTokenProvider jwtTokenProvider;
    Localization localization;
    AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(PasswordEncoder encoder, UserRepository repository, JwtTokenProvider jwtTokenProvider, Localization localization) {
        this.encoder = encoder;
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.localization = localization;
    }

    /**
     * @param resetRequest {@link ResetPasswordRequest} DTO
     * @param req          servlet request
     * @throws BusinessException when user not found
     * @author Sainjargal Ishdorj
     **/
    public void resetPassword(ResetPasswordRequest resetRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[resetPassword][input][" + resetRequest.toString()+ "]");

            User user = repository.findByEmail(req.getRemoteUser())
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));

            user.setPassword(encoder.encode(resetRequest.getPassword()));
            repository.save(user);
            Logger.info(this.getClass().getName(), "[resetPassword][output][" + "" + "]");
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[resetPassword][" + ex.reason + "]");
            throw ex;
        }  catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[resetPassword][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req servlet request
     * @return {@link AuthResponse}
     * @throws BusinessException when User already exists
     * @author Sainjargal Ishdorj
     **/

    public AuthResponse register(RegisterRequest registerRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[register][input][" + registerRequest.toString() + "]");

            Optional<User> optionalUser = repository.findByEmail(registerRequest.getEmail());

            if (optionalUser.isPresent())
                throw new BusinessException(localization.getMessage("user.already"), "User already exists");

            int age = registerRequest.getAge();

            Role role = age < 15 ? Role.ROLE_CHILDREN :
                    age < 25 ? Role.ROLE_YOUTH :
                            age < 65 ? Role.ROLE_ADULT :
                                    Role.ROLE_SENIOR;

            User user = repository.save(User.builder()
                    .isActive(true)
                    .email(registerRequest.getEmail())
                    .password(encoder.encode(registerRequest.getPassword()))
                    .roles(Collections.singletonList(role))
                    .build());

            AuthResponse authResponse = AuthResponse.builder()
                    .accessToken(jwtTokenProvider.createToken(user.getEmail(), user.getRoles().get(0), true))
                    .refreshToken(jwtTokenProvider.createToken(user.getEmail(), user.getRoles().get(0), false))
                    .build();

            Logger.info(this.getClass().getName(), "[register][output][" + "" + "]");
            return authResponse;
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[register][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[register][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    /**
     * @param req servlet request
     * @return {@link User}
     * @throws BusinessException when User not found
     * @author Sainjargal Ishdorj
     **/

    public User findUser(HttpServletRequest req) throws BusinessException {
        try {
            return repository.findByEmail(req.getRemoteUser())
                    .orElseThrow(() -> new BusinessException(localization.getMessage("user.not.found"), "User not found"));
        } catch (BusinessException ex) {
            Logger.warn(getClass().getName(), "[findUser][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[findUser][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }
}
