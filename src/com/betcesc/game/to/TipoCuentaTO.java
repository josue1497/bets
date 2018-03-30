/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.TipoCuentaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo TipoCuentaTO serializables.
 */

public class TipoCuentaTO implements Serializable, TipoCuentaIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3566368856338972615L;
	private String idTipoCuenta = null;
	private String descTipoCuenta = null;

	/**
	 * @return Returns the idTipoCuenta.
	 */
	public String getIdTipoCuenta() {
		return idTipoCuenta;
	}

	/**
	 * @param idTipoCuenta
	 *            The idTipoCuenta to set.
	 */
	public void setIdTipoCuenta(String idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	/**
	 * @return Returns the descTipoCuenta.
	 */
	public String getDescTipoCuenta() {
		return descTipoCuenta;
	}

	/**
	 * @param descTipoCuenta
	 *            The descTipoCuenta to set.
	 */
	public void setDescTipoCuenta(String descTipoCuenta) {
		this.descTipoCuenta = descTipoCuenta;
	}

}
