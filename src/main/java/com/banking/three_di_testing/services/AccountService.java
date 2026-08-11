package com.banking.three_di_testing.services;

import com.banking.three_di_testing.models.Account;
import com.banking.three_di_testing.models.Transaction;
import com.banking.three_di_testing.repositories.AccountRepository;
import com.banking.three_di_testing.repositories.TransactionRepository;
import com.banking.three_di_testing.utils.CodeGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account getAccount(String sortCode, String accountNumber) {
        Optional<Account> account = accountRepository
                .findBySortCodeAndAccountNumber(sortCode, accountNumber);

        account.ifPresent(value ->
                value.setTransactions(transactionRepository
                        .findBySourceAccountIdOrderByInitiationDate(value.getId())));

        return account.orElse(null);
    }

    public Account getAccount(String accountNumber) {
        Optional<Account> account = accountRepository
                .findByAccountNumber(accountNumber);

        return account.orElse(null);
    }

    public Account createAccount(String bankName, String ownerName) {
        CodeGenerator codeGenerator = new CodeGenerator();
        Account newAccount = new Account(bankName, ownerName, codeGenerator.generateSortCode(), codeGenerator.generateAccountNumber(), 0.00);
        return accountRepository.save(newAccount);
    }

    public Account findTransactionsBySourceCodeAndAccountNumber(String accountNumber, String sortCode) {
        Optional<Account> account = accountRepository
                .findBySortCodeAndAccountNumber(sortCode, accountNumber);

        account.ifPresent(value ->
                value.setTransactions(transactionRepository
                        .findBySourceAccountIdOrderByInitiationDate(value.getId()))

        );

        account.ifPresent(value ->
                value.setTransactions(transactionRepository
                        .findBySourceAccountIdOrderByInitiationDate(value.getId()))

        );

        return account.orElse(null);
    }

    public Account findTransactionsBySortCodeAccountNumberAndDateBetween(String sortCode, String accountNumber, LocalDate startDate, LocalDate endDate) {
        Optional<Account> account = accountRepository
                .findBySortCodeAndAccountNumber(sortCode, accountNumber);

        final List<Transaction> transactions = new LinkedList<>();

        account.ifPresent(value ->
                transactions.addAll(transactionRepository.findBySourceAccountIdOrderByInitiationDateWithDateinRange(value.getId(), startDate.atStartOfDay(), endDate.atTime(23,59)))

        );
        transactions.forEach( t->t.setAmount(-t.getAmount()));
        account.ifPresent(value ->
                transactions.addAll(transactionRepository.findByTargetAccountIdOrderByInitiationDateWithDateInRange(value.getId(), startDate.atStartOfDay(), endDate.atTime(23,59)))

        );
        account.ifPresent( a->a.setTransactions(transactions.stream()
                .sorted(Comparator.comparing(t->t.getInitiationDate() ))
                .collect(Collectors.toList())));

        return account.orElse(null);
    }
}
