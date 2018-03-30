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

import com.betcesc.game.interfaces.StatusDeporteIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla status_deporte.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla status_deporte
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class StatusDeporteDAO {
	private static final Log log = LogFactory.getLog(StatusDeporteDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public StatusDeporteDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla status_deporte
	 * 
	 */
	public int insertarStatusDeporteDAO(StatusDeporteIF oStatusDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDeporteDAO.insertarStatusDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oStatusDeporteTO.getIdStatusDeporte());
			oParametros.add(oStatusDeporteTO.getDescStatusDeporte());

			strBuffquery.append("INSERT INTO status_deporte VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDeporteDAO.insertarStatusDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla status_deporte
	 * 
	 */
	public int actualizarStatusDeporteDAO(StatusDeporteIF oStatusDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDeporteDAO.actualizarStatusDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oStatusDeporteTO.getDescStatusDeporte());
			oParametros.add(oStatusDeporteTO.getIdStatusDeporte()); // primary
																	// key

			strBuffquery.append("UPDATE status_deporte SET ");
			strBuffquery.append("desc_status_deporte=?  ");
			strBuffquery.append("WHERE  id_status_deporte = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDeporteDAO.actualizarStatusDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla status_deporte
	 * 
	 */
	public int eliminarStatusDeporteDAO(StatusDeporteIF oStatusDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDeporteDAO.eliminarStatusDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oStatusDeporteTO.getIdStatusDeporte());
			strBuffquery.append("DELETE FROM status_deporte  WHERE  (id_status_deporte = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDeporteDAO.eliminarStatusDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla status_deporte
	 * 
	 */
	public CachedRowSet listaStatusDeporteDAO() throws Exception {
		log.info("Iniciando ejecucion de StatusDeporteDAO.listaStatusDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_status_deporte, desc_status_deporte ");
			strBuffquery.append("FROM status_deporte ");
			strBuffquery.append("ORDER BY id_status_deporte ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDeporteDAO.listaStatusDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla status_deporte
	 * 
	 */
	public boolean cargarStatusDeporteDAO(StatusDeporteIF oStatusDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDeporteDAO.consultarStatusDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_status_deporte, desc_status_deporte ");
			strBuffquery.append("FROM status_deporte ");

			boolean procesar = false;
			if (!oStatusDeporteTO.getIdStatusDeporte().equalsIgnoreCase("")) {
				oParametros.add(oStatusDeporteTO.getIdStatusDeporte());
				strBuffquery.append(" WHERE id_status_deporte = ? ");
				procesar = true;
			} else if (!oStatusDeporteTO.getDescStatusDeporte().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_status_deporte) LIKE '%");
				strBuffquery.append(oStatusDeporteTO.getDescStatusDeporte().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oStatusDeporteTO.setIdStatusDeporte(oCachedRowSet.getString("id_status_deporte"));
					oStatusDeporteTO.setDescStatusDeporte(oCachedRowSet.getString("desc_status_deporte"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDeporteDAO.consultarStatusDeporteDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
