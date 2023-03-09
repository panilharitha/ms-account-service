package igd.anz.assessment.repository.entity;

import com.sun.istack.NotNull;
import igd.anz.assessment.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull
    private Account account;

    @Column(name = "value_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date valueDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType transactionType;

    @Column(name = "transaction_narrative")
    private String transactionNarrative;


}
