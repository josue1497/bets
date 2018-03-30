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
 * Permite la creacion de objetos de tipo OpcionTO serializables.
 */

public interface OpcionIF {

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
	 * @return Returns the descOpcion.
	 */
	public String getDescOpcion();

	/**
	 * @param descOpcion
	 *            The descOpcion to set.
	 */
	public void setDescOpcion(String descOpcion);

	/**
	 * @return Returns the orden.
	 */
	public String getOrden();

	/**
	 * @param orden
	 *            The orden to set.
	 */
	public void setOrden(String orden);

	/**
	 * @return Returns the accion.
	 */
	public String getAccion();

	/**
	 * @param accion
	 *            The accion to set.
	 */
	public void setAccion(String accion);

	/**
	 * @return Returns the imagen.
	 */
	public String getImagen();

	/**
	 * @param imagen
	 *            The imagen to set.
	 */
	public void setImagen(String imagen);

}
