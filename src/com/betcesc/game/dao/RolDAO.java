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

import com.betcesc.game.interfaces.RolIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla rol.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla rol
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class RolDAO {
	private static final Log log = LogFactory.getLog(RolDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public RolDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla rol
	 * 
	 */
	public int insertarRolDAO(RolIF oRolTO) throws Exception {
		log.info("Iniciando ejecucion de RolDAO.insertarRolDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oRolTO.getIdRol());
			oParametros.add(oRolTO.getDescRol());

			strBuffquery.append("INSERT INTO rol VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de RolDAO.insertarRolDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla rol
	 * 
	 */
	public int actualizarRolDAO(RolIF oRolTO) throws Exception {
		log.info("Iniciando ejecucion de RolDAO.actualizarRolDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oRolTO.getDescRol());
			oParametros.add(oRolTO.getIdRol()); // primary key

			strBuffquery.append("UPDATE rol SET ");
			strBuffquery.append("desc_rol=?  ");
			strBuffquery.append("WHERE  id_rol = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de RolDAO.actualizarRolDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla rol
	 * 
	 */
	public int eliminarRolDAO(RolIF oRolTO) throws Exception {
		log.info("Iniciando ejecucion de RolDAO.eliminarRolDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oRolTO.getIdRol());
			strBuffquery.append("DELETE FROM rol  WHERE  (id_rol = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de RolDAO.eliminarRolDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla rol
	 * 
	 */
	public CachedRowSet listaRolDAO(UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de RolDAO.listaRolDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_rol, UPPER(desc_rol) As desc_rol ");
			strBuffquery.append("FROM rol ");
			strBuffquery.append("ORDER BY UPPER(desc_rol) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de RolDAO.listaRolDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	
	/**
	 * 
	 * Consulta un registro en la tabla rol
	 * 
	 */
	public CachedRowSet consultarRolDAO(RolIF oRolTO) throws Exception {
		log.info("Iniciando ejecucion de RolDAO.consultarRolDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_rol, desc_rol ");
			strBuffquery.append("FROM rol ");

			boolean procesar = false;
			if (!oRolTO.getIdRol().equalsIgnoreCase("")) {
				oParametros.add(oRolTO.getIdRol());
				strBuffquery.append(" WHERE id_rol = ? ");
				procesar = true;
			} else if (!oRolTO.getDescRol().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_rol) LIKE '%");
				strBuffquery.append(oRolTO.getDescRol().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de RolDAO.consultarRolDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla rol
	 * 
	 */
	public boolean cargarRolDAO(RolIF oRolTO) throws Exception {
		log.info("Iniciando ejecucion de RolDAO.consultarRolDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_rol, desc_rol ");
			strBuffquery.append("FROM rol ");

			boolean procesar = false;
			if (!oRolTO.getIdRol().equalsIgnoreCase("")) {
				oParametros.add(oRolTO.getIdRol());
				strBuffquery.append(" WHERE id_rol = ? ");
				procesar = true;
			} else if (!oRolTO.getDescRol().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_rol) LIKE '%");
				strBuffquery.append(oRolTO.getDescRol().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oRolTO.setIdRol(oCachedRowSet.getString("id_rol"));
					oRolTO.setDescRol(oCachedRowSet.getString("desc_rol"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de RolDAO.consultarRolDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
