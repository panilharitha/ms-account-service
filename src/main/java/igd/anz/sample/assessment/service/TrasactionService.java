package igd.anz.sample.assessment.service;

import igd.anz.sample.assessment.api.TransactionResponse;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface TrasactionService {

    CollectionModel<TransactionResponse> getTransactionList(String accountId);
}
