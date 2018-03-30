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
 * Permite la creacion de objetos de tipo StatusDeporteTO serializables.
 */

public interface StatusDeporteIF {

	/**
	 * @return Returns the idStatusDeporte.
	 */
	public String getIdStatusDeporte();

	/**
	 * @param idStatusDeporte
	 *            The idStatusDeporte to set.
	 */
	public void setIdStatusDeporte(String idStatusDeporte);

	/**
	 * @return Returns the descStatusDeporte.
	 */
	public String getDescStatusDeporte();

	/**
	 * @param descStatusDeporte
	 *            The descStatusDeporte to set.
	 */
	public void setDescStatusDeporte(String descStatusDeporte);

}
