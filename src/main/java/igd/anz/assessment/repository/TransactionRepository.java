package igd.anz.assessment.repository;

import igd.anz.assessment.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByAccountAccountNumberAndAccountUserUserId(String accountNumber, Long userId);
}
