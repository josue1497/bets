package com.betcesc.game.exceptions;

public class GameClosedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5098730400444041179L;

	public GameClosedException() {
		super("El juego ya esta cerrado");
		// TODO Auto-generated constructor stub
	}

	public GameClosedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameClosedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GameClosedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
