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
 * Permite la creacion de objetos de tipo JuegoTO serializables.
 */

public interface JuegoIF {

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
	 * @return Returns the fechaIni.
	 */
	public String getFechaIni();

	/**
	 * @param fechaIni
	 *            The fechaIni to set.
	 */
	public void setFechaIni(String fechaIni);

	/**
	 * @return Returns the fechaFin.
	 */
	public String getFechaFin();

	/**
	 * @param fechaFin
	 *            The fechaFin to set.
	 */
	public void setFechaFin(String fechaFin);

	/**
	 * @return Returns the minutosCierre.
	 */
	public String getMinutosCierre();

	/**
	 * @param minutosCierre
	 *            The minutosCierre to set.
	 */
	public void setMinutosCierre(String minutosCierre);

	/**
	 * @return Returns the idCampeonato.
	 */
	public String getIdCampeonato();

	/**
	 * @param idCampeonato
	 *            The idCampeonato to set.
	 */
	public void setIdCampeonato(String idCampeonato);

	/**
	 * @return Returns the idStatusJuego.
	 */
	public String getIdStatusJuego();

	/**
	 * @param idStatusJuego
	 *            The idStatusJuego to set.
	 */
	public void setIdStatusJuego(String idStatusJuego);

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
	 * @return Returns the idDeporte.
	 */
	public String getIdDeporte();

	/**
	 * @param idDeporte
	 *            The idDeporte to set.
	 */
	public void setIdDeporte(String idDeporte);

	public String getIdLiga();

	public void setIdLiga(String idLiga);

	public String getMoneyActivo();

	public void setMoneyActivo(String moneyActivo);

	public String getSpreadActivo();

	public void setSpreadActivo(String spreadActivo);

	public String getTotalActivo();

	public void setTotalActivo(String totalActivo);

	public String getIdJuegoPadre();
	
	public void setIdJuegoPadre(String idJuegoPadre);

	public String getIdUsuarioCreador();
	
	public void setIdUsuarioCreador(String idUsuarioCreador);
	
	public String getSuperSpreadActivo();

	public void setSuperSpreadActivo(String spreadActivo);

	public String getDominioActual();

	public void setDominioActual(String dominioActual);

	public String getDominioAnterior();

	public void setDominioAnterior(String dominioAnterior);

	public String getIdUsuarioTotaliza();

	public void setIdUsuarioTotaliza(String idUsuarioTotaliza);

}
