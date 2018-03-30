/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.UsuarioJuegoEquipoIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo UsuarioJuegoEquipoTO serializables.
 */

public class UsuarioJuegoEquipoTO implements Serializable, UsuarioJuegoEquipoIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4710501756021190048L;
	private String idUsuarioJuegoEquipo = null;
    private String idUsuario = null;
    private String idJuegoEquipo = null;
    private String fechaSis = null;
    private String spread = null;
    private String spreadLogro = null;
    private String total = null;
    private String totalLogro = null;
    private String moneyLine = null;
    private String idStatusJuego = null;
    private String superSpread = null;
    private String superSpreadLogro = null;
    private String desactivado = null;
    
    /**
     * @return Returns the idUsuarioJuegoEquipo.
     */
    public String getIdUsuarioJuegoEquipo()
    {
        return idUsuarioJuegoEquipo;
    }

    /**
     * @param idUsuarioJuegoEquipo
     *            The idUsuarioJuegoEquipo to set.
     */
    public void setIdUsuarioJuegoEquipo(String idUsuarioJuegoEquipo)
    {
        this.idUsuarioJuegoEquipo = idUsuarioJuegoEquipo;
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
     * @return Returns the spread.
     */
    public String getSpread()
    {
        return spread;
    }

    /**
     * @param spread
     *            The spread to set.
     */
    public void setSpread(String spread)
    {
        this.spread = spread;
    }

    
    /**
     * @return Returns the spreadLogro.
     */
    public String getSpreadLogro()
    {
        return spreadLogro;
    }

    /**
     * @param spreadLogro
     *            The spreadLogro to set.
     */
    public void setSpreadLogro(String spreadLogro)
    {
        this.spreadLogro = spreadLogro;
    }

    
    /**
     * @return Returns the total.
     */
    public String getTotal()
    {
        return total;
    }

    /**
     * @param total
     *            The total to set.
     */
    public void setTotal(String total)
    {
        this.total = total;
    }

    
    /**
     * @return Returns the totalLogro.
     */
    public String getTotalLogro()
    {
        return totalLogro;
    }

    /**
     * @param totalLogro
     *            The totalLogro to set.
     */
    public void setTotalLogro(String totalLogro)
    {
        this.totalLogro = totalLogro;
    }

    
    /**
     * @return Returns the moneyLine.
     */
    public String getMoneyLine()
    {
        return moneyLine;
    }

    /**
     * @param moneyLine
     *            The moneyLine to set.
     */
    public void setMoneyLine(String moneyLine)
    {
        this.moneyLine = moneyLine;
    }

	public String getIdStatusJuego() {
		return idStatusJuego;
	}

	public void setIdStatusJuego(String idStatusJuego) {
		this.idStatusJuego = idStatusJuego;
	}

	public String getSuperSpread() {
		return superSpread;
	}

	public void setSuperSpread(String superSpread) {
		this.superSpread = superSpread;
	}

	public String getSuperSpreadLogro() {
		return superSpreadLogro;
	}

	public void setSuperSpreadLogro(String superSpreadLogro) {
		this.superSpreadLogro = superSpreadLogro;
	}
    
	public String getDesactivado() {
		return desactivado;
	}

	public void setDesactivado(String desactivado) {
		this.desactivado = desactivado;
	}
    

}
