package com.betcesc.game.bean;

public class ResumenBean {

	
	private String nombre;
	private String jugado;
	private String devolucion;
	private String ganadores;
	private String subTotal;
	private String comisionVenta;
	private String comision;
	private String total;
	
	public ResumenBean() {
		
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public String getComisionVenta() {
		return comisionVenta;
	}

	public void setComisionVenta(String comisionVenta) {
		this.comisionVenta = comisionVenta;
	}

	public String getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(String devolucion) {
		this.devolucion = devolucion;
	}

	public String getGanadores() {
		return ganadores;
	}

	public void setGanadores(String ganadores) {
		this.ganadores = ganadores;
	}

	public String getJugado() {
		return jugado;
	}

	public void setJugado(String jugado) {
		this.jugado = jugado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
