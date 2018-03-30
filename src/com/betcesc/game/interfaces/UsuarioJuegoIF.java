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
 * Permite la creacion de objetos de tipo UsuarioJuegoTO serializables.
 */

public interface UsuarioJuegoIF {

	/**
	 * @return Returns the idUsuario.
	 */
	public String getIdUsuario();

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdUsuario(String idUsuario);

	/**
	 * @return Returns the idJuego.
	 */
	public String getIdJuego();

	/**
	 * @param idJuego
	 *            The idJuego to set.
	 */
	public void setIdJuego(String idJuego);

	/**
	 * @return Returns the fechaSis.
	 */
	public String getFechaSis();

	/**
	 * @param fechaSis
	 *            The fechaSis to set.
	 */
	public void setFechaSis(String fechaSis);

	/**
	 * @return Returns the idStatusJuego.
	 */
	public String getIdStatusJuego();

	/**
	 * @param idStatusJuego
	 *            The idStatusJuego to set.
	 */
	public void setIdStatusJuego(String idStatusJuego);

}
