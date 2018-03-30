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

import com.betcesc.game.interfaces.StatusIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla status.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla status
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class StatusDAO {
	private static final Log log = LogFactory.getLog(StatusDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public StatusDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla status
	 * 
	 */
	public int insertarStatusDAO(StatusIF oStatusTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDAO.insertarStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oStatusTO.getIdStatus());
			oParametros.add(oStatusTO.getDescStatus());

			strBuffquery.append("INSERT INTO status VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDAO.insertarStatusDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla status
	 * 
	 */
	public int actualizarStatusDAO(StatusIF oStatusTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDAO.actualizarStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oStatusTO.getDescStatus());
			oParametros.add(oStatusTO.getIdStatus()); // primary key

			strBuffquery.append("UPDATE status SET ");
			strBuffquery.append("desc_status=?  ");
			strBuffquery.append("WHERE  id_status = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDAO.actualizarStatusDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla status
	 * 
	 */
	public int eliminarStatusDAO(StatusIF oStatusTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDAO.eliminarStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oStatusTO.getIdStatus());
			strBuffquery.append("DELETE FROM status  WHERE  (id_status = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDAO.eliminarStatusDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla status
	 * 
	 */
	public CachedRowSet listaStatusDAO() throws Exception {
		log.info("Iniciando ejecucion de StatusDAO.listaStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_status, UPPER(desc_status) As desc_status ");
			strBuffquery.append("FROM status ");
			strBuffquery.append("ORDER BY UPPER(desc_status) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDAO.listaStatusDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla status
	 * 
	 */
	public CachedRowSet consultarStatusDAO(StatusIF oStatusTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDAO.consultarStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_status, desc_status ");
			strBuffquery.append("FROM status ");

			boolean procesar = false;
			if (!oStatusTO.getIdStatus().equalsIgnoreCase("")) {
				oParametros.add(oStatusTO.getIdStatus());
				strBuffquery.append(" WHERE id_status = ? ");
				procesar = true;
			} else if (!oStatusTO.getDescStatus().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_status) LIKE '%");
				strBuffquery.append(oStatusTO.getDescStatus().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDAO.consultarStatusDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla status
	 * 
	 */
	public boolean cargarStatusDAO(StatusIF oStatusTO) throws Exception {
		log.info("Iniciando ejecucion de StatusDAO.consultarStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_status, desc_status ");
			strBuffquery.append("FROM status ");

			boolean procesar = false;
			if (!oStatusTO.getIdStatus().equalsIgnoreCase("")) {
				oParametros.add(oStatusTO.getIdStatus());
				strBuffquery.append(" WHERE id_status = ? ");
				procesar = true;
			} else if (!oStatusTO.getDescStatus().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_status) LIKE '%");
				strBuffquery.append(oStatusTO.getDescStatus().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oStatusTO.setIdStatus(oCachedRowSet.getString("id_status"));
					oStatusTO.setDescStatus(oCachedRowSet.getString("desc_status"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de StatusDAO.consultarStatusDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
