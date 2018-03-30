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
 * Permite la creacion de objetos de tipo JugadaTO serializables.
 */

public interface JugadaIF 
{

    
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
     * @return Returns the fechaSis.
     */
    public String getFechaSis();

    /**
     * @param fechaSis
     *            The fechaSis to set.
     */
    public void setFechaSis(String fechaSis);

    
    /**
     * @return Returns the fechaExp.
     */
    public String getFechaExp();

    /**
     * @param fechaExp
     *            The fechaExp to set.
     */
    public void setFechaExp(String fechaExp);

    
    /**
     * @return Returns the montoApostado.
     */
    public String getMontoApostado();

    /**
     * @param montoApostado
     *            The montoApostado to set.
     */
    public void setMontoApostado(String montoApostado);

    
    /**
     * @return Returns the montoPremio.
     */
    public String getMontoPremio();

    /**
     * @param montoPremio
     *            The montoPremio to set.
     */
    public void setMontoPremio(String montoPremio);

    
    /**
     * @return Returns the montoPagado.
     */
    public String getMontoPagado();

    /**
     * @param montoPagado
     *            The montoPagado to set.
     */
    public void setMontoPagado(String montoPagado);

    
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
     * @return Returns the idStatusJugada.
     */
    public String getIdStatusJugada();

    /**
     * @param idStatusJugada
     *            The idStatusJugada to set.
     */
    public void setIdStatusJugada(String idStatusJugada);


	public String getDiasExpira();

	public void setDiasExpira(String diasExpira);
	
	public String getFechaPago();

	public void setFechaPago(String fechaPago);

	public String getDetalleEquipo();

	public void setDetalleEquipo(String detalleEquipo);	

	public String getCancelada();

	public void setCancelada(String cancelada);

	public String getItemsJugada();

	public void setItemsJugada(String itemsJugada);

	public String getBono();

	public void setBono(String bono);
}
