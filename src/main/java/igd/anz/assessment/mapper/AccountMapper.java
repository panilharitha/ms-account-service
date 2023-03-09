package igd.anz.assessment.mapper;

import igd.anz.assessment.api.AccountResponse;
import igd.anz.assessment.controller.AccountController;
import igd.anz.assessment.repository.entity.Account;
import igd.anz.assessment.util.CommonUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AccountMapper {

    public List<AccountResponse> maptoAccountResponse(List<Account> accounts){

        List<AccountResponse> response = new ArrayList<AccountResponse>();

        accounts.stream().forEach(account ->
                response.add(AccountResponse.builder()
                                .accountNumber(account.getAccountNumber())
                        .accountName(account.getAccountName())
                        .accountType(account.getAccountType().getName())
                        .balanceDate(CommonUtils.dateToStringwithFormat(account.getBalanceDate()))
                        .currency(account.getCurrency().getName())
                        .openingAvailableBalance(account.getOpaningBalance()).build().add(linkTo(methodOn(AccountController.class)
                                .getTransactions(account.getUser().getUserId().toString(), account.getAccountNumber())).withSelfRel()))
                );

        return response;
    }
}
