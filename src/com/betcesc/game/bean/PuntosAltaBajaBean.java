package com.betcesc.game.bean;

public class PuntosAltaBajaBean {

	public String puntos;
	public String puntos5to;
	public String puntos5toDecimal;
	public String puntosSi;
	public String puntosNo;
	
	public PuntosAltaBajaBean() {
		
	}
	
	public PuntosAltaBajaBean(String puntos,String puntos5to,String puntos5toDecimal,String puntosSi,String puntosNo) {
		setPuntos(puntos);
		setPuntos5to(puntos5to);
		setPuntos5toDecimal(puntos5toDecimal);
		setPuntosSi(puntosSi);
		setPuntosNo(puntosNo);
	}

	
	public String getPuntos() {
		return puntos;
	}
	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	public String getPuntos5to() {
		return puntos5to;
	}
	public void setPuntos5to(String puntos5to) {
		this.puntos5to = puntos5to;
	}
	public String getPuntosSi() {
		return puntosSi;
	}
	public void setPuntosSi(String puntosSi) {
		this.puntosSi = puntosSi;
	}
	public String getPuntosNo() {
		return puntosNo;
	}
	public void setPuntosNo(String puntosNo) {
		this.puntosNo = puntosNo;
	}

	public String getPuntos5toDecimal() {
		return puntos5toDecimal;
	}

	public void setPuntos5toDecimal(String puntos5toDecimal) {
		this.puntos5toDecimal = puntos5toDecimal;
	}
	
	
	
}
