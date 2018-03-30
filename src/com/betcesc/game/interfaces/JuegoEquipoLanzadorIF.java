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
 * Permite la creacion de objetos de tipo JuegoEquipoLanzadorTO serializables.
 */

public interface JuegoEquipoLanzadorIF {

	/**
	 * @return Returns the idJuegoEquipo.
	 */
	public String getIdJuegoEquipo();

	/**
	 * @param idJuegoEquipo
	 *            The idJuegoEquipo to set.
	 */
	public void setIdJuegoEquipo(String idJuegoEquipo);

	/**
	 * @return Returns the idLanzador.
	 */
	public String getIdLanzador();

	/**
	 * @param idLanzador
	 *            The idLanzador to set.
	 */
	public void setIdLanzador(String idLanzador);

}
