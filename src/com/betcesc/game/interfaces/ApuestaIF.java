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
 * Permite la creacion de objetos de tipo ApuestaTO serializables.
 */

public interface ApuestaIF 
{

    
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
     * @return Returns the idStatusApuesta.
     */
    public String getIdStatusApuesta();

    /**
     * @param idStatusApuesta
     *            The idStatusApuesta to set.
     */
    public void setIdStatusApuesta(String idStatusApuesta);


	public String getDiasExpira();

	public void setDiasExpira(String diasExpira);
	
	public String getFechaPago();

	public void setFechaPago(String fechaPago);

	public String getDetalleEquipo();

	public void setDetalleEquipo(String detalleEquipo);	

	public String getCancelada();

	public void setCancelada(String cancelada);
}
