/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.PerfilOpcionIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo PerfilOpcionTO serializables.
 */

public class PerfilOpcionTO implements Serializable, PerfilOpcionIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 888514868556297166L;
	private String idOpcion = null;
	private String idPerfil = null;

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
	 * @return Returns the idPerfil.
	 */
	public String getIdRol() {
		return idPerfil;
	}

	/**
	 * @param idPerfil
	 *            The idPerfil to set.
	 */
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

}
