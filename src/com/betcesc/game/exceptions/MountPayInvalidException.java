package com.betcesc.game.exceptions;

public class MountPayInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MountPayInvalidException() {
		super("El monto del premio no corresponde con el monto apostado, realize nuevamente su apuesta");
		// TODO Auto-generated constructor stub
	}

	public MountPayInvalidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MountPayInvalidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MountPayInvalidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
