/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.JuegoEquipoLanzadorIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo JuegoEquipoLanzadorTO serializables.
 */

public class JuegoEquipoLanzadorTO implements Serializable, JuegoEquipoLanzadorIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4127324592075485465L;
	private String idJuegoEquipo = null;
	private String idLanzador = null;

	/**
	 * @return Returns the idJuegoEquipo.
	 */
	public String getIdJuegoEquipo() {
		return idJuegoEquipo;
	}

	/**
	 * @param idJuegoEquipo
	 *            The idJuegoEquipo to set.
	 */
	public void setIdJuegoEquipo(String idJuegoEquipo) {
		this.idJuegoEquipo = idJuegoEquipo;
	}

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

}
