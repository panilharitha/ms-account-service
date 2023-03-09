package igd.anz.assessment.unit.mapper;

import igd.anz.assessment.api.AccountResponse;
import igd.anz.assessment.enums.AccountType;
import igd.anz.assessment.mapper.AccountMapper;
import igd.anz.assessment.repository.entity.Account;
import igd.anz.assessment.repository.entity.Currency;
import igd.anz.assessment.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountMapperTest {

    @InjectMocks
    private AccountMapper accountMapper;

    @Test
    void maptoAccountResponse_SuccessMapping() {
        List<Account> accountList = Arrays.asList(buildAccount(1L,"Test Account", "1", AccountType.SAVINGS
                , new Date(TimeUnit.SECONDS.toMillis(1220227200L)), 1000.12,
                        igd.anz.assessment.repository.entity.Currency.builder().id(1L).name("AUD").build()));

        List<AccountResponse> response = accountMapper.maptoAccountResponse(accountList);
        assertEquals("Test Account", response.stream().findFirst().get().getAccountName());
        assertEquals("1", response.stream().findFirst().get().getAccountNumber());
        assertEquals("Savings", response.stream().findFirst().get().getAccountType());
        assertEquals("AUD", response.stream().findFirst().get().getCurrency());
        assertEquals(1000.12, response.stream().findFirst().get().getOpeningAvailableBalance(), 0);
        assertEquals("01/09/2008", response.stream().findFirst().get().getBalanceDate());

    }
    @Test
    void maptoAccountResponse_SuccessMapping_withMultipleAccounts() {
        List<Account> accountList = Arrays.asList(buildAccount(1L,"Test Account", "1", AccountType.SAVINGS, new Date(), 1000.12,
                igd.anz.assessment.repository.entity.Currency.builder().id(1L).name("AUD").build()),
                buildAccount(2L,"Test Account", "1", AccountType.CURRENT, new Date(), 1000.12,
                        igd.anz.assessment.repository.entity.Currency.builder().id(1L).name("AUD").build()));

        List<AccountResponse> response = accountMapper.maptoAccountResponse(accountList);
        assertEquals(2, response.size());
        assertEquals("Test Account", response.stream().findFirst().get().getAccountName());
    }

    private Account buildAccount(Long accountId, String accountName, String accountNumber, AccountType accountType, Date balanceDate, double opaningBalance, Currency currency){
        return Account.builder().accountId(accountId)
                .accountName(accountName)
                .accountNumber(accountNumber)
                .accountType(accountType)
                .balanceDate(balanceDate)
                .opaningBalance(opaningBalance)
                .currency(currency)
                .user(User.builder().userId(1L).build())
                .build();
    }
}
