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
 * Permite la creacion de objetos de tipo LigaTO serializables.
 */

public interface LigaIF {

	/**
	 * @return Returns the idLiga.
	 */
	public String getIdLiga();

	/**
	 * @param idLiga
	 *            The idLiga to set.
	 */
	public void setIdLiga(String idLiga);

	/**
	 * @return Returns the descLiga.
	 */
	public String getDescLiga();

	/**
	 * @param descLiga
	 *            The descLiga to set.
	 */
	public void setDescLiga(String descLiga);

	/**
	 * @return Returns the iniciales.
	 */
	public String getIniciales();

	/**
	 * @param iniciales
	 *            The iniciales to set.
	 */
	public void setIniciales(String iniciales);

	/**
	 * @return Returns the idDeporte.
	 */
	public String getIdDeporte();

	/**
	 * @param idDeporte
	 *            The idDeporte to set.
	 */
	public void setIdDeporte(String idDeporte);

}
