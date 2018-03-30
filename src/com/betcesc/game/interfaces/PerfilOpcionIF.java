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
 * Permite la creacion de objetos de tipo PerfilOpcionTO serializables.
 */

public interface PerfilOpcionIF {

	/**
	 * @return Returns the idOpcion.
	 */
	public String getIdOpcion();

	/**
	 * @param idOpcion
	 *            The idOpcion to set.
	 */
	public void setIdOpcion(String idOpcion);

	/**
	 * @return Returns the idPerfil.
	 */
	public String getIdRol();

	/**
	 * @param idPerfil
	 *            The idPerfil to set.
	 */
	public void setIdPerfil(String idPerfil);

}
