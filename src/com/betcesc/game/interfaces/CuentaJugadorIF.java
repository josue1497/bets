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
 * Permite la creacion de objetos de tipo CuentaJugadorTO serializables.
 */

public interface CuentaJugadorIF {

	/**
	 * @return Returns the idCuenta.
	 */
	public String getIdCuenta();

	/**
	 * @param idCuenta
	 *            The idCuenta to set.
	 */
	public void setIdCuenta(String idCuenta);

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
	 * @return Returns the referencia.
	 */
	public String getReferencia();

	/**
	 * @param referencia
	 *            The referencia to set.
	 */
	public void setReferencia(String referencia);

	/**
	 * @return Returns the operacion.
	 */
	public String getOperacion();

	/**
	 * @param operacion
	 *            The operacion to set.
	 */
	public void setOperacion(String operacion);

	/**
	 * @return Returns the monto.
	 */
	public String getMonto();

	/**
	 * @param monto
	 *            The monto to set.
	 */
	public void setMonto(String monto);

	/**
	 * @return Returns the concepto.
	 */
	public String getConcepto();

	/**
	 * @param concepto
	 *            The concepto to set.
	 */
	public void setConcepto(String concepto);

	/**
	 * @return Returns the idUsuario.
	 */
	public String getIdJugador();

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdJugador(String idJugador);

	/**
	 * @return Returns the idUsuario.
	 */
	public String getIdUsuario();

	/**
	 * @param idUsuario
	 *            The idUsuario to set.
	 */
	public void setIdUsuario(String idUsuario);

	public String getNombreJugador();
	
	public void setNombreJugador(String nombreJugador);
	
	public String getTipo();
	
	public void setTipo(String tipo);

}
