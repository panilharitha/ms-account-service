package igd.anz.sample.assessment.repository;

import igd.anz.sample.assessment.repository.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByAccountId(Long accountId);

    Optional<List<Account>> findByUser_UserId(Long userId);
}
