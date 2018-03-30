/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.EquipoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo EquipoTO serializables.
 */

public class EquipoTO implements Serializable, EquipoIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2204590127986658595L;
	private String idEquipo = null;
	private String descEquipo = null;
	private String abreviatura = null;
	private String descCorta = null;

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

	/**
	 * @return Returns the descEquipo.
	 */
	public String getDescEquipo() {
		return descEquipo;
	}

	/**
	 * @param descEquipo
	 *            The descEquipo to set.
	 */
	public void setDescEquipo(String descEquipo) {
		this.descEquipo = descEquipo;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getDescCorta() {
		return descCorta;
	}

	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}

	
}
