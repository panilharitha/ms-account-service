package igd.anz.assessment.mapper;

import igd.anz.assessment.api.TransactionResponse;
import igd.anz.assessment.enums.TransactionType;
import igd.anz.assessment.repository.entity.Transaction;
import igd.anz.assessment.util.CommonUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class TransactionMapper {



    public List<TransactionResponse> mapToTransactionResponse(List<Transaction> tansactions) {
        List<TransactionResponse> response = new ArrayList<>();

        tansactions.forEach(transaction ->
                response.add(TransactionResponse.builder().accountNumber(transaction.getAccount().getAccountNumber())
                        .AccountName(transaction.getAccount().getAccountName())
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
