package com.cs316.money_mind.serviceImpl;

import com.cs316.money_mind.dto.request.transaction.TransactionRequest;
import com.cs316.money_mind.entity.transaction.Transaction;
import com.cs316.money_mind.entity.transaction.Type;
import com.cs316.money_mind.exception.BusinessException;
import com.cs316.money_mind.helper.Localization;
import com.cs316.money_mind.helper.specification.SearchCriteria;
import com.cs316.money_mind.helper.specification.TransactionSpecification;
import com.cs316.money_mind.repository.TransactionRepository;
import com.cs316.money_mind.service.TransactionService;
import com.cs316.money_mind.util.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * TransactionServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository repository;
    Localization localization;
    TransactionSpecification categorySpec, fromSpec, toSpec, typeSpec;

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository, Localization localization, TransactionSpecification fromSpec, TransactionSpecification toSpec, TransactionSpecification categorySpec) {
        this.repository = repository;
        this.localization = localization;
        this.categorySpec = categorySpec;
        this.fromSpec = fromSpec;
        this.toSpec = toSpec;
    }

    @Override
    public Page<Transaction> list(int page, int size, int categoryId, LocalDateTime from, LocalDateTime to, String type, HttpServletRequest req) {
        try {
            Logger.info(this.getClass().getName(), "[list][input][page=" + page + ", size=" + size + ", categoryId=" + categoryId + ", type=" + type + ", from=" + from + ", to=" + to + "]");

            typeSpec = StringUtils.isNotBlank(type)
                    ? new TransactionSpecification(new SearchCriteria("type", ":", type.equalsIgnoreCase("revenue") ? Type.REVENUE : Type.EXPENSE))
                    : new TransactionSpecification();

            toSpec = from != null
                    ? new TransactionSpecification(new SearchCriteria("dateTime", "<=>", from, to != null ? to : LocalDateTime.now()))
                    : new TransactionSpecification();

            categorySpec = categoryId > 0
                    ? new TransactionSpecification(new SearchCriteria("categoryId", ":", (long) categoryId))
                    : new TransactionSpecification();

            Page<Transaction> result = repository.findAll(categorySpec.and(fromSpec.and(toSpec.and(typeSpec)))
                    , PageRequest.of(page, size, Sort.by("id").descending()));
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
                    .type(addRequest.getType().equals("revenue")
                            ? Type.REVENUE
                            : Type.EXPENSE)
                    .categoryId(addRequest.getCategoryId())
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
                    ? Type.REVENUE
                    : Type.EXPENSE);
            transaction.setValue(updateRequest.getValue());
            transaction.setDateTime(updateRequest.getDateTime());
            Logger.info(this.getClass().getName(), "[update][output][" + transaction.toString() + "]");
            return repository.save(transaction);
        } catch (BusinessException ex) {
            Logger.warn(this.getClass().getName(), "[update][" + ex.reason + "]");
            throw ex;
        } catch (Exception ex) {
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
