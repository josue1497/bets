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

import com.betcesc.game.dao.EquipoDAO;
import com.betcesc.game.dao.EquipoLigaDAO;
import com.betcesc.game.interfaces.EquipoIF;
import com.betcesc.game.interfaces.EquipoLigaIF;
import com.betcesc.game.to.EquipoTO;

/**
 * 
 * @author jrivero
 * 
 */

public class EquipoFacade
{
    private static final Log log = LogFactory.getLog(EquipoFacade.class);

    HttpServletRequest request = null;

    /**
     * Constructor.
     */
    public EquipoFacade(HttpServletRequest request)
    {
        super();
        this.request = request;
    }
    
    /*
     *...........................EQUIPO...........................
     */
    
    /**
     * Insertar registros en la tabla Equipo.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int insertarEquipoFacade(EquipoIF oEquipoTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.insertarEquipoFacade");
        int act = 0;
    
        EquipoDAO oEquipoDAO = new EquipoDAO();
    
        try {
            act = oEquipoDAO.insertarEquipoDAO(oEquipoTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.insertarEquipoFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }
    public int insertarEquipoLigaFacade(EquipoLigaIF oEquipoLigaTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.insertarEquipoLigaFacade");
        int act = 0;
    
        EquipoLigaDAO oEquipoLigaDAO = new EquipoLigaDAO();
    
        try {
            act = oEquipoLigaDAO.insertarEquipoLigaDAO(oEquipoLigaTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.insertarEquipoLigaFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }
    public int eliminarEquipoLigaFacade(EquipoLigaIF oEquipoLigaTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.eliminarEquipoLigaFacade");
        int act = 0;
    
        EquipoLigaDAO oEquipoLigaDAO = new EquipoLigaDAO();
    
        try {
            act = oEquipoLigaDAO.eliminarEquipoLigaDAO(oEquipoLigaTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.eliminarEquipoLigaFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Actualizar registros en la tabla Equipo.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int actualizarEquipoFacade(EquipoIF oEquipoTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.actualizarEquipoFacade");
        int act = 0;
        EquipoDAO oEquipoDAO = new EquipoDAO();
        try {
            act = oEquipoDAO.actualizarEquipoDAO(oEquipoTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.actualizarEquipoFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Eliminar registros en la tabla Equipo.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int eliminarEquipoFacade(EquipoIF oEquipoTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.eliminarEquipoFacade");
        int act = 0;
        EquipoDAO oEquipoDAO = new EquipoDAO();
        try {
            act = oEquipoDAO.eliminarEquipoDAO(oEquipoTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.eliminarEquipoFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Cargar registros un registro de la tabla Equipo.
     * 
     * @return EquipoTO.
     */
    public EquipoTO consultarEquipoFacade(EquipoIF oEquipoTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.cargarEquipoFacade");
        int act = 0;

        EquipoDAO oEquipoDAO = new EquipoDAO();
        EquipoTO equipoTO = new EquipoTO();
        equipoTO.setIdEquipo(oEquipoTO.getIdEquipo());
    
        try {
            oEquipoDAO.cargarEquipoDAO(equipoTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de cargarEquipoFacade.consultarEquipoFacade");
            log.error("Error:" + e.getMessage());
            log.error("Error:" + e.getStackTrace());
        }
        return equipoTO;
    }
    /**
     * Construye la lista de los registros de la tabla equipo
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet listaEquipoFacade() throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.listaEquipoFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        EquipoDAO oEquipoDAO = new EquipoDAO();
        EquipoTO oEquipoTO = new EquipoTO();
        try {
            oCachedRowSet = oEquipoDAO.listaEquipoDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.listaEquipoFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }

    /**
     * Construye la consulta de lista de los registros de la tabla equipo
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet consultarEquipoListaFacade(EquipoIF oEquipoTO) throws Exception
    {
        log.info("Iniciando ejecucion de EquipoFacade.consultarEquipoListaFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        EquipoDAO oEquipoDAO = new EquipoDAO();
        try {
            oCachedRowSet = oEquipoDAO.listaEquipoDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de EquipoFacade.consultarEquipoListaFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }


}
