package com.betcesc.game.to;

import com.betcesc.game.interfaces.ParametrosIF;

public class ParametrosTO implements ParametrosIF {
	
	private String nombre;
	private String valor;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}