package com.sg.kata.bank.model;

import org.junit.Assert;
import org.junit.Test;

public class ClientTest {

	private final static String NULL_POINTER_EXCEPTION_EXPECTED = "NullPointerException expected !";
	private final static String FIRST_NAME_MANDATORY = "'firstName' is mandatory";

	// Client initialisation
	@Test
	public void testClientInitialisationExpectedCase() {
		Client client = new Client("Damien");
		Assert.assertEquals("Damien", client.firstName());
	}

	@Test
	public void testClientFailFastUsingNullFirstNameShouldThrowException() {
		try {
			new Client(null);
			Assert.fail(NULL_POINTER_EXCEPTION_EXPECTED);
		} catch (Exception e) {
			Assert.assertTrue(NULL_POINTER_EXCEPTION_EXPECTED, e instanceof NullPointerException);
			Assert.assertEquals(FIRST_NAME_MANDATORY, e.getMessage());
		} 
	}

}
