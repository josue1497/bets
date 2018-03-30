/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.StatusJugadaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo StatusJugadaTO serializables.
 */

public class StatusJugadaTO implements Serializable, StatusJugadaIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5910580190190260244L;
	private String idStatusJugada = null;
    private String descJugada = null;
    
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

    
    /**
     * @return Returns the descJugada.
     */
    public String getDescJugada()
    {
        return descJugada;
    }

    /**
     * @param descJugada
     *            The descJugada to set.
     */
    public void setDescJugada(String descJugada)
    {
        this.descJugada = descJugada;
    }


}
