package com.cs316.money_mind.controller;

import com.cs316.money_mind.dto.request.transaction.TransactionRequest;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.service.TransactionService;
import com.cs316.money_mind.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * TransactionController
 *
 * @author Sainjargal Ishdorj
 **/

@Api(tags = "Transaction")
@RestController
@RequestMapping("transactions")
public class TransactionController {

    TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_CHILDREN','ROLE_YOUTH','ROLE_ADULT','ROLE_SENIOR')")
    public ResponseEntity<Object> list(@RequestParam int page, @RequestParam int size, HttpServletRequest req) {
        return ResponseEntity.ok(service.list(page, size, req));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_CHILDREN','ROLE_YOUTH','ROLE_ADULT','ROLE_SENIOR')")
    public ResponseEntity<Object> get(@PathVariable(value = "id") Long id, HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.get(id, req));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_CHILDREN','ROLE_YOUTH','ROLE_ADULT','ROLE_SENIOR')")
    public ResponseEntity<Object> add(@Valid @RequestBody TransactionRequest addRequest, HttpServletRequest req) {
        service.add(addRequest, req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_CHILDREN','ROLE_YOUTH','ROLE_ADULT','ROLE_SENIOR')")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TransactionRequest updateRequest , HttpServletRequest req) throws BusinessException {
        return ResponseEntity.ok(service.update(id, updateRequest, req));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ROLE_CHILDREN','ROLE_YOUTH','ROLE_ADULT','ROLE_SENIOR')")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id, HttpServletRequest req) throws BusinessException {
        service.delete(id, req);
        return ResponseEntity.noContent().build();
    }

}