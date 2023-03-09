package igd.anz.assessment.service;

import igd.anz.assessment.api.TransactionResponse;
import igd.anz.assessment.controller.AccountController;
import igd.anz.assessment.repository.TransactionRepository;
import igd.anz.assessment.repository.entity.Transaction;
import igd.anz.assessment.exception.ApiError;
import igd.anz.assessment.exception.TransactionNotFoundException;
import igd.anz.assessment.mapper.TransactionMapper;
import igd.anz.assessment.util.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public CollectionModel<TransactionResponse> getTransactionList(String userId, String accountNumber) {


        Optional<List<Transaction>> transactions = transactionRepository.findByAccountAccountNumberAndAccountUserUserId(accountNumber, Long.parseLong(userId));

        if(transactions.isPresent() && transactions.get().size() > 0){
            return CollectionModel.of(transactionMapper.mapToTransactionResponse(transactions.get()), WebMvcLinkBuilder.linkTo(methodOn(AccountController.class)
                    .getUserAccounts(transactions.get().stream().findFirst().get().getAccount().getUser().getUserId().toString())).withSelfRel());
        }
        String maskAccountNumber = CommonUtils.maskGivenAccountNumber(accountNumber);
        log.warn(String.format("message:\"Transaction not found for given account number\", account number=%s", maskAccountNumber));
        throw new TransactionNotFoundException(ApiError.builder().message("Transaction not found for given account number").errorId("E003").build());
    }
}
