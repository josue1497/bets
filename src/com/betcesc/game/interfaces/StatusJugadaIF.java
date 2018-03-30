/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.interfaces;


/**
 * Transfiere los valores desde el objeto de Tipo Factory.
 * 
 * @author jrivero 
 * 
 * Permite la creacion de objetos de tipo StatusJugadaTO serializables.
 */

public interface StatusJugadaIF 
{

    
    /**
     * @return Returns the idStatusJugada.
     */
    public String getIdStatusJugada();

    /**
     * @param idStatusJugada
     *            The idStatusJugada to set.
     */
    public void setIdStatusJugada(String idStatusJugada);

    
    /**
     * @return Returns the descJugada.
     */
    public String getDescJugada();

    /**
     * @param descJugada
     *            The descJugada to set.
     */
    public void setDescJugada(String descJugada);


}
