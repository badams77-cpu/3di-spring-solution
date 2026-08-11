package com.banking.three_di_testing.repositories;

import com.banking.three_di_testing.models.Account;
import com.banking.three_di_testing.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountIdOrderByInitiationDate(long id);

    @Query("SELECT  t from Transaction t WHERE sourceAccountId=:id AND initiationDate BETWEEN :startDate AND :endDate")
    List<Transaction> findBySourceAccountIdOrderByInitiationDateWithDateinRange(long id, LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT  t from Transaction t WHERE targetAccountId=:id AND initiationDate BETWEEN :startDate AND :endDate")
    List<Transaction> findByTargetAccountIdOrderByInitiationDateWithDateInRange(long id, LocalDateTime startDate, LocalDateTime endDate);
}
