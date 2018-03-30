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

import com.betcesc.game.interfaces.EquipoLigaIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla equipo_liga.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla equipo_liga
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class EquipoLigaDAO {
	private static final Log log = LogFactory.getLog(EquipoLigaDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public EquipoLigaDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla equipo_liga
	 * 
	 */
	public int insertarEquipoLigaDAO(EquipoLigaIF oEquipoLigaTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoLigaDAO.insertarEquipoLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oEquipoLigaTO.getIdEquipo());
			oParametros.add(oEquipoLigaTO.getIdLiga());

			strBuffquery.append("INSERT INTO equipo_liga VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			numRegAct = actualizarEquipoLigaDAO(oEquipoLigaTO);
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla equipo_liga
	 * 
	 */
	public int actualizarEquipoLigaDAO(EquipoLigaIF oEquipoLigaTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoLigaDAO.actualizarEquipoLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oEquipoLigaTO.getIdLiga());
			oParametros.add(oEquipoLigaTO.getIdEquipo()); // primary key

			strBuffquery.append("UPDATE equipo_liga SET ");
			strBuffquery.append("id_liga=?  ");
			strBuffquery.append("WHERE  id_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoLigaDAO.actualizarEquipoLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla equipo_liga
	 * 
	 */
	public int eliminarEquipoLigaDAO(EquipoLigaIF oEquipoLigaTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoLigaDAO.eliminarEquipoLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oEquipoLigaTO.getIdEquipo());
			oParametros.add(oEquipoLigaTO.getIdLiga());
			strBuffquery.append("DELETE FROM equipo_liga  WHERE  (id_equipo = ? and id_liga = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoLigaDAO.eliminarEquipoLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla equipo_liga
	 * 
	 */
	public CachedRowSet listaEquipoLigaDAO() throws Exception {
		log.info("Iniciando ejecucion de EquipoLigaDAO.listaEquipoLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_equipo, UPPER(id_liga) ");
			strBuffquery.append("FROM equipo_liga ");
			strBuffquery.append("ORDER BY UPPER(id_liga) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoLigaDAO.listaEquipoLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla equipo_liga
	 * 
	 */
	public boolean cargarEquipoLigaDAO(EquipoLigaIF oEquipoLigaTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoLigaDAO.consultarEquipoLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_equipo, id_liga ");
			strBuffquery.append("FROM equipo_liga ");

			boolean procesar = false;
			if (!oEquipoLigaTO.getIdEquipo().equalsIgnoreCase("")) {
				oParametros.add(oEquipoLigaTO.getIdEquipo());
				strBuffquery.append(" WHERE id_equipo = ? ");
				procesar = true;
			} else if (!oEquipoLigaTO.getIdLiga().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_liga) LIKE '%");
				strBuffquery.append(oEquipoLigaTO.getIdLiga().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oEquipoLigaTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
					oEquipoLigaTO.setIdLiga(oCachedRowSet.getString("id_liga"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoLigaDAO.consultarEquipoLigaDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
