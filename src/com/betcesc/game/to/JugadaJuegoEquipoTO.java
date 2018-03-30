/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.JugadaJuegoEquipoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo JugadaJuegoEquipoTO serializables.
 */

public class JugadaJuegoEquipoTO implements Serializable, JugadaJuegoEquipoIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3725351617123606110L;
	private String idJugadaJuegoEquipo = null;
	private String idJugada = null;
	private String idUsuarioJuegoEquipo = null;
	private String tipo = null;
	private String idStatusJugada = null;
	/**
	 * @return Returns the idJugadaJuegoEquipo.
	 */
	public String getIdJugadaJuegoEquipo() {
		return idJugadaJuegoEquipo;
	}

	/**
	 * @param idJugadaJuegoEquipo
	 *            The idJugadaJuegoEquipo to set.
	 */
	public void setIdJugadaJuegoEquipo(String idJugadaJuegoEquipo) {
		this.idJugadaJuegoEquipo = idJugadaJuegoEquipo;
	}

	/**
	 * @return Returns the idJugada.
	 */
	public String getIdJugada() {
		return idJugada;
	}

	/**
	 * @param idJugada
	 *            The idJugada to set.
	 */
	public void setIdJugada(String idJugada) {
		this.idJugada = idJugada;
	}

	/**
	 * @return Returns the idUsuarioJuegoEquipo.
	 */
	public String getIdUsuarioJuegoEquipo() {
		return idUsuarioJuegoEquipo;
	}

	/**
	 * @param idUsuarioJuegoEquipo
	 *            The idUsuarioJuegoEquipo to set.
	 */
	public void setIdUsuarioJuegoEquipo(String idUsuarioJuegoEquipo) {
		this.idUsuarioJuegoEquipo = idUsuarioJuegoEquipo;
	}

	/**
	 * @return Returns the tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            The tipo to set.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdStatusJugada() {
		return idStatusJugada;
	}

	public void setIdStatusJugada(String idStatusJugada) {
		this.idStatusJugada = idStatusJugada;
	}

	
	
}
