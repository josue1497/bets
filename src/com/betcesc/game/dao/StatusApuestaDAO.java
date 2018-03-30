/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.StatusApuestaIF;
import com.jade.util.lbda.EjecutorSql;


/**
* Administracion de la tabla status_apuesta.
* 
* @author jrivero
* 
* Esta clase permite la actualizacion de la tabla status_apuesta
*
* @see EjecutorSql
* @see CachedRowSet 
*
*/

public class StatusApuestaDAO
{
    private static final Log log = LogFactory.getLog(StatusApuestaDAO.class);
    private EjecutorSql oEjecutorSql = new EjecutorSql();
    private StringBuffer strBuffquery = new StringBuffer ();
    public CachedRowSet oCachedRowSet = null; 

    /**  
    * El constructor no tiene parámetros.  
    */
    public StatusApuestaDAO()
    {
        super();
        oEjecutorSql = new EjecutorSql();
    }


    /**
    *
    * Inserta un registro en la tabla status_apuesta
    *
    */
    public int insertarStatusApuestaDAO(StatusApuestaIF oStatusApuestaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusApuestaDAO.insertarStatusApuestaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
        try
        {
    
            oParametros.add(oStatusApuestaTO.getIdStatusApuesta());
            oParametros.add(oStatusApuestaTO.getDescApuesta());
    
            strBuffquery.append("INSERT INTO status_apuesta VALUES (?,?)");
    
            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusApuestaDAO.insertarStatusApuestaDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Actualiza un registro en la tabla status_apuesta
    *
    */
    public int actualizarStatusApuestaDAO(StatusApuestaIF oStatusApuestaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusApuestaDAO.actualizarStatusApuestaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
    
        try
        {
    
                oParametros.add(oStatusApuestaTO.getDescApuesta());
                oParametros.add(oStatusApuestaTO.getIdStatusApuesta());   // primary key 
    
                strBuffquery.append("UPDATE status_apuesta SET ");
                strBuffquery.append("desc_apuesta=?  " );
                strBuffquery.append("WHERE  id_status_apuesta = ? ");
    
                numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
    
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusApuestaDAO.actualizarStatusApuestaDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Elimina un registro en la tabla status_apuesta
    *
    */
    public int eliminarStatusApuestaDAO(StatusApuestaIF oStatusApuestaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusApuestaDAO.eliminarStatusApuestaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
        try
        {

            oParametros.add(oStatusApuestaTO.getIdStatusApuesta());
            strBuffquery.append("DELETE FROM status_apuesta  WHERE  (id_status_apuesta = ?) ");

            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusApuestaDAO.eliminarStatusApuestaDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Consulta una lista de registros en la tabla status_apuesta
    *
    */
    public CachedRowSet listaStatusApuestaDAO() throws Exception
    {
        log.info("Iniciando ejecucion de StatusApuestaDAO.listaStatusApuestaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        try
        {
            
            oCachedRowSet = new CachedRowSet();
            strBuffquery.append("SELECT id_status_apuesta, UPPER(desc_apuesta) ");
            strBuffquery.append("FROM status_apuesta ");
            strBuffquery.append("ORDER BY UPPER(desc_apuesta) ");
            
            oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
            
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusApuestaDAO.listaStatusApuestaDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return oCachedRowSet;
    }

    /**
    *
    * Consulta una lista de registros en la tabla status_apuesta
    *
    */
    public CachedRowSet listaStatusSoloApuestaDAO() throws Exception
    {
        log.info("Iniciando ejecucion de StatusApuestaDAO.listaStatusSoloApuestaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        try
        {
        	oParametros.add(Constants.STATUS_JUGADA_GANADOR);
        	oParametros.add(Constants.STATUS_JUGADA_PERDEDOR);
            
            oCachedRowSet = new CachedRowSet();
            strBuffquery.append("SELECT id_status_apuesta, UPPER(desc_apuesta) As desc_apuesta ");
            strBuffquery.append("FROM status_apuesta ");
            strBuffquery.append("WHERE id_status_apuesta NOT IN (?,?) ");
            strBuffquery.append("ORDER BY UPPER(desc_apuesta) ");
            
            oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
            
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusApuestaDAO.listaStatusSoloApuestaDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return oCachedRowSet;
    }
    
    /**
    *
    * Consulta un registro en la tabla status_apuesta
    *
    */
    public boolean cargarStatusApuestaDAO(StatusApuestaIF oStatusApuestaTO) throws Exception
    {
        log.info("Iniciando ejecucion de StatusApuestaDAO.consultarStatusApuestaDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        boolean retorno = false;
        try
        {
            oCachedRowSet = new CachedRowSet();

            strBuffquery.append("SELECT  id_status_apuesta, desc_apuesta " );
            strBuffquery.append("FROM status_apuesta ");

            boolean procesar = false;
            if (!oStatusApuestaTO.getIdStatusApuesta().equalsIgnoreCase(""))
            {
                oParametros.add(oStatusApuestaTO.getIdStatusApuesta());
                strBuffquery.append(" WHERE id_status_apuesta = ? ");
                procesar = true;
            } else if (oStatusApuestaTO.getDescApuesta()!=null && !oStatusApuestaTO.getDescApuesta().equalsIgnoreCase(""))
            {
                strBuffquery.append(" WHERE UPPER(desc_apuesta) LIKE '%");
                strBuffquery.append(oStatusApuestaTO.getDescApuesta().toUpperCase().trim());
                strBuffquery.append("%'");
                procesar = true;
            }

            if (procesar) {
                oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
                if (oCachedRowSet.next()) {
                    oStatusApuestaTO.setIdStatusApuesta(oCachedRowSet.getString("id_status_apuesta"));
                    oStatusApuestaTO.setDescApuesta(oCachedRowSet.getString("desc_apuesta"));
                    retorno = true;
                }
            }
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de StatusApuestaDAO.consultarStatusApuestaDAO");
            log.error("Error:" + e.getMessage());
            log.error(e.getStackTrace());
            throw e;
        }
        
        return retorno;
    }


}
