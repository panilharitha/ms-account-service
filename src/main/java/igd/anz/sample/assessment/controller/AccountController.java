package igd.anz.sample.assessment.controller;

import igd.anz.sample.assessment.api.AccountResponse;
import igd.anz.sample.assessment.api.TransactionResponse;
import igd.anz.sample.assessment.service.AccountService;
import igd.anz.sample.assessment.service.TrasactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TrasactionService trasactionService;

    @Autowired
    private HttpSanitizer httpSanitizer;

    @GetMapping(value = "/api/v1/owners/{userId}/accounts", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getOwnerAccounts(@PathVariable String userId){
        String sanitizedUserId = httpSanitizer.sanitizer(userId);
        CollectionModel<AccountResponse> response = accountService.getOwnerAccountList(sanitizedUserId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/api/v1/owners/{accountId}/transactions", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> getTransactions(@PathVariable String accountId){
        String sanitizedAccountId = httpSanitizer.sanitizer(accountId);
        CollectionModel<TransactionResponse> response = trasactionService.getTransactionList(accountId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
