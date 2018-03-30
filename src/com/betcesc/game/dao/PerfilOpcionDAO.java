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

import com.betcesc.game.interfaces.PerfilOpcionIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla perfil_opcion.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla perfil_opcion
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class PerfilOpcionDAO {
	private static final Log log = LogFactory.getLog(PerfilOpcionDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public PerfilOpcionDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla perfil_opcion
	 * 
	 */
	public int insertarPerfilOpcionDAO(PerfilOpcionIF oPerfilOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilOpcionDAO.insertarPerfilOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oPerfilOpcionTO.getIdOpcion());
			oParametros.add(oPerfilOpcionTO.getIdRol());

			strBuffquery.append("INSERT INTO perfil_opcion VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilOpcionDAO.insertarPerfilOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla perfil_opcion
	 * 
	 */
	public int actualizarPerfilOpcionDAO(PerfilOpcionIF oPerfilOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilOpcionDAO.actualizarPerfilOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oPerfilOpcionTO.getIdRol());
			oParametros.add(oPerfilOpcionTO.getIdOpcion()); // primary key

			strBuffquery.append("UPDATE perfil_opcion SET ");
			strBuffquery.append("id_perfil=?  ");
			strBuffquery.append("WHERE  id_opcion = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilOpcionDAO.actualizarPerfilOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla perfil_opcion
	 * 
	 */
	public int eliminarPerfilOpcionDAO(PerfilOpcionIF oPerfilOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilOpcionDAO.eliminarPerfilOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oPerfilOpcionTO.getIdOpcion());
			strBuffquery.append("DELETE FROM perfil_opcion  WHERE  (id_opcion = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilOpcionDAO.eliminarPerfilOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla perfil_opcion
	 * 
	 */
	public CachedRowSet listaPerfilOpcionDAO() throws Exception {
		log.info("Iniciando ejecucion de PerfilOpcionDAO.listaPerfilOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_opcion, UPPER(id_perfil) ");
			strBuffquery.append("FROM perfil_opcion ");
			strBuffquery.append("ORDER BY UPPER(id_perfil) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilOpcionDAO.listaPerfilOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla perfil_opcion
	 * 
	 */
	public CachedRowSet consultarPerfilOpcionDAO(PerfilOpcionIF oPerfilOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilOpcionDAO.consultarPerfilOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_opcion, id_perfil ");
			strBuffquery.append("FROM perfil_opcion ");

			boolean procesar = false;
			if (!oPerfilOpcionTO.getIdOpcion().equalsIgnoreCase("")) {
				oParametros.add(oPerfilOpcionTO.getIdOpcion());
				strBuffquery.append(" WHERE id_opcion = ? ");
				procesar = true;
			} else if (!oPerfilOpcionTO.getIdRol().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_perfil) LIKE '%");
				strBuffquery.append(oPerfilOpcionTO.getIdRol().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilOpcionDAO.consultarPerfilOpcionDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla perfil_opcion
	 * 
	 */
	public boolean cargarPerfilOpcionDAO(PerfilOpcionIF oPerfilOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilOpcionDAO.consultarPerfilOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_opcion, id_perfil ");
			strBuffquery.append("FROM perfil_opcion ");

			boolean procesar = false;
			if (!oPerfilOpcionTO.getIdOpcion().equalsIgnoreCase("")) {
				oParametros.add(oPerfilOpcionTO.getIdOpcion());
				strBuffquery.append(" WHERE id_opcion = ? ");
				procesar = true;
			} else if (!oPerfilOpcionTO.getIdRol().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_perfil) LIKE '%");
				strBuffquery.append(oPerfilOpcionTO.getIdRol().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oPerfilOpcionTO.setIdOpcion(oCachedRowSet.getString("id_opcion"));
					oPerfilOpcionTO.setIdPerfil(oCachedRowSet.getString("id_perfil"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilOpcionDAO.consultarPerfilOpcionDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
