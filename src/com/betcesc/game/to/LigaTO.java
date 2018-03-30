/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.LigaIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo LigaTO serializables.
 */

public class LigaTO implements Serializable, LigaIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1332707000841567353L;
	private String idLiga = null;
    private String descLiga = null;
    private String iniciales = null;
    private String idDeporte = null;
    
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

    
    /**
     * @return Returns the descLiga.
     */
    public String getDescLiga()
    {
        return descLiga;
    }

    /**
     * @param descLiga
     *            The descLiga to set.
     */
    public void setDescLiga(String descLiga)
    {
        this.descLiga = descLiga;
    }

    
    /**
     * @return Returns the iniciales.
     */
    public String getIniciales()
    {
        return iniciales;
    }

    /**
     * @param iniciales
     *            The iniciales to set.
     */
    public void setIniciales(String iniciales)
    {
        this.iniciales = iniciales;
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


}
