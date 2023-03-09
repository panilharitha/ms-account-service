package igd.anz.assessment.repository.entity;

import com.sun.istack.NotNull;
import igd.anz.assessment.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    @NotNull
    private String accountNumber;

    @Column(name = "account_name")
    @NotNull
    private String accountName;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType accountType;

    @Column(name = "balance_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date balanceDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @NotNull
    private Currency currency;

    @Column(name = "opaning_balance")
    private Double opaningBalance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account")
    @Builder.Default
    private List<Transaction> transactions = new ArrayList<>();


}
