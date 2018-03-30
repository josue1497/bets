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
import com.betcesc.game.interfaces.StatusJuegoIF;
import com.jade.util.CacheControl;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla status_juego.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla status_juego
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class StatusJuegoDAO {
	private static final Log log = LogFactory.getLog(StatusJuegoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;
	CacheControl cacheControl = new CacheControl();

	/**
	 * El constructor no tiene parámetros.
	 */
	public StatusJuegoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla status_juego
	 * 
	 */
	public int insertarStatusJuegoDAO(StatusJuegoIF oStatusJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de StatusJuegoDAO.insertarStatusJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oStatusJuegoTO.getIdStatusJuego());
			oParametros.add(oStatusJuegoTO.getDescStatusJuego());

			strBuffquery.append("INSERT INTO status_juego VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusJuegoDAO.insertarStatusJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla status_juego
	 * 
	 */
	public int actualizarStatusJuegoDAO(StatusJuegoIF oStatusJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de StatusJuegoDAO.actualizarStatusJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oStatusJuegoTO.getDescStatusJuego());
			oParametros.add(oStatusJuegoTO.getIdStatusJuego()); // primary key

			strBuffquery.append("UPDATE status_juego SET ");
			strBuffquery.append("desc_status_juego=?  ");
			strBuffquery.append("WHERE  id_status_juego = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusJuegoDAO.actualizarStatusJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla status_juego
	 * 
	 */
	public int eliminarStatusJuegoDAO(StatusJuegoIF oStatusJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de StatusJuegoDAO.eliminarStatusJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oStatusJuegoTO.getIdStatusJuego());
			strBuffquery.append("DELETE FROM status_juego  WHERE  (id_status_juego = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusJuegoDAO.eliminarStatusJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla status_juego
	 * 
	 */
	public CachedRowSet listaStatusJuegoDAO() throws Exception {
		log.info("Iniciando ejecucion de StatusJuegoDAO.listaStatusJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);

		if (cacheControl.existInCache("listaStatusJuegoDAO"))
		    {
			CachedRowSet rs = (CachedRowSet) cacheControl.getObject("listaStatusJuegoDAO");
			rs.beforeFirst();
			
			return rs;
		    }

		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_status_juego, desc_status_juego ");
			strBuffquery.append("FROM status_juego ");
			strBuffquery.append("ORDER BY id_status_juego ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			cacheControl.saveObject("listaStatusJuegoDAO", oCachedRowSet);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusJuegoDAO.listaStatusJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	/**
	 * 
	 * Consulta una lista de registros en la tabla status_juego
	 * 
	 */
	public CachedRowSet listaStatusTaquillaJuegoDAO() throws Exception {
		log.info("Iniciando ejecucion de StatusJuegoDAO.listaStatusTaquillaJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(Constants.STATUS_JUEGO_BORRADOR);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_status_juego, desc_status_juego ");
			strBuffquery.append("FROM status_juego ");
			strBuffquery.append("WHERE id_status_juego!=? ");
			strBuffquery.append("ORDER BY id_status_juego ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusJuegoDAO.listaStatusTaquillaJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla status_juego
	 * 
	 */
	public boolean cargarStatusJuegoDAO(StatusJuegoIF oStatusJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de StatusJuegoDAO.consultarStatusJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_status_juego, desc_status_juego ");
			strBuffquery.append("FROM status_juego ");

			boolean procesar = false;
			if (!oStatusJuegoTO.getIdStatusJuego().equalsIgnoreCase("")) {
				oParametros.add(oStatusJuegoTO.getIdStatusJuego());
				strBuffquery.append(" WHERE id_status_juego = ? ");
				procesar = true;
			} else if (!oStatusJuegoTO.getDescStatusJuego().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_status_juego) LIKE '%");
				strBuffquery.append(oStatusJuegoTO.getDescStatusJuego().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oStatusJuegoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
					oStatusJuegoTO.setDescStatusJuego(oCachedRowSet.getString("desc_status_juego"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusJuegoDAO.consultarStatusJuegoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
