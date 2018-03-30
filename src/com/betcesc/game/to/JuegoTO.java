/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */
package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.JuegoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo JuegoTO serializables.
 */

public class JuegoTO implements Serializable, JuegoIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2857712948511955909L;
	private String idJuego = null;
    private String fechaSis = null;
    private String fechaIni = null;
    private String fechaFin = null;
    private String minutosCierre = null;
    private String idCampeonato = null;
    private String idStatusJuego = null;
    private String idUsuario = null;
    private String idDeporte = null;
    private String idLiga = null;
    private String spreadActivo = null;
    private String totalActivo = null;
    private String moneyActivo = null;
	private String idJuegoPadre = null;
    private String idUsuarioCreador = null;
    private String superSpreadActivo = null;
    private String dominioActual = null;
    private String dominioAnterior = null;
    private String idUsuarioTotaliza = null;
    
    
    /**
     * @return Returns the idJuego.
     */
    public String getIdJuego()
    {
        return idJuego;
    }

    /**
     * @param idJuego
     *            The idJuego to set.
     */
    public void setIdJuego(String idJuego)
    {
        this.idJuego = idJuego;
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
     * @return Returns the fechaIni.
     */
    public String getFechaIni()
    {
        return fechaIni;
    }

    /**
     * @param fechaIni
     *            The fechaIni to set.
     */
    public void setFechaIni(String fechaIni)
    {
        this.fechaIni = fechaIni;
    }

    
    /**
     * @return Returns the fechaFin.
     */
    public String getFechaFin()
    {
        return fechaFin;
    }

    /**
     * @param fechaFin
     *            The fechaFin to set.
     */
    public void setFechaFin(String fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    
    /**
     * @return Returns the minutosCierre.
     */
    public String getMinutosCierre()
    {
        return minutosCierre;
    }

    /**
     * @param minutosCierre
     *            The minutosCierre to set.
     */
    public void setMinutosCierre(String minutosCierre)
    {
        this.minutosCierre = minutosCierre;
    }

    
    /**
     * @return Returns the idCampeonato.
     */
    public String getIdCampeonato()
    {
        return idCampeonato;
    }

    /**
     * @param idCampeonato
     *            The idCampeonato to set.
     */
    public void setIdCampeonato(String idCampeonato)
    {
        this.idCampeonato = idCampeonato;
    }

    
    /**
     * @return Returns the idStatusJuego.
     */
    public String getIdStatusJuego()
    {
        return idStatusJuego;
    }

    /**
     * @param idStatusJuego
     *            The idStatusJuego to set.
     */
    public void setIdStatusJuego(String idStatusJuego)
    {
        this.idStatusJuego = idStatusJuego;
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
     * @return Returns the idDeporte.
     */
    public String getIdDeporte()
    {
        return idDeporte;
    }

    /**
     * @param idDeporte
     *            The idDeporte to set.
     */
    public void setIdDeporte(String idDeporte)
    {
        this.idDeporte = idDeporte;
    }

	public String getIdLiga() {
		return idLiga;
	}

	public void setIdLiga(String idLiga) {
		this.idLiga = idLiga;
	}

	public String getMoneyActivo() {
		return moneyActivo;
	}

	public void setMoneyActivo(String moneyActivo) {
		this.moneyActivo = moneyActivo;
	}

	public String getSpreadActivo() {
		return spreadActivo;
	}

	public void setSpreadActivo(String spreadActivo) {
		this.spreadActivo = spreadActivo;
	}

	public String getTotalActivo() {
		return totalActivo;
	}

	public void setTotalActivo(String totalActivo) {
		this.totalActivo = totalActivo;
	}

	public String getIdJuegoPadre() {
		return idJuegoPadre;
	}

	public void setIdJuegoPadre(String idJuegoPadre) {
		this.idJuegoPadre = idJuegoPadre;
	}

	public String getIdUsuarioCreador() {
		return idUsuarioCreador;
	}

	public void setIdUsuarioCreador(String idUsuarioCreador) {
		this.idUsuarioCreador = idUsuarioCreador;
	}

	public String getSuperSpreadActivo() {
		return superSpreadActivo;
	}

	public void setSuperSpreadActivo(String superSpreadActivo) {
		this.superSpreadActivo = superSpreadActivo;
	}

	public String getDominioActual() {
		return dominioActual;
	}

	public void setDominioActual(String dominioActual) {
		this.dominioActual = dominioActual;
	}

	public String getDominioAnterior() {
		return dominioAnterior;
	}

	public void setDominioAnterior(String dominioAnterior) {
		this.dominioAnterior = dominioAnterior;
	}

	public String getIdUsuarioTotaliza() {
		return idUsuarioTotaliza;
	}

	public void setIdUsuarioTotaliza(String idUsuarioTotaliza) {
		this.idUsuarioTotaliza = idUsuarioTotaliza;
	}

	
}
