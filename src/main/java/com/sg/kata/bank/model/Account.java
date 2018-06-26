package com.sg.kata.bank.model;

import java.math.BigDecimal;

import com.google.common.base.Preconditions;

public class Account {

	private final Client client;

	private BigDecimal currentBalance;

	public Account(final Client client, final BigDecimal initialBalance) {
		Preconditions.checkNotNull(client, "'client' is mandatory");
		Preconditions.checkNotNull(initialBalance, "'initialBalance' is mandatory");
		Preconditions.checkState(initialBalance.compareTo(BigDecimal.ZERO) > 0, "'initialBalance' must be superior to 0");
		this.client = client;
		this.currentBalance = initialBalance;
	}

	public synchronized void withdrawsAmount(final BigDecimal amountToWithdraw) {
		Preconditions.checkNotNull(amountToWithdraw, "'amountToWithdraw' is mandatory");
		Preconditions.checkState(amountToWithdraw.compareTo(BigDecimal.ZERO) > 0, "'amountToWithdraw' must be superior to 0");
		this.currentBalance = this.currentBalance.subtract(amountToWithdraw);
	}

	public Client client() {
		return client;
	}

	public BigDecimal currentBalance() {
		return currentBalance;
	}

}
