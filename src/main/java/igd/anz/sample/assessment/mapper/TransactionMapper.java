package igd.anz.sample.assessment.mapper;

import igd.anz.sample.assessment.api.TransactionResponse;
import igd.anz.sample.assessment.controller.AccountController;
import igd.anz.sample.assessment.enums.TransactionType;
import igd.anz.sample.assessment.repository.dao.Account;
import igd.anz.sample.assessment.repository.dao.Transaction;
import igd.anz.sample.assessment.util.CommonUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionMapper {

    public List<TransactionResponse> mapToTransactionResponse(List<Transaction> transactionList) {
        List<TransactionResponse> response = new ArrayList<>();

        transactionList.forEach(transaction ->
                        response.add(TransactionResponse.builder().accountNumber(transaction.getAccount().getAccountNumber())
                                .AccountName(transaction.getAccount().getAccountName())
                                .valueDate(CommonUtils.dateToStringwithmonthNameFormat(transaction.getValueDate()))
                                .currency(transaction.getAccount().getCurrency().getName())
                                .debitAmount(transaction.getTransactionType().equals(TransactionType.DEBIT) ? transaction.getAmount() : 0.00)
                                .creditAmount(transaction.getTransactionType().equals(TransactionType.CREDIT) ? transaction.getAmount() : 0.00)
                                .transactionType(transaction.getTransactionType().name())
                                .transactionNarrative(transaction.getTransactionNarrative()).build().add(linkTo(methodOn(AccountController.class)
                                        .getOwnerAccounts(transaction.getAccount().getUser().getUserId().toString())).withSelfRel()))
                );


        return response;

    }

    public List<TransactionResponse> mapToTransactionResponse(Account account, List<Transaction> tansactions) {
        List<TransactionResponse> response = new ArrayList<>();

        tansactions.forEach(transaction ->
                response.add(TransactionResponse.builder().accountNumber(account.getAccountNumber())
                        .AccountName(account.getAccountName())
                        .valueDate(CommonUtils.dateToStringwithmonthNameFormat(transaction.getValueDate()))
                        .currency(transaction.getAccount().getCurrency().getName())
                        .debitAmount(transaction.getTransactionType().equals(TransactionType.DEBIT) ? transaction.getAmount() : 0.00)
                        .creditAmount(transaction.getTransactionType().equals(TransactionType.CREDIT) ? transaction.getAmount() : 0.00)
                        .transactionType(transaction.getTransactionType().name())
                        .transactionNarrative(transaction.getTransactionNarrative()).build())
        );


        return response;

    }
}
