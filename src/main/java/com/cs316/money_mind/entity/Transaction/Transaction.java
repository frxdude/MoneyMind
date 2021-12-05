package com.cs316.money_mind.entity.Transaction;

import com.cs316.money_mind.entity.Audit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Transaction
 *
 * @author Sainjargal Ishdorj
 **/

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Transaction extends Audit {

    @Id
    @SequenceGenerator(name = "transactionSeq", sequenceName = "TRANSACTION_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "transactionSeq")
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "VALUE")
    private Double value;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

}
