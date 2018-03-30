/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.interfaces;


/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo StatusTO serializables.
 */

public interface StatusIF {

	/**
	 * @return Returns the idStatus.
	 */
	public String getIdStatus();

	/**
	 * @param idStatus
	 *            The idStatus to set.
	 */
	public void setIdStatus(String idStatus);

	/**
	 * @return Returns the descStatus.
	 */
	public String getDescStatus();

	/**
	 * @param descStatus
	 *            The descStatus to set.
	 */
	public void setDescStatus(String descStatus);

}
