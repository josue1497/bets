package com.betcesc.game.exceptions;

public class UserNotValidMachineException extends Exception {

	public UserNotValidMachineException() {
		super("El usuario no esta registrado en esta maquina.");
		// TODO Auto-generated constructor stub
	}

	UserNotValidMachineException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	UserNotValidMachineException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	UserNotValidMachineException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
