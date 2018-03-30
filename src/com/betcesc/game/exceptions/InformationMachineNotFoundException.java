package com.betcesc.game.exceptions;

public class InformationMachineNotFoundException extends Exception {

	public InformationMachineNotFoundException() {
		super("La informacion de la maquina no se ha podido recuperar, Coloque en sitio seguro y active los scriptx no firmados como seguros en IE.");
		// TODO Auto-generated constructor stub
	}

	InformationMachineNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	InformationMachineNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	InformationMachineNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
