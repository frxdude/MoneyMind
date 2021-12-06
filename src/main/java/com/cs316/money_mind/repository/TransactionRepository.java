package com.cs316.money_mind.repository;

import com.cs316.money_mind.entity.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

/**
 * TransactionRepository
 *
 * @author Sainjargal Ishdorj
 **/

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Page<Transaction> findAll(Specification<Transaction> transactionSpecification, Pageable pageable);

}
