/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.JugadaApostadorIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo JugadaApostadorTO serializables.
 */

public class JugadaApostadorTO implements Serializable, JugadaApostadorIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6707542814499927610L;
	private String idJugada = null;
    private String idApostador = null;
    
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
     * @return Returns the idApostador.
     */
    public String getIdApostador()
    {
        return idApostador;
    }

    /**
     * @param idApostador
     *            The idApostador to set.
     */
    public void setIdApostador(String idApostador)
    {
        this.idApostador = idApostador;
    }


}
