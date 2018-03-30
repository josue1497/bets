package com.betcesc.game.exceptions;

public class SessionDuplicateException extends Exception {

	public SessionDuplicateException() {
		super("El usuario ya esta en session.");
		// TODO Auto-generated constructor stub
	}

	public SessionDuplicateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SessionDuplicateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SessionDuplicateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
