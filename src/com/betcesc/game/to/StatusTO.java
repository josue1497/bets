/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo StatusTO serializables.
 */

public class StatusTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8210347726058546472L;
	private String idStatus = null;
	private String descStatus = null;

	/**
	 * @return Returns the idStatus.
	 */
	public String getIdStatus() {
		return idStatus;
	}

	/**
	 * @param idStatus
	 *            The idStatus to set.
	 */
	public void setIdStatus(String idStatus) {
		this.idStatus = idStatus;
	}

	/**
	 * @return Returns the descStatus.
	 */
	public String getDescStatus() {
		return descStatus;
	}

	/**
	 * @param descStatus
	 *            The descStatus to set.
	 */
	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

}
