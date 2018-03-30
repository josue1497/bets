/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.RolIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo RolTO serializables.
 */

public class RolTO implements Serializable, RolIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2051880659602438756L;
	private String idRol = null;
	private String descRol = null;

	/**
	 * @return Returns the idRol.
	 */
	public String getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol
	 *            The idRol to set.
	 */
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return Returns the descRol.
	 */
	public String getDescRol() {
		return descRol;
	}

	/**
	 * @param descRol
	 *            The descRol to set.
	 */
	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}

}
