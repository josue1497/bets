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

import com.betcesc.game.interfaces.JuegoEquipoLanzadorIF;
import com.jade.util.lbda.EjecutorSql;


/**
* Administracion de la tabla juego_equipo_lanzador.
* 
* @author jrivero
* 
* Esta clase permite la actualizacion de la tabla juego_equipo_lanzador
*
* @see EjecutorSql
* @see CachedRowSet 
*
*/

public class JuegoEquipoLanzadorDAO
{
    private static final Log log = LogFactory.getLog(JuegoEquipoLanzadorDAO.class);
    private EjecutorSql oEjecutorSql = new EjecutorSql();
    private StringBuffer strBuffquery = new StringBuffer ();
    public CachedRowSet oCachedRowSet = null; 

    /**  
    * El constructor no tiene parámetros.  
    */
    public JuegoEquipoLanzadorDAO()
    {
        super();
        oEjecutorSql = new EjecutorSql();
    }
	public JuegoEquipoLanzadorDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
		
	}


    /**
    *
    * Inserta un registro en la tabla juego_equipo_lanzador
    *
    */
    public int insertarJuegoEquipoLanzadorDAO(JuegoEquipoLanzadorIF oJuegoEquipoLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de JuegoEquipoLanzadorDAO.insertarJuegoEquipoLanzadorDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
        try
        {
        	oJuegoEquipoLanzadorTO.setIdJuegoEquipo(oJuegoEquipoLanzadorTO.getIdJuegoEquipo()==null?"0":oJuegoEquipoLanzadorTO.getIdJuegoEquipo());

        	oParametros.add(oJuegoEquipoLanzadorTO.getIdJuegoEquipo());


        	strBuffquery.setLength(0);
            strBuffquery.append("DELETE FROM juego_equipo_lanzador WHERE id_juego_equipo=?");
            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
        	

            
            oParametros.add(oJuegoEquipoLanzadorTO.getIdLanzador());
            
            strBuffquery.setLength(0);
            strBuffquery.append("INSERT INTO juego_equipo_lanzador VALUES (?,?)");
    
            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
            
            oJuegoEquipoLanzadorTO.setIdJuegoEquipo(String.valueOf(oEjecutorSql.getGeneratedKey()));
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de JuegoEquipoLanzadorDAO.insertarJuegoEquipoLanzadorDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Actualiza un registro en la tabla juego_equipo_lanzador
    *
    */
    public int actualizarJuegoEquipoLanzadorDAO(JuegoEquipoLanzadorIF oJuegoEquipoLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de JuegoEquipoLanzadorDAO.actualizarJuegoEquipoLanzadorDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
    
        try
        {
    
                oParametros.add(oJuegoEquipoLanzadorTO.getIdLanzador());
                oParametros.add(oJuegoEquipoLanzadorTO.getIdJuegoEquipo());   // primary key 
    
                strBuffquery.append("UPDATE juego_equipo_lanzador SET ");
                strBuffquery.append("id_lanzador=?  " );
                strBuffquery.append("WHERE  id_juego_equipo = ? ");
    
                numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
    
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de JuegoEquipoLanzadorDAO.actualizarJuegoEquipoLanzadorDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Elimina un registro en la tabla juego_equipo_lanzador
    *
    */
    public int eliminarJuegoEquipoLanzadorDAO(JuegoEquipoLanzadorIF oJuegoEquipoLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de JuegoEquipoLanzadorDAO.eliminarJuegoEquipoLanzadorDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        int numRegAct = 0;
        try
        {

            oParametros.add(oJuegoEquipoLanzadorTO.getIdJuegoEquipo());
            strBuffquery.append("DELETE FROM juego_equipo_lanzador  WHERE  (id_juego_equipo = ?) ");

            numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de JuegoEquipoLanzadorDAO.eliminarJuegoEquipoLanzadorDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return numRegAct;
    }
    
    /**
    *
    * Consulta una lista de registros en la tabla juego_equipo_lanzador
    *
    */
    public CachedRowSet listaJuegoEquipoLanzadorDAO() throws Exception
    {
        log.info("Iniciando ejecucion de JuegoEquipoLanzadorDAO.listaJuegoEquipoLanzadorDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        try
        {
            
            oCachedRowSet = new CachedRowSet();
            strBuffquery.append("SELECT id_juego_equipo, UPPER(id_lanzador) ");
            strBuffquery.append("FROM juego_equipo_lanzador ");
            strBuffquery.append("ORDER BY UPPER(id_lanzador) ");
            
            oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
            
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de JuegoEquipoLanzadorDAO.listaJuegoEquipoLanzadorDAO");
            log.error("Error:" + e.getMessage());
            throw e;
        }
        return oCachedRowSet;
    }
    
    /**
    *
    * Consulta un registro en la tabla juego_equipo_lanzador
    *
    */
    public boolean cargarJuegoEquipoLanzadorDAO(JuegoEquipoLanzadorIF oJuegoEquipoLanzadorTO) throws Exception
    {
        log.info("Iniciando ejecucion de JuegoEquipoLanzadorDAO.consultarJuegoEquipoLanzadorDAO");
        ArrayList oParametros = new ArrayList();
        strBuffquery.setLength(0);
        boolean retorno = false;
        try
        {
            oCachedRowSet = new CachedRowSet();

            strBuffquery.append("SELECT  id_juego_equipo, id_lanzador " );
            strBuffquery.append("FROM juego_equipo_lanzador ");

            boolean procesar = false;
            if (!oJuegoEquipoLanzadorTO.getIdJuegoEquipo().equalsIgnoreCase(""))
            {
                oParametros.add(oJuegoEquipoLanzadorTO.getIdJuegoEquipo());
                strBuffquery.append(" WHERE id_juego_equipo = ? ");
                procesar = true;
            } else if (!oJuegoEquipoLanzadorTO.getIdLanzador().equalsIgnoreCase(""))
            {
                strBuffquery.append(" WHERE UPPER(id_lanzador) LIKE '%");
                strBuffquery.append(oJuegoEquipoLanzadorTO.getIdLanzador().toUpperCase().trim());
                strBuffquery.append("%'");
                procesar = true;
            }

            if (procesar) {
                oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
                if (oCachedRowSet.next()) {
                    oJuegoEquipoLanzadorTO.setIdJuegoEquipo(oCachedRowSet.getString("id_juego_equipo"));
                    oJuegoEquipoLanzadorTO.setIdLanzador(oCachedRowSet.getString("id_lanzador"));
                    retorno = true;
                }
            }
        }
        catch (Exception e)
        {
            log.info("Error en la ejecucion de JuegoEquipoLanzadorDAO.consultarJuegoEquipoLanzadorDAO");
            log.error("Error:" + e.getMessage());
            log.error(e.getStackTrace());
            throw e;
        }
        
        return retorno;
    }


}
