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
 * Permite la creacion de objetos de tipo ApuestaApostadorTO serializables.
 */

public interface ApuestaApostadorIF {

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
	 * @return Returns the idApostador.
	 */
	public String getIdApostador();

	/**
	 * @param idApostador
	 *            The idApostador to set.
	 */
	public void setIdApostador(String idApostador);

}
