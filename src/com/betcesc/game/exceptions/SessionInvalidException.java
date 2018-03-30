package com.betcesc.game.exceptions;

public class SessionInvalidException extends Exception {
	
	private String dominio = "000";

	public SessionInvalidException() {
		super("Session invalida, el usuario no esta en session.");
		// TODO Auto-generated constructor stub
	}

	public SessionInvalidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SessionInvalidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SessionInvalidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public String getDominio() {
		return dominio;
	}

}
