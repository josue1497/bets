package com.betcesc.game.exceptions;

public class SecurityIDNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 575188183959837465L;

	public SecurityIDNotValidException() {
		super("Session invalida, el usuario no esta en session.");
		// TODO Auto-generated constructor stub
	}

	public SecurityIDNotValidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SecurityIDNotValidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SecurityIDNotValidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
