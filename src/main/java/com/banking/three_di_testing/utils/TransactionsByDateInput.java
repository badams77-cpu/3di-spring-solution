package com.banking.three_di_testing.utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionsByDateInput {

    @NotBlank(message = "Sort code is mandatory")
    private String sortCode;

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;


    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    public TransactionsByDateInput() {
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsByDateInput that = (TransactionsByDateInput) o;
        return Objects.equals(sortCode, that.sortCode) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortCode, accountNumber, startDate, endDate);
    }

    @Override
    public String toString() {
        return "TransactionsByDateInput{" +
                "sortCode='" + sortCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}