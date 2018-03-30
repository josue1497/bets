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
 * Permite la creacion de objetos de tipo LanzadorTO serializables.
 */

public interface LanzadorIF 
{

    
    /**
     * @return Returns the idLanzador.
     */
    public String getIdLanzador();

    /**
     * @param idLanzador
     *            The idLanzador to set.
     */
    public void setIdLanzador(String idLanzador);

    
    /**
     * @return Returns the nombreLanzador.
     */
    public String getNombreLanzador();

    /**
     * @param nombreLanzador
     *            The nombreLanzador to set.
     */
    public void setNombreLanzador(String nombreLanzador);

    
    /**
     * @return Returns the idEquipo.
     */
    public String getIdEquipo();

    /**
     * @param idEquipo
     *            The idEquipo to set.
     */
    public void setIdEquipo(String idEquipo);


}
