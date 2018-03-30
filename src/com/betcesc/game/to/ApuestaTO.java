/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.ApuestaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo ApuestaTO serializables.
 */

public class ApuestaTO implements Serializable, ApuestaIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4968850773061672256L;
	private String idApuesta = null;
    private String fechaSis = null;
    private String fechaExp = null;
    private String montoApostado = null;
    private String montoPremio = null;
    private String montoPagado = null;
    private String idUsuario = null;
    private String idStatusApuesta = null;
    private String diasExpira = null;
    private String fechaPago = null;
    private String detalleEquipo = null;
    private String cancelada = null;
    
    /**
     * @return Returns the idApuesta.
     */
    public String getIdApuesta()
    {
        return idApuesta;
    }

    /**
     * @param idApuesta
     *            The idApuesta to set.
     */
    public void setIdApuesta(String idApuesta)
    {
        this.idApuesta = idApuesta;
    }

    
    /**
     * @return Returns the fechaSis.
     */
    public String getFechaSis()
    {
        return fechaSis;
    }

    /**
     * @param fechaSis
     *            The fechaSis to set.
     */
    public void setFechaSis(String fechaSis)
    {
        this.fechaSis = fechaSis;
    }

    
    /**
     * @return Returns the fechaExp.
     */
    public String getFechaExp()
    {
        return fechaExp;
    }

    /**
     * @param fechaExp
     *            The fechaExp to set.
     */
    public void setFechaExp(String fechaExp)
    {
        this.fechaExp = fechaExp;
    }

    
    /**
     * @return Returns the montoApostado.
     */
    public String getMontoApostado()
    {
        return montoApostado;
    }

    /**
     * @param montoApostado
     *            The montoApostado to set.
     */
    public void setMontoApostado(String montoApostado)
    {
        this.montoApostado = montoApostado;
    }

    
    /**
     * @return Returns the montoPremio.
     */
    public String getMontoPremio()
    {
        return montoPremio;
    }

    /**
     * @param montoPremio
     *            The montoPremio to set.
     */
    public void setMontoPremio(String montoPremio)
    {
        this.montoPremio = montoPremio;
    }

    
    /**
     * @return Returns the montoPagado.
     */
    public String getMontoPagado()
    {
        return montoPagado;
    }

    /**
     * @param montoPagado
     *            The montoPagado to set.
     */
    public void setMontoPagado(String montoPagado)
    {
        this.montoPagado = montoPagado;
    }

    
    /**
     * @return Returns the idUsuario.
     */
    public String getIdUsuario()
    {
        return idUsuario;
    }

    /**
     * @param idUsuario
     *            The idUsuario to set.
     */
    public void setIdUsuario(String idUsuario)
    {
        this.idUsuario = idUsuario;
    }

    
    /**
     * @return Returns the idStatusApuesta.
     */
    public String getIdStatusApuesta()
    {
        return idStatusApuesta;
    }

    /**
     * @param idStatusApuesta
     *            The idStatusApuesta to set.
     */
    public void setIdStatusApuesta(String idStatusApuesta)
    {
        this.idStatusApuesta = idStatusApuesta;
    }

	public String getDiasExpira() {
		return diasExpira;
	}

	public void setDiasExpira(String diasExpira) {
		this.diasExpira = diasExpira;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getDetalleEquipo() {
		return detalleEquipo;
	}

	public void setDetalleEquipo(String detalleEquipo) {
		this.detalleEquipo = detalleEquipo;
	}

	public String getCancelada() {
		return cancelada;
	}

	public void setCancelada(String cancelada) {
		this.cancelada = cancelada;
	}

	
}
