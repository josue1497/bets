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
 * Permite la creacion de objetos de tipo EquipoLigaTO serializables.
 */

public interface EquipoLigaIF {

	/**
	 * @return Returns the idEquipo.
	 */
	public String getIdEquipo();

	/**
	 * @param idEquipo
	 *            The idEquipo to set.
	 */
	public void setIdEquipo(String idEquipo);

	/**
	 * @return Returns the idLiga.
	 */
	public String getIdLiga();

	/**
	 * @param idLiga
	 *            The idLiga to set.
	 */
	public void setIdLiga(String idLiga);

}
