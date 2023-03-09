package igd.anz.assessment.unit.service;


import igd.anz.assessment.api.AccountResponse;
import igd.anz.assessment.controller.AccountController;
import igd.anz.assessment.enums.AccountType;
import igd.anz.assessment.exception.AccountNotFoundException;
import igd.anz.assessment.mapper.AccountMapper;
import igd.anz.assessment.repository.AccountRepository;
import igd.anz.assessment.repository.entity.Account;
import igd.anz.assessment.repository.entity.Currency;
import igd.anz.assessment.service.AccountServiceImpl;
import igd.anz.assessment.util.CommonUtils;
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.quality.Strictness;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.*;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getOwnerAccountList_SuccessWithValiedUserId(){

        Optional<List<Account>> dbReturn = Optional.of(Arrays.asList(Account.builder().build()));
        List<AccountResponse> mapperReturn = Arrays.asList(buildAccountResponse(
                buildAccount(1L,"Test Account", "1", AccountType.SAVINGS, new Date(), 1000.12,
                        igd.anz.assessment.repository.entity.Currency.builder().id(1L).name("AUD").build())));

        when(accountRepository.findByUserUserId(anyLong())).thenReturn(dbReturn);
        when(accountMapper.maptoAccountResponse(any(List.class))).thenReturn(mapperReturn);

        CollectionModel<AccountResponse> response = accountService.getAccountList("1");
        verify(accountRepository, times(1)).findByUserUserId(anyLong());
        verify(accountRepository, times(0)).findByAccountId(anyLong());
        verify(accountMapper, times(1)).maptoAccountResponse(any());
        assertEquals(response.getContent().stream().findFirst().get().getAccountName(),"Test Account");
        assertEquals(response.getContent().stream().findFirst().get().getAccountNumber(),"1");
    }

    @Test
    void getOwnerAccountList_AccountNotFoundExceptionForInvalidUserId_throw404(){

        Optional<List<Account>> dbReturn = Optional.of(new ArrayList<>());

        when(accountRepository.findByUserUserId(anyLong())).thenReturn(dbReturn);

        AccountNotFoundException exception = assertThrows(
                AccountNotFoundException.class, () -> accountService.getAccountList("1"));

        assertAll("exception",
                () -> {
                    Assertions.assertNotNull(exception);
                    Assertions.assertEquals(exception.getClass(), AccountNotFoundException.class);
                    Assertions.assertEquals("Account not found for given user id", exception.getApiError().getMessage());
                }
        );
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
                .openingAvailableBalance(account.getOpaningBalance()).build().add(WebMvcLinkBuilder.linkTo(methodOn(AccountController.class)
                        .getTransactions("1",account.getAccountId().toString())).withSelfRel());
    }
}
