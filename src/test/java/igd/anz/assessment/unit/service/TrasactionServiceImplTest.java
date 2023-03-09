package igd.anz.assessment.unit.service;

import igd.anz.assessment.api.TransactionResponse;
import igd.anz.assessment.enums.AccountType;
import igd.anz.assessment.enums.TransactionType;
import igd.anz.assessment.exception.AccountNotFoundException;
import igd.anz.assessment.exception.TransactionNotFoundException;
import igd.anz.assessment.mapper.TransactionMapper;
import igd.anz.assessment.repository.TransactionRepository;
import igd.anz.assessment.repository.entity.Account;
import igd.anz.assessment.repository.entity.Currency;
import igd.anz.assessment.repository.entity.Transaction;
import igd.anz.assessment.repository.entity.User;
import igd.anz.assessment.service.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.hateoas.CollectionModel;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TrasactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void getTransactionList_SuccessWithValiedUserIdAndAccountNumber(){

        Optional<List<Transaction>> dbReturn = Optional.of(Arrays.asList(Transaction.builder()
                .account(Account.builder().user(User.builder().userId(1L).build()).build()).build()));
        List<TransactionResponse> mapperReturn = Arrays.asList(buildTransactionResponse());

        when(transactionRepository.findByAccountAccountNumberAndAccountUserUserId(anyString(),anyLong())).thenReturn(dbReturn);
        when(transactionMapper.mapToTransactionResponse(any(List.class))).thenReturn(mapperReturn);

        CollectionModel<TransactionResponse> response = transactionService.getTransactionList("1", "123456789");
        verify(transactionRepository, times(1)).findByAccountAccountNumberAndAccountUserUserId(anyString(),anyLong());
        verify(transactionMapper, times(1)).mapToTransactionResponse(any());
        assertEquals(response.getContent().stream().findFirst().get().getAccountName(),"Test Account Name");
        assertEquals(response.getContent().stream().findFirst().get().getAccountNumber(),"123456789");
    }

    @Test
    void getTransactionList_SuccessWithValiedUserIdAndAccountNumber_MultipleTransactionReturn(){

        Optional<List<Transaction>> dbReturn = Optional.of(Arrays.asList(Transaction.builder()
                .account(Account.builder().user(User.builder().userId(1L).build()).build()).build(),
                Transaction.builder()
                        .account(Account.builder().user(User.builder().userId(2L).build()).build()).build()));
        List<TransactionResponse> mapperReturn = Arrays.asList(buildTransactionResponse(),buildTransactionResponse());

        when(transactionRepository.findByAccountAccountNumberAndAccountUserUserId(anyString(),anyLong())).thenReturn(dbReturn);
        when(transactionMapper.mapToTransactionResponse(any(List.class))).thenReturn(mapperReturn);

        CollectionModel<TransactionResponse> response = transactionService.getTransactionList("1", "123456789");
        verify(transactionRepository, times(1)).findByAccountAccountNumberAndAccountUserUserId(anyString(),anyLong());
        verify(transactionMapper, times(1)).mapToTransactionResponse(any());
        assertEquals(response.getContent().size(),2);
    }

    @Test
    void getTransactionList_ErrorsWithValiedUserIdAndInvalidAccountNumber_throw_404(){

        Optional<List<Transaction>> dbReturn = Optional.of(new ArrayList<>());

        when(transactionRepository.findByAccountAccountNumberAndAccountUserUserId(anyString(),anyLong())).thenReturn(dbReturn);

        TransactionNotFoundException exception = assertThrows(
                TransactionNotFoundException.class,
                () -> transactionService.getTransactionList("1", "123456789"));

        assertAll("exception",
                () -> {
                    Assertions.assertNotNull(exception);
                    Assertions.assertEquals(exception.getClass(), TransactionNotFoundException.class);
                    Assertions.assertEquals("Transaction not found for given account number", exception.getApiError().getMessage());
                }
        );
    }

    private TransactionResponse buildTransactionResponse() {
        return TransactionResponse.builder().transactionType(TransactionType.CREDIT.name()).AccountName("Test Account Name")
                .accountNumber("123456789").creditAmount(1234.59).debitAmount(0.00).transactionNarrative("test note")
                .currency("AUD").valueDate("20-01-2023").build();
    }
}
