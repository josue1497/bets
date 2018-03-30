package com.betcesc.game.exceptions;

public class GameOpenException extends Exception {

	public GameOpenException() {
		super("El usuario ya esta en session.");
		// TODO Auto-generated constructor stub
	}

	public GameOpenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameOpenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GameOpenException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
