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
 * Permite la creacion de objetos de tipo EquipoTO serializables.
 */

public interface EquipoIF {

	/**
	 * @return Returns the idEquipo.
	 */
	public String getIdEquipo();

	/**
	 * @param idEquipo
	 *            The idEquipo to set.
	 */
	public void setIdEquipo(String idEquipo);

	/**
	 * @return Returns the descEquipo.
	 */
	public String getDescEquipo();

	/**
	 * @param descEquipo
	 *            The descEquipo to set.
	 */
	public void setDescEquipo(String descEquipo);
	
	public String getAbreviatura();
	
	public void setAbreviatura(String abreviatura);	

	public String getDescCorta();

	public void setDescCorta(String descCorta);
	
}
