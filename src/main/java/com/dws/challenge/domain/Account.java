package com.dws.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Account {

  @NotNull
  @NotEmpty
  private final String accountId;

  @NotNull
  @Min(value = 0, message = "Initial balance must be positive.")
  private BigDecimal balance;

  public Account(String accountId) {
    this.accountId = accountId;
    this.balance = BigDecimal.ZERO;
  }

  @JsonCreator
  public Account(@JsonProperty("accountId") String accountId,
                 @JsonProperty("balance") BigDecimal balance) {
    this.accountId = accountId;
    this.balance = balance;
  }

  public @NotNull @NotEmpty String getAccountId() {
    return accountId;
  }

  public @NotNull @Min(value = 0, message = "Initial balance must be positive.") BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(@NotNull @Min(value = 0, message = "Initial balance must be positive.") BigDecimal balance) {
    this.balance = balance;
  }
}
