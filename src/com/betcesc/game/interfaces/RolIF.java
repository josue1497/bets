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
 * Permite la creacion de objetos de tipo RolTO serializables.
 */

public interface RolIF {

	/**
	 * @return Returns the idRol.
	 */
	public String getIdRol();

	/**
	 * @param idRol
	 *            The idRol to set.
	 */
	public void setIdRol(String idRol);

	/**
	 * @return Returns the descRol.
	 */
	public String getDescRol();

	/**
	 * @param descRol
	 *            The descRol to set.
	 */
	public void setDescRol(String descRol);

}
