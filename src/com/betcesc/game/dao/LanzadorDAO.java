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

import com.betcesc.game.interfaces.LanzadorIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla lanzador.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla lanzador
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class LanzadorDAO {
	private static final Log log = LogFactory.getLog(LanzadorDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public LanzadorDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla lanzador
	 * 
	 */
	public int insertarLanzadorDAO(LanzadorIF oLanzadorTO) throws Exception {
		log.info("Iniciando ejecucion de LanzadorDAO.insertarLanzadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oLanzadorTO.getIdLanzador());
			oParametros.add(oLanzadorTO.getNombreLanzador());
			oParametros.add(oLanzadorTO.getIdEquipo());

			strBuffquery.append("INSERT INTO lanzador VALUES (?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LanzadorDAO.insertarLanzadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla lanzador
	 * 
	 */
	public int actualizarLanzadorDAO(LanzadorIF oLanzadorTO) throws Exception {
		log.info("Iniciando ejecucion de LanzadorDAO.actualizarLanzadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oLanzadorTO.getNombreLanzador());
			oParametros.add(oLanzadorTO.getIdEquipo());
			oParametros.add(oLanzadorTO.getIdLanzador()); // primary key

			strBuffquery.append("UPDATE lanzador SET ");
			strBuffquery.append("nombre_lanzador=? , id_equipo=? ");
			strBuffquery.append("WHERE  id_lanzador = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LanzadorDAO.actualizarLanzadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla lanzador
	 * 
	 */
	public int eliminarLanzadorDAO(LanzadorIF oLanzadorTO) throws Exception {
		log.info("Iniciando ejecucion de LanzadorDAO.eliminarLanzadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oLanzadorTO.getIdLanzador());
			strBuffquery.append("DELETE FROM lanzador  WHERE  (id_lanzador = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LanzadorDAO.eliminarLanzadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla lanzador
	 * 
	 */
	public CachedRowSet listaLanzadorDAO() throws Exception {
		log.info("Iniciando ejecucion de LanzadorDAO.listaLanzadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_lanzador, UPPER(nombre_lanzador) ");
			strBuffquery.append("FROM lanzador ");
			strBuffquery.append("ORDER BY UPPER(nombre_lanzador) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LanzadorDAO.listaLanzadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla lanzador
	 * 
	 */
	public CachedRowSet listaLanzadorDAO(LanzadorIF oLanzadorTO) throws Exception {
		log.info("Iniciando ejecucion de LanzadorDAO.listaLanzadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(oLanzadorTO.getIdEquipo());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_lanzador, nombre_lanzador ");
			strBuffquery.append("FROM lanzador ");
			strBuffquery.append("WHERE id_equipo = ? ");
			strBuffquery.append("ORDER BY UPPER(nombre_lanzador) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LanzadorDAO.listaLanzadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla lanzador
	 * 
	 */
	public boolean cargarLanzadorDAO(LanzadorIF oLanzadorTO) throws Exception {
		log.info("Iniciando ejecucion de LanzadorDAO.consultarLanzadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_lanzador, nombre_lanzador, id_equipo ");
			strBuffquery.append("FROM lanzador ");

			boolean procesar = false;
			if (!oLanzadorTO.getIdLanzador().equalsIgnoreCase("")) {
				oParametros.add(oLanzadorTO.getIdLanzador());
				strBuffquery.append(" WHERE id_lanzador = ? ");
				procesar = true;
			} else if (!oLanzadorTO.getNombreLanzador().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(nombre_lanzador) LIKE '%");
				strBuffquery.append(oLanzadorTO.getNombreLanzador().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oLanzadorTO.setIdLanzador(oCachedRowSet.getString("id_lanzador"));
					oLanzadorTO.setNombreLanzador(oCachedRowSet.getString("nombre_lanzador"));
					oLanzadorTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de LanzadorDAO.consultarLanzadorDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
