package com.cs316.money_mind.controller;

import com.cs316.money_mind.dto.request.auth.RegisterRequest;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @RequestMapping(value = "{userId}/reset_password", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(@PathVariable(value = "userId") Long id,
                                                HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.resetPassword(id, req));
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_TEMP')")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest registerRequest,
                                                HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.register(registerRequest, req));
    }
}

