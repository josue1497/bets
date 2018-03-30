package com.betcesc.game.exceptions;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("El usuario no esta registrado en el sistema.");
		// TODO Auto-generated constructor stub
	}

	UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	UserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
