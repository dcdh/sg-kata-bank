package com.sg.kata.bank.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AccountTest {

	private Client client = Mockito.mock(Client.class);

	private final static BigDecimal CENT = new BigDecimal("100");
	private final static BigDecimal DIX = new BigDecimal("10");

	private final static String NULL_POINTER_EXCEPTION_EXPECTED = "NullPointerException expected !";
	private final static String ILLEGAL_STATE_EXCEPTION_EXPECTED = "IllegalStateException expected !";
	private final static String CLIENT_MANDATORY = "'client' is mandatory";
	private final static String INITIAL_BALANCE_SUPERIOR_TO_ZERO = "'initialBalance' must be superior to 0";
	private final static String INITIAL_BALANCE_MANDATORY = "'initialBalance' is mandatory";
	private final static String AMOUNT_TO_WITHDRAW_MANDATORY = "'amountToWithdraw' is mandatory";
	private final static String AMOUNT_TO_WITHDRAW_SUPERIOR_TO_ZERO = "'amountToWithdraw' must be superior to 0";

	// Account initialisation
	@Test
	public void testAccountInitialisationExpectedCase() {
		Account account  = new Account(client, CENT);
		Assert.assertEquals(client, account.client());
		Assert.assertEquals(CENT, account.currentBalance());
	}

	@Test
	public void testAccountFailFastUsingNullClientShouldThrowException() {
		try {
			new Account(null, CENT);
			Assert.fail(NULL_POINTER_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(NULL_POINTER_EXCEPTION_EXPECTED, e instanceof NullPointerException);
			Assert.assertEquals(CLIENT_MANDATORY, e.getMessage());
		}
	}

	@Test
	public void testAccountFailFastUsingNullAmountAsInitialAmountShouldThrowException() {
		try {
			new Account(client, null);
			Assert.fail(NULL_POINTER_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(NULL_POINTER_EXCEPTION_EXPECTED, e instanceof NullPointerException);
			Assert.assertEquals(INITIAL_BALANCE_MANDATORY, e.getMessage());
		}
	}

	@Test
	public void testAccountFailFastUsingZeroAmountAsInitialAmountShouldThrowException() {
		try {
			new Account(client, BigDecimal.ZERO);
			Assert.fail(ILLEGAL_STATE_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(ILLEGAL_STATE_EXCEPTION_EXPECTED, e instanceof IllegalStateException);
			Assert.assertEquals(INITIAL_BALANCE_SUPERIOR_TO_ZERO, e.getMessage());
		}
	}

	@Test
	public void testAccountFailFastUsingNegativeAmountAsInitialAmountShouldThrowException() {
		try {
			new Account(client, CENT.negate());
			Assert.fail(ILLEGAL_STATE_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(ILLEGAL_STATE_EXCEPTION_EXPECTED, e instanceof IllegalStateException);
			Assert.assertEquals(INITIAL_BALANCE_SUPERIOR_TO_ZERO, e.getMessage());
		}
	}

	// Account withdraw
	@Test
	public void testAccountWithdrawExpectedCase() {
		Account account  = new Account(client, CENT);
		account.withdrawsAmount(DIX);
		Assert.assertEquals(new BigDecimal("90"), account.currentBalance());
	}

	@Test
	public void testAccountWithdrawFailFastUsingNullAmountToWithdrawShouldThrowException() {
		try {
			Account account  = new Account(client, CENT);
			account.withdrawsAmount(null);
			Assert.fail(NULL_POINTER_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(NULL_POINTER_EXCEPTION_EXPECTED, e instanceof NullPointerException);
			Assert.assertEquals(AMOUNT_TO_WITHDRAW_MANDATORY, e.getMessage());
		}
	}

	@Test
	public void testAccountWithdrawFailFastUsingZeroAmountToWithdrawShouldThrowException() {
		try {
			Account account  = new Account(client, CENT);
			account.withdrawsAmount(BigDecimal.ZERO);
			Assert.fail(ILLEGAL_STATE_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(ILLEGAL_STATE_EXCEPTION_EXPECTED, e instanceof IllegalStateException);
			Assert.assertEquals(AMOUNT_TO_WITHDRAW_SUPERIOR_TO_ZERO, e.getMessage());
		}
	}

	@Test
	public void testAccountWithdrawFailFastUsingNegativeAmountToWithdrawShouldThrowException() {
		try {
			Account account  = new Account(client, CENT);
			account.withdrawsAmount(CENT.negate());
			Assert.fail(ILLEGAL_STATE_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(ILLEGAL_STATE_EXCEPTION_EXPECTED, e instanceof IllegalStateException);
			Assert.assertEquals(AMOUNT_TO_WITHDRAW_SUPERIOR_TO_ZERO, e.getMessage());
		}
	}

}
