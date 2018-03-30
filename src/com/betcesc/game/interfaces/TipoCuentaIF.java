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
 * Permite la creacion de objetos de tipo TipoCuentaTO serializables.
 */

public interface TipoCuentaIF {

	/**
	 * @return Returns the idTipoCuenta.
	 */
	public String getIdTipoCuenta();

	/**
	 * @param idTipoCuenta
	 *            The idTipoCuenta to set.
	 */
	public void setIdTipoCuenta(String idTipoCuenta);

	/**
	 * @return Returns the descTipoCuenta.
	 */
	public String getDescTipoCuenta();

	/**
	 * @param descTipoCuenta
	 *            The descTipoCuenta to set.
	 */
	public void setDescTipoCuenta(String descTipoCuenta);

}
