/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.StatusDeporteIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo StatusDeporteTO serializables.
 */

public class StatusDeporteTO implements Serializable, StatusDeporteIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4474749088980957428L;
	private String idStatusDeporte = null;
	private String descStatusDeporte = null;

	/**
	 * @return Returns the idStatusDeporte.
	 */
	public String getIdStatusDeporte() {
		return idStatusDeporte;
	}

	/**
	 * @param idStatusDeporte
	 *            The idStatusDeporte to set.
	 */
	public void setIdStatusDeporte(String idStatusDeporte) {
		this.idStatusDeporte = idStatusDeporte;
	}

	/**
	 * @return Returns the descStatusDeporte.
	 */
	public String getDescStatusDeporte() {
		return descStatusDeporte;
	}

	/**
	 * @param descStatusDeporte
	 *            The descStatusDeporte to set.
	 */
	public void setDescStatusDeporte(String descStatusDeporte) {
		this.descStatusDeporte = descStatusDeporte;
	}

}
