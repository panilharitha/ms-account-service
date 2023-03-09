package igd.anz.assessment.service;

import igd.anz.assessment.api.AccountResponse;
import org.springframework.hateoas.CollectionModel;

public interface AccountService {
    CollectionModel<AccountResponse> getAccountList(String userId);


}
