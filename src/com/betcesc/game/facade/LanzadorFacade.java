/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.facade;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.dao.LanzadorDAO;
import com.betcesc.game.interfaces.LanzadorIF;
import com.betcesc.game.to.LanzadorTO;

/**
 * 
 * @author jrivero
 * 
 */

public class LanzadorFacade
{
    private static final Log log = LogFactory.getLog(LanzadorFacade.class);

    HttpServletRequest request = null;

    /**
     * Constructor.
     */
    public LanzadorFacade(HttpServletRequest request)
    {
        super();
        this.request = request;
    }
    
    /*
     *...........................LANZADOR...........................
     */
    
    /**
     * Insertar registros en la tabla Lanzador.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int insertarLanzadorFacade(LanzadorIF oLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.insertarLanzadorFacade");
        int act = 0;
    
        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
    
        try {
            act = oLanzadorDAO.insertarLanzadorDAO(oLanzadorTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LanzadorFacade.insertarLanzadorFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Actualizar registros en la tabla Lanzador.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int actualizarLanzadorFacade(LanzadorIF oLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.actualizarLanzadorFacade");
        int act = 0;
        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
        try {
            act = oLanzadorDAO.actualizarLanzadorDAO(oLanzadorTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LanzadorFacade.actualizarLanzadorFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Eliminar registros en la tabla Lanzador.
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public int eliminarLanzadorFacade(LanzadorIF oLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.eliminarLanzadorFacade");
        int act = 0;
        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
        try {
            act = oLanzadorDAO.eliminarLanzadorDAO(oLanzadorTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LanzadorFacade.eliminarLanzadorFacade");
            log.error("Error:" + e.getMessage());
        }
        return (act);
    }

    /**
     * Cargar registros un registro de la tabla Lanzador.
     * 
     * @return LanzadorTO.
     */
    public LanzadorTO consultarLanzadorFacade(LanzadorIF oLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.cargarLanzadorFacade");
        int act = 0;

        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
        LanzadorTO lanzadorTO = new LanzadorTO();
        lanzadorTO.setIdLanzador(oLanzadorTO.getIdLanzador());
    
        try {
            oLanzadorDAO.cargarLanzadorDAO(lanzadorTO);
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de cargarLanzadorFacade.consultarLanzadorFacade");
            log.error("Error:" + e.getMessage());
            log.error("Error:" + e.getStackTrace());
        }
        return lanzadorTO;
    }
    /**
     * Construye la lista de los registros de la tabla lanzador
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet listaLanzadorFacade() throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.listaLanzadorFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
        LanzadorTO oLanzadorTO = new LanzadorTO();
        try {
            oCachedRowSet = oLanzadorDAO.listaLanzadorDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LanzadorFacade.listaLanzadorFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }

    /**
     * Construye la lista de los registros de la tabla lanzador
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public ArrayList listaLanzadorFacade(LanzadorIF oLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.listaLanzadorFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
        LanzadorTO lanzadorTO = new LanzadorTO();
        ArrayList lista = new ArrayList();
        try {
            oCachedRowSet = oLanzadorDAO.listaLanzadorDAO(oLanzadorTO);
            while(oCachedRowSet.next()) {
            	lanzadorTO = new LanzadorTO();
            	lanzadorTO.setIdLanzador(oCachedRowSet.getString("id_lanzador"));
            	lanzadorTO.setNombreLanzador(oCachedRowSet.getString("nombre_lanzador"));
            	lista.add(lanzadorTO);
            }
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LanzadorFacade.listaLanzadorFacade");
            log.error("Error:" + e.getMessage());
        }
        return lista;
    }

    /**
     * Construye la consulta de lista de los registros de la tabla lanzador
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet consultarLanzadorListaFacade(LanzadorIF oLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de LanzadorFacade.consultarLanzadorListaFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        LanzadorDAO oLanzadorDAO = new LanzadorDAO();
        try {
            oCachedRowSet = oLanzadorDAO.listaLanzadorDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LanzadorFacade.consultarLanzadorListaFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }


}
