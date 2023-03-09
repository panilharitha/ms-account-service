package igd.anz.assessment.repository;

import igd.anz.assessment.repository.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByAccountId(Long accountId);

    Optional<List<Account>> findByUserUserId(Long userId);
}
