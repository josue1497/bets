package com.betcesc.game.bean;

public class ResultadosBean {

	public String fecha;
	public String juego;
	public String deporte;
	public String equipo;
	public String puntos;
	public String puntos5to;
	public String puntos1er;
	public String juegoPadre;
	
	public ResultadosBean() {
		fecha = "";
		juego = "";
		deporte = "";
		equipo = "";
		puntos = "";
		puntos5to = "";
		puntos1er = "";
		juegoPadre = "";
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		fecha = fecha.replace("Monday","Lunes");
		fecha = fecha.replace("Tuesday","Martes");
		fecha = fecha.replace("Wednesday","Miércoles");
		fecha = fecha.replace("Thursday","Jueves");
		fecha = fecha.replace("Friday","Viernes");
		fecha = fecha.replace("Saturday","Sábado");
		fecha = fecha.replace("Sunday","Domingo");
		this.fecha = fecha;
	}

	public String getJuego() {
		return juego;
	}

	public void setJuego(String juego) {
		this.juego = juego;
	}

	public String getJuegoPadre() {
		return juegoPadre;
	}

	public void setJuegoPadre(String juegoPadre) {
		this.juegoPadre = juegoPadre;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

	public String getPuntos1er() {
		return puntos1er;
	}

	public void setPuntos1er(String puntos1er) {
		this.puntos1er = puntos1er;
	}

	public String getPuntos5to() {
		return puntos5to;
	}

	public void setPuntos5to(String puntos5to) {
		this.puntos5to = puntos5to;
	}
	
	
}
