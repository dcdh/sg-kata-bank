package com.sg.kata.bank.model;

import java.math.BigDecimal;

import org.junit.Assert;

import com.sg.kata.bank.model.Account;
import com.sg.kata.bank.model.Client;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountSteps {

	private Account account;

	@Given("^an existing client named \"([^\"]*)\" with (\\d+.\\d+) EUR in his account$")
	public void an_existing_client_named_with_EUR_in_his_account(final String firstName, final BigDecimal initialBalance) throws Throwable {
	    this.account = new Account(new Client(firstName),
	    		initialBalance);
	}

	@When("^he withdraws (\\d+.\\d+) EUR from his account$")
	public void he_withdraws_EUR_from_his_account(final BigDecimal amountToWithdraw) throws Throwable {
		this.account.withdrawsAmount(amountToWithdraw);
	}

	@Then("^the new balance is (\\d+.\\d+) EUR$")
	public void the_new_balance_is_EUR(final BigDecimal expectedNewBalance) throws Throwable {
	    Assert.assertEquals(expectedNewBalance, this.account.currentBalance());
	}

}
