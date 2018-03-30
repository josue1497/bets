/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.LanzadorIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo LanzadorTO serializables.
 */

public class LanzadorTO implements Serializable, LanzadorIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6776994889269458695L;
	private String idLanzador = null;
	private String nombreLanzador = null;
	private String idEquipo = null;

	/**
	 * @return Returns the idLanzador.
	 */
	public String getIdLanzador() {
		return idLanzador;
	}

	/**
	 * @param idLanzador
	 *            The idLanzador to set.
	 */
	public void setIdLanzador(String idLanzador) {
		this.idLanzador = idLanzador;
	}

	/**
	 * @return Returns the nombreLanzador.
	 */
	public String getNombreLanzador() {
		return nombreLanzador;
	}

	/**
	 * @param nombreLanzador
	 *            The nombreLanzador to set.
	 */
	public void setNombreLanzador(String nombreLanzador) {
		this.nombreLanzador = nombreLanzador;
	}

	/**
	 * @return Returns the idEquipo.
	 */
	public String getIdEquipo() {
		return idEquipo;
	}

	/**
	 * @param idEquipo
	 *            The idEquipo to set.
	 */
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}

}
