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

import com.betcesc.game.interfaces.CampeonatoIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla campeonato.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla campeonato
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class CampeonatoDAO {
	private static final Log log = LogFactory.getLog(CampeonatoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public CampeonatoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla campeonato
	 * 
	 */
	public int insertarCampeonatoDAO(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoDAO.insertarCampeonatoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oCampeonatoTO.getIdCampeonato());
			oParametros.add(oCampeonatoTO.getDescCampeonato());

			strBuffquery.append("INSERT INTO campeonato VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			
			oCampeonatoTO.setIdCampeonato(String.valueOf(oEjecutorSql.getGeneratedKey()));
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoDAO.insertarCampeonatoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla campeonato
	 * 
	 */
	public int actualizarCampeonatoDAO(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoDAO.actualizarCampeonatoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oCampeonatoTO.getDescCampeonato());
			oParametros.add(oCampeonatoTO.getIdCampeonato()); // primary key

			strBuffquery.append("UPDATE campeonato SET ");
			strBuffquery.append("desc_campeonato=?  ");
			strBuffquery.append("WHERE  id_campeonato = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoDAO.actualizarCampeonatoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla campeonato
	 * 
	 */
	public int eliminarCampeonatoDAO(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoDAO.eliminarCampeonatoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oCampeonatoTO.getIdCampeonato());
			strBuffquery.append("DELETE FROM campeonato  WHERE  (id_campeonato = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoDAO.eliminarCampeonatoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla campeonato
	 * 
	 */
	public CachedRowSet listaCampeonatoDAO() throws Exception {
		log.info("Iniciando ejecucion de CampeonatoDAO.listaCampeonatoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_campeonato, UPPER(desc_campeonato) ");
			strBuffquery.append("FROM campeonato ");
			strBuffquery.append("ORDER BY UPPER(desc_campeonato) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoDAO.listaCampeonatoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla campeonato
	 * 
	 */
	public boolean cargarCampeonatoDAO(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoDAO.consultarCampeonatoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_campeonato, desc_campeonato ");
			strBuffquery.append("FROM campeonato ");

			boolean procesar = false;
			if (!oCampeonatoTO.getIdCampeonato().equalsIgnoreCase("")) {
				oParametros.add(oCampeonatoTO.getIdCampeonato());
				strBuffquery.append(" WHERE id_campeonato = ? ");
				procesar = true;
			} else if (!oCampeonatoTO.getDescCampeonato().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_campeonato) LIKE '%");
				strBuffquery.append(oCampeonatoTO.getDescCampeonato().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oCampeonatoTO.setIdCampeonato(oCachedRowSet.getString("id_campeonato"));
					oCampeonatoTO.setDescCampeonato(oCachedRowSet.getString("desc_campeonato"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoDAO.consultarCampeonatoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
