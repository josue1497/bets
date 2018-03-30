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
 * Permite la creacion de objetos de tipo UsuarioJuegoEquipoTO serializables.
 */

public interface UsuarioJuegoEquipoIF {

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
	 * @return Returns the idUsuario.
	 */
	public String getIdUsuario();

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdUsuario(String idUsuario);

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
	 * @return Returns the fechaSis.
	 */
	public String getFechaSis();

	/**
	 * @param fechaSis
	 *            The fechaSis to set.
	 */
	public void setFechaSis(String fechaSis);

	/**
	 * @return Returns the spread.
	 */
	public String getSpread();

	/**
	 * @param spread
	 *            The spread to set.
	 */
	public void setSpread(String spread);

	/**
	 * @return Returns the spreadLogro.
	 */
	public String getSpreadLogro();

	/**
	 * @param spreadLogro
	 *            The spreadLogro to set.
	 */
	public void setSpreadLogro(String spreadLogro);

	/**
	 * @return Returns the total.
	 */
	public String getTotal();

	/**
	 * @param total
	 *            The total to set.
	 */
	public void setTotal(String total);

	/**
	 * @return Returns the totalLogro.
	 */
	public String getTotalLogro();

	/**
	 * @param totalLogro
	 *            The totalLogro to set.
	 */
	public void setTotalLogro(String totalLogro);

	/**
	 * @return Returns the moneyLine.
	 */
	public String getMoneyLine();

	/**
	 * @param moneyLine
	 *            The moneyLine to set.
	 */
	public void setMoneyLine(String moneyLine);

	public String getIdStatusJuego();

	public void setIdStatusJuego(String idStatusJuego);

	/**
	 * @return Returns the spread.
	 */
	public String getSuperSpread();

	/**
	 * @param spread
	 *            The spread to set.
	 */
	public void setSuperSpread(String spread);

	/**
	 * @return Returns the spreadLogro.
	 */
	public String getSuperSpreadLogro();

	/**
	 * @param spreadLogro
	 *            The spreadLogro to set.
	 */
	public void setSuperSpreadLogro(String spreadLogro);

	public String getDesactivado();

	public void setDesactivado(String desactivado);
}
