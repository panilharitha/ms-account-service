package igd.anz.sample.assessment.serviceImpl;

import igd.anz.sample.assessment.api.AccountResponse;
import igd.anz.sample.assessment.controller.AccountController;
import igd.anz.sample.assessment.enums.AccountType;
import igd.anz.sample.assessment.mapper.AccountMapper;
import igd.anz.sample.assessment.repository.AccountRepository;
import igd.anz.sample.assessment.repository.dao.Account;
import igd.anz.sample.assessment.repository.dao.Currency;
import igd.anz.sample.assessment.util.CommonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ExtendWith((MockitoExtension.class))
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    private AccountServiceImpl accountService;

    @BeforeEach
    void setup(){
        this.accountService = new AccountServiceImpl(accountRepository, accountMapper);
    }

    @Test
    void getOwnerAccountListSuccess(){

        Optional<List<Account>> dbReturn = Optional.of(Arrays.asList(Account.builder().build()));
        List<AccountResponse> mapperReturn = Arrays.asList(buildAccountResponse(
                buildAccount(1L,"Test Account", "1", AccountType.SAVINGS, new Date(), 1000.12,
                        Currency.builder().id(1L).name("AUD").build())));

        when(accountRepository.findByUser_UserID(anyLong())).thenReturn(dbReturn);
        when(accountMapper.maptoAccountResponse(any(List.class))).thenReturn(mapperReturn);

        CollectionModel<AccountResponse> response = accountService.getOwnerAccountList("1");
        verify(accountRepository, times(1)).findByUser_UserID(anyLong());

    }

    private Account buildAccount(Long accountId, String accountName, String accountNumber, AccountType accountType, Date balanceDate, double opaningBalance, Currency currency){
        return Account.builder().accountId(accountId)
                .accountName(accountName)
                .accountNumber(accountNumber)
                .accountType(accountType)
                .balanceDate(balanceDate)
                .opaningBalance(opaningBalance)
                .currency(currency)
                .build();
    }

    private AccountResponse buildAccountResponse(Account account){
        return AccountResponse.builder()
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .accountType(account.getAccountType().getName())
                .balanceDate(CommonUtils.dateToStringwithFormat(account.getBalanceDate()))
                .currency(account.getCurrency().getName())
                .openingAvailableBalance(account.getOpaningBalance()).build().add(linkTo(methodOn(AccountController.class)
                        .getTransactions(account.getAccountId().toString())).withSelfRel());
    }
}
