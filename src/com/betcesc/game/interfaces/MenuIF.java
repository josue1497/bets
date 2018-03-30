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
 * Permite la creacion de objetos de tipo MenuTO serializables.
 */

public interface MenuIF {

	/**
	 * @return Returns the idMenu.
	 */
	public String getIdMenu();

	/**
	 * @param idMenu
	 *            The idMenu to set.
	 */
	public void setIdMenu(String idMenu);

	/**
	 * @return Returns the descMenu.
	 */
	public String getDescMenu();

	/**
	 * @param descMenu
	 *            The descMenu to set.
	 */
	public void setDescMenu(String descMenu);

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
