/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.CampeonatoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo CampeonatoTO serializables.
 */

public class CampeonatoTO implements Serializable, CampeonatoIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1110757689896797841L;
	private String idCampeonato = null;
	private String descCampeonato = null;

	/**
	 * @return Returns the idCampeonato.
	 */
	public String getIdCampeonato() {
		return idCampeonato;
	}

	/**
	 * @param idCampeonato
	 *            The idCampeonato to set.
	 */
	public void setIdCampeonato(String idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	/**
	 * @return Returns the descCampeonato.
	 */
	public String getDescCampeonato() {
		return descCampeonato;
	}

	/**
	 * @param descCampeonato
	 *            The descCampeonato to set.
	 */
	public void setDescCampeonato(String descCampeonato) {
		this.descCampeonato = descCampeonato;
	}

}
