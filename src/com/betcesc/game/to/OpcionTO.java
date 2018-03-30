/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.OpcionIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo OpcionTO serializables.
 */

public class OpcionTO implements Serializable, OpcionIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 297479198266157387L;
	private String idOpcion = null;
	private String descOpcion = null;
	private String orden = null;
	private String accion = null;
	private String imagen = null;
	
	public OpcionTO() {
		
	}
	
	public OpcionTO(String descOpcion, String accion) {
		setDescOpcion(descOpcion);
		setAccion(accion);
	}
	

	/**
	 * @return Returns the idOpcion.
	 */
	public String getIdOpcion() {
		return idOpcion;
	}

	/**
	 * @param idOpcion
	 *            The idOpcion to set.
	 */
	public void setIdOpcion(String idOpcion) {
		this.idOpcion = idOpcion;
	}

	/**
	 * @return Returns the descOpcion.
	 */
	public String getDescOpcion() {
		return descOpcion;
	}

	/**
	 * @param descOpcion
	 *            The descOpcion to set.
	 */
	public void setDescOpcion(String descOpcion) {
		this.descOpcion = descOpcion;
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
