package com.cs316.money_mind.helper.specification;

import com.cs316.money_mind.entity.transaction.Transaction;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Component
public class TransactionSpecification implements Specification<Transaction> {

    private SearchCriteria criteria;

    public TransactionSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public TransactionSpecification() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey("id");
        searchCriteria.setOperation(">");
        searchCriteria.setValue(0);
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate
            (@NonNull Root<Transaction> root,
             @NonNull CriteriaQuery<?> query,
             @NonNull CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThan(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThan(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return builder.equal(
                    root.get(criteria.getKey()), criteria.getValue());
        }
        else if(criteria.getOperation().equalsIgnoreCase("<=>")) {
            return builder.between(
                    root.get(criteria.getKey()), (LocalDateTime)criteria.getValue(), (LocalDateTime)criteria.getValue2());
        }
        else if(criteria.getOperation().equalsIgnoreCase(".%")) {
            return builder.like(
                    root.get(criteria.getKey()), criteria.getValue().toString() + "%");
        }
        else if(criteria.getOperation().equalsIgnoreCase("%.")) {
            return builder.like(
                    root.get(criteria.getKey()),"%" + criteria.getValue().toString());
        }
        else if(criteria.getOperation().equalsIgnoreCase("%%")) {
            return builder.like(
                    root.get(criteria.getKey()), "%" + criteria.getValue().toString() + "%");
        }
        else if(criteria.getOperation().equalsIgnoreCase(".i%")) {
            return builder.like(
                builder.lower(
                        root.get(criteria.getKey())),criteria.getValue().toString().toLowerCase() + "%");
        }
        return null;
    }
}
