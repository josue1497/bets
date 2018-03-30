/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.StatusApuestaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo StatusApuestaTO serializables.
 */

public class StatusApuestaTO implements Serializable, StatusApuestaIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5910580190190260244L;
	private String idStatusApuesta = null;
    private String descApuesta = null;
    
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

    
    /**
     * @return Returns the descApuesta.
     */
    public String getDescApuesta()
    {
        return descApuesta;
    }

    /**
     * @param descApuesta
     *            The descApuesta to set.
     */
    public void setDescApuesta(String descApuesta)
    {
        this.descApuesta = descApuesta;
    }


}
