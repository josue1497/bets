/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.ApuestaJuegoEquipoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo ApuestaJuegoEquipoTO serializables.
 */

public class ApuestaJuegoEquipoTO implements Serializable, ApuestaJuegoEquipoIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3725351617123606110L;
	private String idApuestaJuegoEquipo = null;
	private String idApuesta = null;
	private String idUsuarioJuegoEquipo = null;
	private String tipo = null;
	private String idStatusApuesta = null;
	/**
	 * @return Returns the idApuestaJuegoEquipo.
	 */
	public String getIdApuestaJuegoEquipo() {
		return idApuestaJuegoEquipo;
	}

	/**
	 * @param idApuestaJuegoEquipo
	 *            The idApuestaJuegoEquipo to set.
	 */
	public void setIdApuestaJuegoEquipo(String idApuestaJuegoEquipo) {
		this.idApuestaJuegoEquipo = idApuestaJuegoEquipo;
	}

	/**
	 * @return Returns the idApuesta.
	 */
	public String getIdApuesta() {
		return idApuesta;
	}

	/**
	 * @param idApuesta
	 *            The idApuesta to set.
	 */
	public void setIdApuesta(String idApuesta) {
		this.idApuesta = idApuesta;
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

	public String getIdStatusApuesta() {
		return idStatusApuesta;
	}

	public void setIdStatusApuesta(String idStatusApuesta) {
		this.idStatusApuesta = idStatusApuesta;
	}

	
	
}
