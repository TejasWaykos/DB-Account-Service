package com.dws.challenge.service;

import com.dws.challenge.domain.Account;
import com.dws.challenge.repository.AccountsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }

  public void transfer(String accountFromId, String accountToId, BigDecimal amount) {
    Account accountFrom = accountsRepository.getAccount(accountFromId);
    Account accountTo = accountsRepository.getAccount(accountToId);

    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new RuntimeException("The amount to transfer should always be a positive number : " + amount);
    }

    if (accountFrom == null) {
      throw new RuntimeException("Account not found: " + accountFrom.getAccountId());
    }

    if (accountTo == null) {
      throw new RuntimeException("Account not found: " + accountTo.getAccountId());
    }

    if (accountFrom.getBalance().compareTo(amount) < 0) {
      throw new RuntimeException("Insufficient balance in account: " + accountFrom);
    }

    accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
    accountTo.setBalance(accountTo.getBalance().add(amount));
  }
}
