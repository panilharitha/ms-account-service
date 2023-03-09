package igd.anz.assessment.controller;

import igd.anz.assessment.api.AccountResponse;
import igd.anz.assessment.api.common.RegexConstants;
import igd.anz.assessment.configuration.HttpSanitizer;
import igd.anz.assessment.service.AccountService;
import igd.anz.assessment.api.TransactionResponse;
import igd.anz.assessment.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService trasactionService;

    @Autowired
    private HttpSanitizer httpSanitizer;

    @GetMapping(value = "/api/v1/users/{userId}/accounts", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getUserAccounts(@PathVariable("userId") @NotBlank @Pattern(regexp = RegexConstants.USER_ID) @Size(min = 1, max = 9) final String userId){
        /* In best practice, User id not pass with url. have to use Token for get user id. But in here used user id as url path variable.
        Because login part is not included to this assessment scope*/
        String sanitizedUserId = httpSanitizer.sanitizer(userId);
        CollectionModel<AccountResponse> response = accountService.getAccountList(sanitizedUserId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/users/{userId}/accounts/{accountNumber}/transactions", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getTransactions(@PathVariable("userId") @NotBlank @Pattern(regexp = RegexConstants.USER_ID) @Size(min = 1, max = 9) final String userId,
                                                  @PathVariable @Pattern(regexp = RegexConstants.ACCOUNT_NUMBER) final String accountNumber){
        /* In best practice, User id not pass with url. have to use Token for get user id. But in here used user id as url path variable.
        Because login part is not included to this assessment scope*/
        String sanitizedUserId = httpSanitizer.sanitizer(userId);
        String sanitizedAccountId = httpSanitizer.sanitizer(accountNumber);
        CollectionModel<TransactionResponse> response = trasactionService.getTransactionList(sanitizedUserId, sanitizedAccountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
