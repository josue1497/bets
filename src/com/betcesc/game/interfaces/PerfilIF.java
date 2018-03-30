/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
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

public interface PerfilIF {

	/**
	 * @return Returns the idRol.
	 */
	public String getIdRol();

	/**
	 * @param idRol
	 *            The idRol to set.
	 */
	public void setIdPerfil(String idRol);

	/**
	 * @return Returns the descRol.
	 */
	public String getDescPerfil();

	/**
	 * @param descRol
	 *            The descRol to set.
	 */
	public void setDescPerfil(String descRol);

}
