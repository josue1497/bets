/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.StatusJugadaIF;
import com.jade.util.lbda.EjecutorSql;


/**
* Administracion de la tabla status_jugada.
* 
* @author jrivero
* 
* Esta clase permite la actualizacion de la tabla status_jugada
*
* @see EjecutorSql
* @see CachedRowSet 
*
*/

public class StatusJugadaDAO
{
    private static final Log log = LogFactory.getLog(StatusJugadaDAO.class);
    private EjecutorSql oEjecutorSql = new EjecutorSql();
    private StringBuffer strBuffquery = new StringBuffer ();
    public CachedRowSet oCachedRowSet = null; 

    /**  
    * El constructor no tiene parámetros.  
    */
    public StatusJugadaDAO()
    {
        super();
        oEjecutorSql = new EjecutorSql();
    }


    /**
    *
    * Inserta un registro en la tabla status_jugada
    *
    */
    public int insertarStatusJugadaDAO(StatusJugadaIF oStatusJugadaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusJugadaDAO.insertarStatusJugadaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
        try
        {
    
            oParametros.add(oStatusJugadaTO.getIdStatusJugada());
            oParametros.add(oStatusJugadaTO.getDescJugada());
    
            strBuffquery.append("INSERT INTO status_jugada VALUES (?,?)");
    
            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusJugadaDAO.insertarStatusJugadaDAO");
            e.printStackTrace();
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Actualiza un registro en la tabla status_jugada
    *
    */
    public int actualizarStatusJugadaDAO(StatusJugadaIF oStatusJugadaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusJugadaDAO.actualizarStatusJugadaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
    
        try
        {
    
                oParametros.add(oStatusJugadaTO.getDescJugada());
                oParametros.add(oStatusJugadaTO.getIdStatusJugada());   // primary key 
    
                strBuffquery.append("UPDATE status_jugada SET ");
                strBuffquery.append("desc_jugada=?  " );
                strBuffquery.append("WHERE  id_status_jugada = ? ");
    
                numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
    
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusJugadaDAO.actualizarStatusJugadaDAO");
            e.printStackTrace();
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Elimina un registro en la tabla status_jugada
    *
    */
    public int eliminarStatusJugadaDAO(StatusJugadaIF oStatusJugadaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusJugadaDAO.eliminarStatusJugadaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
        try
        {

            oParametros.add(oStatusJugadaTO.getIdStatusJugada());
            strBuffquery.append("DELETE FROM status_jugada  WHERE  (id_status_jugada = ?) ");

            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusJugadaDAO.eliminarStatusJugadaDAO");
            e.printStackTrace();
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Consulta una lista de registros en la tabla status_jugada
    *
    */
    public CachedRowSet listaStatusJugadaDAO() throws Exception
    {
        log.info("Iniciando ejecucion de StatusJugadaDAO.listaStatusJugadaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        try
        {
            
            oCachedRowSet = new CachedRowSet();
            strBuffquery.append("SELECT id_status_jugada, UPPER(desc_jugada) ");
            strBuffquery.append("FROM status_jugada ");
            strBuffquery.append("ORDER BY UPPER(desc_jugada) ");
            
            oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
            
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusJugadaDAO.listaStatusJugadaDAO");
            e.printStackTrace();
            throw e;
        }
        return oCachedRowSet;
    }

    /**
    *
    * Consulta una lista de registros en la tabla status_jugada
    *
    */
    public CachedRowSet listaStatusSoloJugadaDAO() throws Exception
    {
        log.info("Iniciando ejecucion de StatusJugadaDAO.listaStatusSoloJugadaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        try
        {
        	oParametros.add(Constants.STATUS_JUGADA_GANADOR);
        	oParametros.add(Constants.STATUS_JUGADA_PERDEDOR);
            
            oCachedRowSet = new CachedRowSet();
            strBuffquery.append("SELECT id_status_jugada, UPPER(desc_jugada) As desc_jugada ");
            strBuffquery.append("FROM status_jugada ");
            strBuffquery.append("WHERE id_status_jugada NOT IN (?,?) ");
            strBuffquery.append("ORDER BY UPPER(desc_jugada) ");
            
            oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
            
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusJugadaDAO.listaStatusSoloJugadaDAO");
            e.printStackTrace();
            throw e;
        }
        return oCachedRowSet;
    }
    
    /**
    *
    * Consulta un registro en la tabla status_jugada
    *
    */
    public boolean cargarStatusJugadaDAO(StatusJugadaIF oStatusJugadaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusJugadaDAO.consultarStatusJugadaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        boolean retorno = false;
        try
        {
            oCachedRowSet = new CachedRowSet();

            strBuffquery.append("SELECT  id_status_jugada, desc_jugada " );
            strBuffquery.append("FROM status_jugada ");

            boolean procesar = false;
            if (oStatusJugadaTO.getIdStatusJugada()!=null && !oStatusJugadaTO.getIdStatusJugada().equalsIgnoreCase(""))
            {
                oParametros.add(oStatusJugadaTO.getIdStatusJugada());
                strBuffquery.append(" WHERE id_status_jugada = ? ");
                procesar = true;
            } else if (oStatusJugadaTO.getDescJugada()!=null && !oStatusJugadaTO.getDescJugada().equalsIgnoreCase(""))
            {
                strBuffquery.append(" WHERE UPPER(desc_jugada) LIKE '%");
                strBuffquery.append(oStatusJugadaTO.getDescJugada().toUpperCase().trim());
                strBuffquery.append("%'");
                procesar = true;
            }

            if (procesar) {
                oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
                if (oCachedRowSet.next()) {
                    oStatusJugadaTO.setIdStatusJugada(oCachedRowSet.getString("id_status_jugada"));
                    oStatusJugadaTO.setDescJugada(oCachedRowSet.getString("desc_jugada"));
                    retorno = true;
                }
            }
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusJugadaDAO.consultarStatusJugadaDAO");
            e.printStackTrace();
            throw e;
        }
        
        return retorno;
    }


}
