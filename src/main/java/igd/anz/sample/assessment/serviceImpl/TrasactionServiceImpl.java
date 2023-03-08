package igd.anz.sample.assessment.serviceImpl;

import igd.anz.sample.assessment.api.TransactionResponse;
import igd.anz.sample.assessment.controller.AccountController;
import igd.anz.sample.assessment.exception.AccountNotFoundException;
import igd.anz.sample.assessment.exception.ApiError;
import igd.anz.sample.assessment.exception.TransactionNotFoundException;
import igd.anz.sample.assessment.mapper.TransactionMapper;
import igd.anz.sample.assessment.repository.AccountRepository;
import igd.anz.sample.assessment.repository.TransactionRepository;
import igd.anz.sample.assessment.repository.dao.Account;
import igd.anz.sample.assessment.repository.dao.Transaction;
import igd.anz.sample.assessment.service.TrasactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional(readOnly = true)
public class TrasactionServiceImpl implements TrasactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CollectionModel<TransactionResponse> getTransactionList(String accountId) {


        Optional<Account> account = accountRepository.findByAccountId(Long.parseLong(accountId));

        if(account.isPresent()){

            List<Transaction> transactions = transactionRepository.findByAccount_AccountId(account.get().getAccountId());
            if(account.isPresent() && transactions.size() > 0){
                return CollectionModel.of(transactionMapper.mapToTransactionResponse(account.get(), transactions), linkTo(methodOn(AccountController.class)
                        .getOwnerAccounts(account.get().getUser().getUserId().toString())).withSelfRel());
            }
            throw new TransactionNotFoundException(ApiError.builder().message("Transaction not found for given account id").errorId("E003").build());
        }
        throw new AccountNotFoundException(ApiError.builder().message("Account not found for given account id").errorId("E002").build());
    }
}
