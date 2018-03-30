/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.StatusJuegoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo StatusJuegoTO serializables.
 */

public class StatusJuegoTO implements Serializable, StatusJuegoIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4490934237820769543L;
	private String idStatusJuego = null;
	private String descStatusJuego = null;

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

	/**
	 * @return Returns the descStatusJuego.
	 */
	public String getDescStatusJuego() {
		return descStatusJuego;
	}

	/**
	 * @param descStatusJuego
	 *            The descStatusJuego to set.
	 */
	public void setDescStatusJuego(String descStatusJuego) {
		this.descStatusJuego = descStatusJuego;
	}

}
