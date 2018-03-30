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
 * Permite la creacion de objetos de tipo CampeonatoTO serializables.
 */

public interface CampeonatoIF {

	/**
	 * @return Returns the idCampeonato.
	 */
	public String getIdCampeonato();

	/**
	 * @param idCampeonato
	 *            The idCampeonato to set.
	 */
	public void setIdCampeonato(String idCampeonato);

	/**
	 * @return Returns the descCampeonato.
	 */
	public String getDescCampeonato();

	/**
	 * @param descCampeonato
	 *            The descCampeonato to set.
	 */
	public void setDescCampeonato(String descCampeonato);

}
