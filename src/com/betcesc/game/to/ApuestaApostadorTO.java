/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.ApuestaApostadorIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo ApuestaApostadorTO serializables.
 */

public class ApuestaApostadorTO implements Serializable, ApuestaApostadorIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6707542814499927610L;
	private String idApuesta = null;
    private String idApostador = null;
    
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
