/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.EquipoLigaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo EquipoLigaTO serializables.
 */

public class EquipoLigaTO implements Serializable, EquipoLigaIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5322575962144863468L;
	private String idEquipo = null;
    private String idLiga = null;
    
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
     * @return Returns the idLiga.
     */
    public String getIdLiga()
    {
        return idLiga;
    }

    /**
     * @param idLiga
     *            The idLiga to set.
     */
    public void setIdLiga(String idLiga)
    {
        this.idLiga = idLiga;
    }


}
