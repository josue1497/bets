/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.MenuIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo MenuTO serializables.
 */

public class MenuTO implements Serializable, MenuIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 297479198266157387L;
	private String idMenu = null;
	private String descMenu = null;
	private String orden = null;
	private String accion = null;
	private String imagen = null;
	
	public MenuTO() {
		
	}
	
	public MenuTO(String descMenu, String accion) {
		setDescMenu(descMenu);
		setAccion(accion);
	}
	

	/**
	 * @return Returns the idMenu.
	 */
	public String getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu
	 *            The idMenu to set.
	 */
	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}

	/**
	 * @return Returns the descMenu.
	 */
	public String getDescMenu() {
		return descMenu;
	}

	/**
	 * @param descMenu
	 *            The descMenu to set.
	 */
	public void setDescMenu(String descMenu) {
		this.descMenu = descMenu;
	}

	/**
	 * @return Returns the orden.
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            The orden to set.
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * @return Returns the accion.
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion
	 *            The accion to set.
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return Returns the imagen.
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            The imagen to set.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
