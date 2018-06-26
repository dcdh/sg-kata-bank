package com.sg.kata.bank.model;

import com.google.common.base.Preconditions;

public class Client {

	private final String firstName;

	public Client(final String firstName) {
		Preconditions.checkNotNull(firstName, "'firstName' is mandatory");
		this.firstName = firstName;
	}

	public String firstName() {
		return firstName;
	}

}
