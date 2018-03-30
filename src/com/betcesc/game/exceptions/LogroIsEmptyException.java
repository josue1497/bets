package com.betcesc.game.exceptions;

public class LogroIsEmptyException extends Exception {

	public LogroIsEmptyException() {
		super("Apuesta no valida");
		// TODO Auto-generated constructor stub
	}

	public LogroIsEmptyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LogroIsEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LogroIsEmptyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
