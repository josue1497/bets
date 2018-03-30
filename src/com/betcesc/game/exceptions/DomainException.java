package com.betcesc.game.exceptions;

public class DomainException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DomainException() {
		super("El usuario no esta registrado en el dominio.");
		// TODO Auto-generated constructor stub
	}

	DomainException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	DomainException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	DomainException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
