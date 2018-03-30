package com.betcesc.game.exceptions;

public class TopePorCombinacionAdministradorExcedidoException extends Exception {


	private String maximo = "0.00";
	private String acumulado = "0.00";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TopePorCombinacionAdministradorExcedidoException() {
		super("Tope por derecho excedido");
		// TODO Auto-generated constructor stub
	}

	public TopePorCombinacionAdministradorExcedidoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TopePorCombinacionAdministradorExcedidoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TopePorCombinacionAdministradorExcedidoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TopePorCombinacionAdministradorExcedidoException(String message, double maximo, double acumulado) {
		super(message);
		setMaximo(String.valueOf(maximo));
		setAcumulado(String.valueOf(acumulado));
	}

	public String getMaximo() {
		return maximo;
	}

	public void setMaximo(String maximo) {
		this.maximo = maximo;
	}

	public String getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(String acumulado) {
		this.acumulado = acumulado;
	}
	
	

	
}
