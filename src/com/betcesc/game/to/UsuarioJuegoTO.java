/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.UsuarioJuegoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo UsuarioJuegoTO serializables.
 */

public class UsuarioJuegoTO implements Serializable, UsuarioJuegoIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7003007975665681700L;
	private String idUsuario = null;
	private String idJuego = null;
	private String fechaSis = null;
	private String idStatusJuego = null;

	/**
	 * @return Returns the idUsuario.
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return Returns the idJuego.
	 */
	public String getIdJuego() {
		return idJuego;
	}

	/**
	 * @param idJuego
	 *            The idJuego to set.
	 */
	public void setIdJuego(String idJuego) {
		this.idJuego = idJuego;
	}

	/**
	 * @return Returns the fechaSis.
	 */
	public String getFechaSis() {
		return fechaSis;
	}

	/**
	 * @param fechaSis
	 *            The fechaSis to set.
	 */
	public void setFechaSis(String fechaSis) {
		this.fechaSis = fechaSis;
	}

	/**
	 * @return Returns the idStatusJuego.
	 */
	public String getIdStatusJuego() {
		return idStatusJuego;
	}

	/**
	 * @param idStatusJuego
	 *            The idStatusJuego to set.
	 */
	public void setIdStatusJuego(String idStatusJuego) {
		this.idStatusJuego = idStatusJuego;
	}

}
