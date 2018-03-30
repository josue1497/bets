/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.JugadaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo JugadaTO serializables.
 */

public class JugadaTO implements Serializable, JugadaIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4968850773061672256L;
	private String idJugada = null;
    private String fechaSis = null;
    private String fechaExp = null;
    private String montoApostado = null;
    private String montoPremio = null;
    private String montoPagado = null;
    private String idUsuario = null;
    private String idStatusJugada = null;
    private String diasExpira = null;
    private String fechaPago = null;
    private String detalleEquipo = null;
    private String cancelada = null;
    private String itemsJugada = null;
    private String bono = null;
    
    /**
     * @return Returns the idJugada.
     */
    public String getIdJugada()
    {
        return idJugada;
    }

    /**
     * @param idJugada
     *            The idJugada to set.
     */
    public void setIdJugada(String idJugada)
    {
        this.idJugada = idJugada;
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
     * @return Returns the idStatusJugada.
     */
    public String getIdStatusJugada()
    {
        return idStatusJugada;
    }

    /**
     * @param idStatusJugada
     *            The idStatusJugada to set.
     */
    public void setIdStatusJugada(String idStatusJugada)
    {
        this.idStatusJugada = idStatusJugada;
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

	public String getItemsJugada() {
		return itemsJugada;
	}

	public void setItemsJugada(String itemsJugada) {
		this.itemsJugada = itemsJugada;
	}

	public String getBono() {
		return bono;
	}

	public void setBono(String bono) {
		this.bono = bono;
	}


	
}
