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
 * Permite la creacion de objetos de tipo JugadaApostadorTO serializables.
 */

public interface JugadaApostadorIF {

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
	 * @return Returns the idApostador.
	 */
	public String getIdApostador();

	/**
	 * @param idApostador
	 *            The idApostador to set.
	 */
	public void setIdApostador(String idApostador);

}
