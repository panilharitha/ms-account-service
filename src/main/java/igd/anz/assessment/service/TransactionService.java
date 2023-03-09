package igd.anz.assessment.service;

import igd.anz.assessment.api.TransactionResponse;
import org.springframework.hateoas.CollectionModel;

public interface TransactionService {

    CollectionModel<TransactionResponse> getTransactionList(String userId, String accountNumber);
}
