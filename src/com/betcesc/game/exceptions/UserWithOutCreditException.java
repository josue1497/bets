package com.betcesc.game.exceptions;

public class UserWithOutCreditException extends Exception {

	public UserWithOutCreditException() {
		super("Session invalida, el usuario no esta en session.");
		// TODO Auto-generated constructor stub
	}

	public UserWithOutCreditException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserWithOutCreditException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserWithOutCreditException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
