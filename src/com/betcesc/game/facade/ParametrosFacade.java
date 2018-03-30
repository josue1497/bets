/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.facade;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.dao.ParametrosDAO;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.to.ParametrosTO;

/**
 * 
 * @author jrivero
 * 
 */

public class ParametrosFacade
{
    private static final Log log = LogFactory.getLog(ParametrosFacade.class);

    HttpServletRequest request = null;

    /**
     * Constructor.
     */
    public ParametrosFacade(HttpServletRequest request)
    {
        super();
        this.request = request;
    }
    
    /*
     *...........................PARAMETROS...........................
     */
    
    /**
     * Insertar registros en la tabla Parametros.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int insertarParametrosFacade(ParametrosIF oParametrosTO) throws Exception
    {
        log.info("Iniciando ejecucion de ParametrosFacade.insertarParametrosFacade");
        int act = 0;
    
        ParametrosDAO oParametrosDAO = new ParametrosDAO();
    
        try {
            act = oParametrosDAO.insertarParametrosDAO(oParametrosTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de ParametrosFacade.insertarParametrosFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Actualizar registros en la tabla Parametros.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int actualizarParametrosFacade(ParametrosIF oParametrosTO) throws Exception
    {
        log.info("Iniciando ejecucion de ParametrosFacade.actualizarParametrosFacade");
        int act = 0;
        ParametrosDAO oParametrosDAO = new ParametrosDAO();
        try {
            act = oParametrosDAO.actualizarParametrosDAO(oParametrosTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de ParametrosFacade.actualizarParametrosFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Eliminar registros en la tabla Parametros.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int eliminarParametrosFacade(ParametrosIF oParametrosTO) throws Exception
    {
        log.info("Iniciando ejecucion de ParametrosFacade.eliminarParametrosFacade");
        int act = 0;
        ParametrosDAO oParametrosDAO = new ParametrosDAO();
        try {
            act = oParametrosDAO.eliminarParametrosDAO(oParametrosTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de ParametrosFacade.eliminarParametrosFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Cargar registros un registro de la tabla Parametros.
     * 
     * @return ParametrosTO.
     */
    public ParametrosTO consultarParametrosFacade(ParametrosIF oParametrosTO) throws Exception
    {
        log.info("Iniciando ejecucion de ParametrosFacade.cargarParametrosFacade");
        int act = 0;

        ParametrosDAO oParametrosDAO = new ParametrosDAO();
        ParametrosTO parametrosTO = new ParametrosTO();
        parametrosTO.setNombre(oParametrosTO.getNombre());
    
        try {
            oParametrosDAO.cargarParametrosDAO(parametrosTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de cargarParametrosFacade.consultarParametrosFacade");
            log.error("Error:" + e.getMessage());
            log.error("Error:" + e.getStackTrace());
        }
        return parametrosTO;
    }
    /**
     * Construye la lista de los registros de la tabla parametros
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet listaParametrosFacade() throws Exception
    {
        log.info("Iniciando ejecucion de ParametrosFacade.listaParametrosFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        ParametrosDAO oParametrosDAO = new ParametrosDAO();
        ParametrosTO oParametrosTO = new ParametrosTO();
        try {
            oCachedRowSet = oParametrosDAO.listaParametrosDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de ParametrosFacade.listaParametrosFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }

    /**
     * Construye la consulta de lista de los registros de la tabla parametros
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet consultarParametrosListaFacade(ParametrosIF oParametrosTO) throws Exception
    {
        log.info("Iniciando ejecucion de ParametrosFacade.consultarParametrosListaFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        ParametrosDAO oParametrosDAO = new ParametrosDAO();
        try {
            oCachedRowSet = oParametrosDAO.listaParametrosDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de ParametrosFacade.consultarParametrosListaFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }


}
