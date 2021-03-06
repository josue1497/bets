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
 * Permite la creacion de objetos de tipo StatusJuegoTO serializables.
 */

public interface StatusJuegoIF {

	/**
	 * @return Returns the idStatusJuego.
	 */
	public String getIdStatusJuego();

	/**
	 * @param idStatusJuego
	 *            The idStatusJuego to set.
	 */
	public void setIdStatusJuego(String idStatusJuego);

	/**
	 * @return Returns the descStatusJuego.
	 */
	public String getDescStatusJuego();

	/**
	 * @param descStatusJuego
	 *            The descStatusJuego to set.
	 */
	public void setDescStatusJuego(String descStatusJuego);

}
