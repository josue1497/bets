/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.JuegoEquipoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo JuegoEquipoTO serializables.
 */

public class JuegoEquipoTO implements Serializable, JuegoEquipoIF 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8357839141758994997L;
	private String idJuegoEquipo = null;
    private String idJuego = null;
    private String idEquipo = null;
    private String ganador = null;
    private String puntos = null;
    private String referencia = null;
    private String referenciaRunline = null;
    private String referenciaAB = null;
    private String referenciaSuperRunline = null;
    
    /**
     * @return Returns the idJuegoEquipo.
     */
    public String getIdJuegoEquipo()
    {
        return idJuegoEquipo;
    }

    /**
     * @param idJuegoEquipo
     *            The idJuegoEquipo to set.
     */
    public void setIdJuegoEquipo(String idJuegoEquipo)
    {
        this.idJuegoEquipo = idJuegoEquipo;
    }

    
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
     * @return Returns the idEquipo.
     */
    public String getIdEquipo()
    {
        return idEquipo;
    }

    /**
     * @param idEquipo
     *            The idEquipo to set.
     */
    public void setIdEquipo(String idEquipo)
    {
        this.idEquipo = idEquipo;
    }

    
    /**
     * @return Returns the ganador.
     */
    public String getGanador()
    {
        return ganador;
    }

    /**
     * @param ganador
     *            The ganador to set.
     */
    public void setGanador(String ganador)
    {
        this.ganador = ganador;
    }

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getReferenciaAB() {
		return referenciaAB;
	}

	public void setReferenciaAB(String referenciaAB) {
		this.referenciaAB = referenciaAB;
	}

	public String getReferenciaRunline() {
		return referenciaRunline;
	}

	public void setReferenciaRunline(String referenciaRunline) {
		this.referenciaRunline = referenciaRunline;
	}

	public String getReferenciaSuperRunline() {
		return referenciaSuperRunline;
	}

	public void setReferenciaSuperRunline(String referenciaSuperRunline) {
		this.referenciaSuperRunline = referenciaSuperRunline;
	}

	
}
