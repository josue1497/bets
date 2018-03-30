/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.to;

import java.io.Serializable;

import com.betcesc.game.interfaces.ApostadorIF;

/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo ApostadorTO serializables.
 */

public class ApostadorTO implements Serializable, ApostadorIF 
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6936923281167407147L;
	private String idApostador = null;
    private String nombreApostador = null;
    private String telefono = null;
    private String correo = null;
    private String direccion = null;
    
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

    
    /**
     * @return Returns the nombreApostador.
     */
    public String getNombreApostador()
    {
        return nombreApostador;
    }

    /**
     * @param nombreApostador
     *            The nombreApostador to set.
     */
    public void setNombreApostador(String nombreApostador)
    {
        this.nombreApostador = nombreApostador;
    }

    
    /**
     * @return Returns the telefono.
     */
    public String getTelefono()
    {
        return telefono;
    }

    /**
     * @param telefono
     *            The telefono to set.
     */
    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    
    /**
     * @return Returns the correo.
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * @param correo
     *            The correo to set.
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    
    /**
     * @return Returns the direccion.
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * @param direccion
     *            The direccion to set.
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }


}
