package igd.anz.assessment.service;

import igd.anz.assessment.api.AccountResponse;
import igd.anz.assessment.exception.AccountNotFoundException;
import igd.anz.assessment.exception.ApiError;
import igd.anz.assessment.mapper.AccountMapper;
import igd.anz.assessment.repository.AccountRepository;
import igd.anz.assessment.repository.entity.Account;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public CollectionModel<AccountResponse> getAccountList(String userId) {
        Optional<List<Account>> accounts = accountRepository.findByUserUserId(Long.parseLong(userId));

        if(accounts.isPresent() && accounts.get().size() > 0){
            return CollectionModel.of(accountMapper.maptoAccountResponse(accounts.get()));
        }
        log.warn(String.format("message:\"Account not found for given account number\", user id=%s", userId));
        throw new AccountNotFoundException(ApiError.builder().message("Account not found for given user id").errorId("E001").build());
    }
}
