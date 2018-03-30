package com.betcesc.game.exceptions;

public class TopePorPagarCombinacionAdministradorExcedidoException extends Exception {


	private String maximo = "0.00";
	private String acumulado = "0.00";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TopePorPagarCombinacionAdministradorExcedidoException() {
		super("Tope por derecho excedido");
		// TODO Auto-generated constructor stub
	}

	public TopePorPagarCombinacionAdministradorExcedidoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TopePorPagarCombinacionAdministradorExcedidoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TopePorPagarCombinacionAdministradorExcedidoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public TopePorPagarCombinacionAdministradorExcedidoException(String message, double maximo, double acumulado) {
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
