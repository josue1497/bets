/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.interfaces;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero
 * 
 * Permite la creacion de objetos de tipo ApuestaJuegoEquipoTO serializables.
 */

public interface ApuestaJuegoEquipoIF {

	/**
	 * @return Returns the idApuestaJuegoEquipo.
	 */
	public String getIdApuestaJuegoEquipo();

	/**
	 * @param idApuestaJuegoEquipo
	 *            The idApuestaJuegoEquipo to set.
	 */
	public void setIdApuestaJuegoEquipo(String idApuestaJuegoEquipo);

	/**
	 * @return Returns the idApuesta.
	 */
	public String getIdApuesta();

	/**
	 * @param idApuesta
	 *            The idApuesta to set.
	 */
	public void setIdApuesta(String idApuesta);

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

	public String getIdStatusApuesta();

	public void setIdStatusApuesta(String idStatusApuesta);
}
