package com.cs316.money_mind.serviceImpl;

import com.cs316.money_mind.dto.request.transaction.TransactionRequest;
import com.cs316.money_mind.entity.Transaction.Transaction;
import com.cs316.money_mind.entity.Transaction.TransactionType;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.helper.Localization;
import com.cs316.money_mind.repository.TransactionRepository;
import com.cs316.money_mind.service.TransactionService;
import com.cs316.money_mind.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * TransactionServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository repository;
    Localization localization;

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository, Localization localization) {
        this.repository = repository;
        this.localization = localization;
    }

    @Override
    public Page<Transaction> list(int page, int size, HttpServletRequest req) {
        try {
            Logger.info(this.getClass().getName(), "[list][input][page=" + page + ", size=" + size + "]");
            Page<Transaction> result = repository.findAll(PageRequest.of(page, size));
            Logger.info(this.getClass().getName(), "[list][output][size=" + result.getTotalElements() + "]");
            return result;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[list][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    public Transaction get(Long id, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[get][input][id=" + id + "]");
            Transaction transaction = repository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Transaction data not found"));
            Logger.info(this.getClass().getName(), "[get][output][" + transaction.toString() + "]");
            return transaction;
        } catch (BusinessException ex) {
            Logger.warn(this.getClass().getName(), "[get][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[get][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    public void add(TransactionRequest addRequest, HttpServletRequest req) {
        try {
            Logger.info(this.getClass().getName(), "[add][input][" + addRequest.toString() + "]");
            Transaction transaction = Transaction.builder()
                    .type(addRequest.getType().equals("revenue") ? TransactionType.REVENUE : TransactionType.EXPENSE)
                    .dateTime(addRequest.getDateTime())
                    .value(addRequest.getValue())
                    .build();
            repository.save(transaction);
            Logger.info(this.getClass().getName(), "[add][output][" + transaction.toString() + "]");
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[add][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    public Transaction update(Long id, TransactionRequest updateRequest, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[update][input][id=" + id + ", " + updateRequest.toString() + "]");
            Transaction transaction = repository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Transaction data not found"));
            transaction.setType(updateRequest.getType().equals("revenue")
                    ? TransactionType.REVENUE
                    : TransactionType.EXPENSE);
            transaction.setValue(updateRequest.getValue());
            transaction.setDateTime(updateRequest.getDateTime());
            Logger.info(this.getClass().getName(), "[update][output][" + transaction.toString() + "]");
            return repository.save(transaction);
        } catch (BusinessException ex) {
            Logger.warn(this.getClass().getName(), "[update][" + ex.reason + "]");
            throw ex;
        }  catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[update][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }

    public void delete(Long id, HttpServletRequest req) throws BusinessException {
        try {
            Logger.info(this.getClass().getName(), "[delete][input][id=" + id + "]");
            repository.delete(repository.findById(id)
                    .orElseThrow(() -> new BusinessException(localization.getMessage("data.not.found"), "Transaction data not found")));
            Logger.info(this.getClass().getName(), "[delete][output][]");
        } catch (BusinessException ex) {
            Logger.warn(this.getClass().getName(), "[delete][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
            Logger.fatal(this.getClass().getName(), "[delete][output][" + ex.getMessage() + "]", ex);
            throw ex;
        }
    }
}
