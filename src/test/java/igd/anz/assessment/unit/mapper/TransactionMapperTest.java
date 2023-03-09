package igd.anz.assessment.unit.mapper;

import igd.anz.assessment.api.TransactionResponse;
import igd.anz.assessment.enums.TransactionType;
import igd.anz.assessment.mapper.TransactionMapper;
import igd.anz.assessment.repository.entity.Account;
import igd.anz.assessment.repository.entity.Currency;
import igd.anz.assessment.repository.entity.Transaction;
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
public class TransactionMapperTest {

    @InjectMocks
    private TransactionMapper transactionMapper;

    @Test
    void mapToTransactionResponse_SuccessMapping() {
        List<Transaction> transactionList = Arrays.asList(buildTransaction(1L, TransactionType.CREDIT));
        List<TransactionResponse> response = transactionMapper.mapToTransactionResponse(transactionList);

        assertEquals("Test Account Name", response.stream().findFirst().get().getAccountName());
        assertEquals("123456789", response.stream().findFirst().get().getAccountNumber());
        assertEquals("CREDIT", response.stream().findFirst().get().getTransactionType());
        assertEquals("AUD", response.stream().findFirst().get().getCurrency());
        assertEquals("Test note", response.stream().findFirst().get().getTransactionNarrative());
        assertEquals(1220.21, response.stream().findFirst().get().getCreditAmount(),0);
        assertEquals(0.00, response.stream().findFirst().get().getDebitAmount(),0);
        assertEquals("Sep.,01,2008", response.stream().findFirst().get().getValueDate());
    }

    @Test
    void mapToTransactionResponse_SuccessMapping_MuplipleTransactions() {
        List<Transaction> transactionList = Arrays.asList(buildTransaction(1L, TransactionType.CREDIT),
                buildTransaction(2L, TransactionType.DEBIT));
        List<TransactionResponse> response = transactionMapper.mapToTransactionResponse(transactionList);

        assertEquals(2, response.size());

        assertEquals("Test Account Name", response.stream().findFirst().get().getAccountName());
        assertEquals("123456789", response.stream().findFirst().get().getAccountNumber());
        assertEquals("CREDIT", response.stream().findFirst().get().getTransactionType());
        assertEquals("AUD", response.stream().findFirst().get().getCurrency());
        assertEquals("Test note", response.stream().findFirst().get().getTransactionNarrative());
        assertEquals(1220.21, response.stream().findFirst().get().getCreditAmount(),0);
        assertEquals(0.00, response.stream().findFirst().get().getDebitAmount(),0);
        assertEquals("Sep.,01,2008", response.stream().findFirst().get().getValueDate());

        assertEquals("Test Account Name", response.get(1).getAccountName());
        assertEquals("123456789", response.get(1).getAccountNumber());
        assertEquals("DEBIT", response.get(1).getTransactionType());
        assertEquals("AUD", response.get(1).getCurrency());
        assertEquals("Test note", response.get(1).getTransactionNarrative());
        assertEquals(0.00, response.get(1).getCreditAmount(),0);
        assertEquals(1220.21, response.get(1).getDebitAmount(),0);
        assertEquals("Sep.,01,2008", response.get(1).getValueDate());
    }

    private Transaction buildTransaction(Long transactionId, TransactionType transactionType) {
        return Transaction.builder().transactionId(transactionId).transactionType(transactionType)
                .amount(1220.21).valueDate(new Date(TimeUnit.SECONDS.toMillis(1220227200L))).transactionNarrative("Test note")
                .account(Account.builder().accountName("Test Account Name").accountNumber("123456789")
                .currency(Currency.builder().id(1l).name("AUD").build()).build()).build();
    }
}
