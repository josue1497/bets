/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.RolMenuIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo RolMenuTO serializables.
 */

public class RolMenuTO implements Serializable, RolMenuIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 888514868556297166L;
	private String idMenu = null;
	private String idRol = null;

	/**
	 * @return Returns the idMenu.
	 */
	public String getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu
	 *            The idMenu to set.
	 */
	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}

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

}
