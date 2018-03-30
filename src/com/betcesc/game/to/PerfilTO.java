/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.PerfilIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo PerfilTO serializables.
 */

public class PerfilTO implements Serializable, PerfilIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2051880659602438756L;
	private String idPerfil = null;
	private String descPerfil = null;

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

	/**
	 * @return Returns the descPerfil.
	 */
	public String getDescPerfil() {
		return descPerfil;
	}

	/**
	 * @param descPerfil
	 *            The descPerfil to set.
	 */
	public void setDescPerfil(String descPerfil) {
		this.descPerfil = descPerfil;
	}

}
