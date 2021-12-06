package com.cs316.money_mind.service;

import com.cs316.money_mind.dto.request.transaction.TransactionRequest;
import com.cs316.money_mind.entity.transaction.Transaction;
import com.cs316.money_mind.exception.BusinessException;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * TransactionService
 *
 * @author Sainjargal Ishdorj
 **/

public interface TransactionService {

    Page<Transaction> list(int page, int size, int categoryId, LocalDateTime from, LocalDateTime to, String type, HttpServletRequest req);

    Transaction get(Long id, HttpServletRequest req) throws BusinessException;

    void add(TransactionRequest addRequest, HttpServletRequest req);

    Transaction update(Long id, TransactionRequest updateRequest, HttpServletRequest req) throws BusinessException;

    void delete(Long id, HttpServletRequest req) throws BusinessException;

}
