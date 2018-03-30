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
 * Permite la creacion de objetos de tipo JugadaJuegoEquipoTO serializables.
 */

public interface JugadaJuegoEquipoIF {

	/**
	 * @return Returns the idJugadaJuegoEquipo.
	 */
	public String getIdJugadaJuegoEquipo();

	/**
	 * @param idJugadaJuegoEquipo
	 *            The idJugadaJuegoEquipo to set.
	 */
	public void setIdJugadaJuegoEquipo(String idJugadaJuegoEquipo);

	/**
	 * @return Returns the idJugada.
	 */
	public String getIdJugada();

	/**
	 * @param idJugada
	 *            The idJugada to set.
	 */
	public void setIdJugada(String idJugada);

	/**
	 * @return Returns the idUsuarioJuegoEquipo.
	 */
	public String getIdUsuarioJuegoEquipo();

	/**
	 * @param idUsuarioJuegoEquipo
	 *            The idUsuarioJuegoEquipo to set.
	 */
	public void setIdUsuarioJuegoEquipo(String idUsuarioJuegoEquipo);

	/**
	 * @return Returns the tipo.
	 */
	public String getTipo();

	/**
	 * @param tipo
	 *            The tipo to set.
	 */
	public void setTipo(String tipo);

	public String getIdStatusJugada();

	public void setIdStatusJugada(String idStatusJugada);
}
