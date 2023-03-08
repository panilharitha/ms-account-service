package igd.anz.sample.assessment.service;

import igd.anz.sample.assessment.api.AccountResponse;
import org.springframework.hateoas.CollectionModel;

public interface AccountService {
    CollectionModel<AccountResponse> getOwnerAccountList(String userId);


}
