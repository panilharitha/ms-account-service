package igd.anz.sample.assessment.serviceImpl;

import igd.anz.sample.assessment.api.AccountResponse;
import igd.anz.sample.assessment.exception.AccountNotFoundException;
import igd.anz.sample.assessment.exception.ApiError;
import igd.anz.sample.assessment.exception.UserNotFoundException;
import igd.anz.sample.assessment.mapper.AccountMapper;
import igd.anz.sample.assessment.repository.AccountRepository;
import igd.anz.sample.assessment.repository.UserRepository;
import igd.anz.sample.assessment.repository.dao.Account;
import igd.anz.sample.assessment.repository.dao.User;
import igd.anz.sample.assessment.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public CollectionModel<AccountResponse> getOwnerAccountList(String userId) {
        Optional<List<Account>> accounts = accountRepository.findByUser_UserID(Long.parseLong(userId));

        if(accounts.isPresent()){
            return CollectionModel.of(accountMapper.maptoAccountResponse(accounts.get()));
        }

        throw new UserNotFoundException(ApiError.builder().message("Account not found for given user id").errorId("E001").build());
    }
}
