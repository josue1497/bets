package com.betcesc.game.exceptions;

public class LogrosChangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5098730400444041179L;

	public LogrosChangeException() {
		super("Los logros han sido modificados.");
		// TODO Auto-generated constructor stub
	}

	public LogrosChangeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public LogrosChangeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LogrosChangeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
