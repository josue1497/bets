/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.interfaces;


/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo StatusApuestaTO serializables.
 */

public interface StatusApuestaIF 
{

    
    /**
     * @return Returns the idStatusApuesta.
     */
    public String getIdStatusApuesta();

    /**
     * @param idStatusApuesta
     *            The idStatusApuesta to set.
     */
    public void setIdStatusApuesta(String idStatusApuesta);

    
    /**
     * @return Returns the descApuesta.
     */
    public String getDescApuesta();

    /**
     * @param descApuesta
     *            The descApuesta to set.
     */
    public void setDescApuesta(String descApuesta);


}
