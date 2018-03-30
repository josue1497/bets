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

import com.betcesc.game.interfaces.RolMenuIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla rol_menu.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla rol_menu
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class RolMenuDAO {
	private static final Log log = LogFactory.getLog(RolMenuDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public RolMenuDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla rol_menu
	 * 
	 */
	public int insertarRolMenuDAO(RolMenuIF oRolMenuTO) throws Exception {
		log.info("Iniciando ejecucion de RolMenuDAO.insertarRolMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oRolMenuTO.getIdMenu());
			oParametros.add(oRolMenuTO.getIdRol());

			strBuffquery.append("INSERT INTO rol_menu VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de RolMenuDAO.insertarRolMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla rol_menu
	 * 
	 */
	public int actualizarRolMenuDAO(RolMenuIF oRolMenuTO) throws Exception {
		log.info("Iniciando ejecucion de RolMenuDAO.actualizarRolMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oRolMenuTO.getIdRol());
			oParametros.add(oRolMenuTO.getIdMenu()); // primary key

			strBuffquery.append("UPDATE rol_menu SET ");
			strBuffquery.append("id_rol=?  ");
			strBuffquery.append("WHERE  id_menu = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de RolMenuDAO.actualizarRolMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla rol_menu
	 * 
	 */
	public int eliminarRolMenuDAO(RolMenuIF oRolMenuTO) throws Exception {
		log.info("Iniciando ejecucion de RolMenuDAO.eliminarRolMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oRolMenuTO.getIdMenu());
			strBuffquery.append("DELETE FROM rol_menu  WHERE  (id_menu = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de RolMenuDAO.eliminarRolMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla rol_menu
	 * 
	 */
	public CachedRowSet listaRolMenuDAO() throws Exception {
		log.info("Iniciando ejecucion de RolMenuDAO.listaRolMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_menu, UPPER(id_rol) ");
			strBuffquery.append("FROM rol_menu ");
			strBuffquery.append("ORDER BY UPPER(id_rol) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de RolMenuDAO.listaRolMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla rol_menu
	 * 
	 */
	public CachedRowSet consultarRolMenuDAO(RolMenuIF oRolMenuTO) throws Exception {
		log.info("Iniciando ejecucion de RolMenuDAO.consultarRolMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_menu, id_rol ");
			strBuffquery.append("FROM rol_menu ");

			boolean procesar = false;
			if (!oRolMenuTO.getIdMenu().equalsIgnoreCase("")) {
				oParametros.add(oRolMenuTO.getIdMenu());
				strBuffquery.append(" WHERE id_menu = ? ");
				procesar = true;
			} else if (!oRolMenuTO.getIdRol().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_rol) LIKE '%");
				strBuffquery.append(oRolMenuTO.getIdRol().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de RolMenuDAO.consultarRolMenuDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla rol_menu
	 * 
	 */
	public boolean cargarRolMenuDAO(RolMenuIF oRolMenuTO) throws Exception {
		log.info("Iniciando ejecucion de RolMenuDAO.consultarRolMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_menu, id_rol ");
			strBuffquery.append("FROM rol_menu ");

			boolean procesar = false;
			if (!oRolMenuTO.getIdMenu().equalsIgnoreCase("")) {
				oParametros.add(oRolMenuTO.getIdMenu());
				strBuffquery.append(" WHERE id_menu = ? ");
				procesar = true;
			} else if (!oRolMenuTO.getIdRol().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_rol) LIKE '%");
				strBuffquery.append(oRolMenuTO.getIdRol().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oRolMenuTO.setIdMenu(oCachedRowSet.getString("id_menu"));
					oRolMenuTO.setIdRol(oCachedRowSet.getString("id_rol"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de RolMenuDAO.consultarRolMenuDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
