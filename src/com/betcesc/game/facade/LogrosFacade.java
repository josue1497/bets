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

import com.betcesc.game.dao.LogrosDAO;

/**
 * 
 * @author jrivero
 * 
 */

public class LogrosFacade
{
    private static final Log log = LogFactory.getLog(LanzadorFacade.class);

    HttpServletRequest request = null;

    /**
     * Constructor.
     */
    public LogrosFacade(HttpServletRequest request)
    {
        super();
        this.request = request;
    }
    
    /*
     *...........................LOGROS...........................
     */
    
    /**
     * Construye la lista de los registros de la tabla logros
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public CachedRowSet listaLogrosFacade() throws Exception
    {
        log.info("Iniciando ejecucion de LogrosFacade.listaLogrosFacade");
        CachedRowSet oCachedRowSet = new CachedRowSet();
        LogrosDAO oLogrosDAO = new LogrosDAO();
        try {
            oCachedRowSet = oLogrosDAO.listaLogrosDAO();
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LogrosFacade.listaLogrosFacade");
            log.error("Error:" + e.getMessage());
        }
        return oCachedRowSet;
    }
    
    /**
     * Construye la lista de los registros de la tabla logros
     * 
     * @return ArrayList, array cuerpo del main.
     */
    public String listaLogrosJSFacade() throws Exception
    {
        log.info("Iniciando ejecucion de LogrosFacade.listaLogrosFacade");
        CachedRowSet crs = new CachedRowSet();
        LogrosDAO oLogrosDAO = new LogrosDAO();
        StringBuffer arreglo = new StringBuffer();
        String sep = "";
        String coma = ",";
        try {
            crs = oLogrosDAO.listaLogrosDAO();
        	arreglo.append("[");
            while(crs.next()) {
            	arreglo.append(sep);
            	arreglo.append("{");
            	arreglo.append("idDeporte:'").append(crs.getString("id_deporte")).append("'").append(coma);
            	arreglo.append("favorito:'").append(crs.getString("favorito")).append("'").append(coma);
            	arreglo.append("hembra:'").append(crs.getString("hembra")).append("'").append(coma);
            	arreglo.append("FRL0:'") .append(crs.getString("FRL0")).append("'").append(coma);
            	arreglo.append("LRL0:'") .append(crs.getString("LRL0")).append("'").append(coma);
            	arreglo.append("FRL1:'") .append(crs.getString("FRL1")).append("'").append(coma);
            	arreglo.append("LRL1:'") .append(crs.getString("LRL1")).append("'").append(coma);
            	arreglo.append("FSRL0:'").append(crs.getString("FSRL0")).append("'").append(coma);
            	arreglo.append("LSRL0:'").append(crs.getString("LSRL0")).append("'").append(coma);
            	arreglo.append("FSRL1:'").append(crs.getString("FSRL1")).append("'").append(coma);
            	arreglo.append("LSRL1:'").append(crs.getString("LSRL1")).append("'").append(coma);
            	arreglo.append("FAB0:'") .append(crs.getString("FAB0")).append("'").append(coma);
            	arreglo.append("LAB0:'") .append(crs.getString("LAB0")).append("'").append(coma);
            	arreglo.append("FAB1:'") .append(crs.getString("FAB1")).append("'").append(coma);
            	arreglo.append("LAB1:'") .append(crs.getString("LAB1")).append("'");
            	arreglo.append("}");
            	sep = coma;
            }
            arreglo.append("]");
        }
        catch (Exception e) {
            log.info("Error en la ejecucion de LogrosFacade.listaLogrosFacade");
            log.error("Error:" + e.getMessage());
        }
        return arreglo.toString();
    }
    



}
