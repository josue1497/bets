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
 * Permite la creacion de objetos de tipo ApostadorTO serializables.
 */

public interface ApostadorIF {

	/**
	 * @return Returns the idApostador.
	 */
	public String getIdApostador();

	/**
	 * @param idApostador
	 *            The idApostador to set.
	 */
	public void setIdApostador(String idApostador);

	/**
	 * @return Returns the nombreApostador.
	 */
	public String getNombreApostador();

	/**
	 * @param nombreApostador
	 *            The nombreApostador to set.
	 */
	public void setNombreApostador(String nombreApostador);

	/**
	 * @return Returns the telefono.
	 */
	public String getTelefono();

	/**
	 * @param telefono
	 *            The telefono to set.
	 */
	public void setTelefono(String telefono);

	/**
	 * @return Returns the correo.
	 */
	public String getCorreo();

	/**
	 * @param correo
	 *            The correo to set.
	 */
	public void setCorreo(String correo);

	/**
	 * @return Returns the direccion.
	 */
	public String getDireccion();

	/**
	 * @param direccion
	 *            The direccion to set.
	 */
	public void setDireccion(String direccion);

}
