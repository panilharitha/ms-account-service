package igd.anz.assessment.Intergration.controller;

import igd.anz.assessment.AssessmentApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AssessmentApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class AccountControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getUserAccounts_giveValidUserId_thenStatus200() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts","2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("_embedded.accountResponseList[0].accountNumber", is("585309209")));
    }

    @Test
    public void getUserAccounts_giveUnavailableUserId_thenStatus404() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts","5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("Account not found for given user id")));
    }

    @Test
    public void getUserAccounts_giveInvalidUserId_MoreThanNineNumbers_thenStatus400() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts","1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("getUserAccounts.userId: size must be between 1 and 9")));
    }

    @Test
    public void getTransactions_giveValidUserIdAndAccountNumber_thenStatus200() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts/{accountNumber}/transactions","2", "585309209")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("_embedded.transactionResponseList[0].accountNumber", is("585309209")));
    }

    @Test
    public void getTransactions_giveValidUserIdAndUnavailableAccountNumber_thenStatus404() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts/{accountNumber}/transactions","2", "585301234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("Transaction not found for given account number")));
    }

    @Test
    public void getTransactions_giveValidUserIdAndInvalidAccountNumber_MoreThanNineNumbers_thenStatus400() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts/{accountNumber}/transactions","123456789", "1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("getTransactions.accountNumber: must match \"^[A-Za-z0-9-]{9}$\"")));
    }

    @Test
    public void getTransactions_giveInvalidUserIdAndValidAccountNumber_NotAccountOwner_thenStatus404() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts/{accountNumber}/transactions","3", "585309209")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("Transaction not found for given account number")));
    }

    @Test
    public void getTransactions_givevalidUserIdAndINValidAccountNumber_WrongAccountNumberPattern_thenStatus400() throws Exception {

        mvc.perform(get("/api/v1/users/{userId}/accounts/{accountNumber}/transactions","2", "5853 9209")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is("getTransactions.accountNumber: must match \"^[A-Za-z0-9-]{9}$\"")));
    }
}
