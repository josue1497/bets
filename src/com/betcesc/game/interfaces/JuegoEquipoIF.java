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
 * Permite la creacion de objetos de tipo JuegoEquipoTO serializables.
 */

public interface JuegoEquipoIF {

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
	 * @return Returns the idJuego.
	 */
	public String getIdJuego();

	/**
	 * @param idJuego
	 *            The idJuego to set.
	 */
	public void setIdJuego(String idJuego);

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
	 * @return Returns the ganador.
	 */
	public String getGanador();

	/**
	 * @param ganador
	 *            The ganador to set.
	 */
	public void setGanador(String ganador);

	public String getPuntos();

	public void setPuntos(String puntos);
	
	public String getReferencia();

	public void setReferencia(String referencia);

	public String getReferenciaAB();

	public void setReferenciaAB(String referenciaAB);

	public String getReferenciaRunline();

	public void setReferenciaRunline(String referenciaRunline);

	public String getReferenciaSuperRunline();

	public void setReferenciaSuperRunline(String referenciaSuperRunline);
}
