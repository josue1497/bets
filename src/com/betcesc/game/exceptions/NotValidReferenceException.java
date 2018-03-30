package com.betcesc.game.exceptions;

public class NotValidReferenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3598049180240785565L;

	public NotValidReferenceException() {
		super("La Referencia de juego no es valida");
		// TODO Auto-generated constructor stub
	}

	public NotValidReferenceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotValidReferenceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotValidReferenceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
