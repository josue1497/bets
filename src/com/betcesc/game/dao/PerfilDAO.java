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

import com.betcesc.game.interfaces.PerfilIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla perfil.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla perfil
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class PerfilDAO {
	private static final Log log = LogFactory.getLog(PerfilDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public PerfilDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla perfil
	 * 
	 */
	public int insertarPerfilDAO(PerfilIF oPerfilTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilDAO.insertarPerfilDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oPerfilTO.getIdRol());
			oParametros.add(oPerfilTO.getDescPerfil());

			strBuffquery.append("INSERT INTO perfil VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilDAO.insertarPerfilDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla perfil
	 * 
	 */
	public int actualizarPerfilDAO(PerfilIF oPerfilTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilDAO.actualizarPerfilDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oPerfilTO.getDescPerfil());
			oParametros.add(oPerfilTO.getIdRol()); // primary key

			strBuffquery.append("UPDATE perfil SET ");
			strBuffquery.append("desc_perfil=?  ");
			strBuffquery.append("WHERE  id_perfil = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilDAO.actualizarPerfilDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla perfil
	 * 
	 */
	public int eliminarPerfilDAO(PerfilIF oPerfilTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilDAO.eliminarPerfilDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oPerfilTO.getIdRol());
			strBuffquery.append("DELETE FROM perfil  WHERE  (id_perfil = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilDAO.eliminarPerfilDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla perfil
	 * 
	 */
	public CachedRowSet listaPerfilDAO(UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilDAO.listaPerfilDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_perfil, UPPER(desc_perfil) As desc_perfil ");
			strBuffquery.append("FROM perfil ");
			strBuffquery.append("ORDER BY UPPER(desc_perfil) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilDAO.listaPerfilDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	
	/**
	 * 
	 * Consulta un registro en la tabla perfil
	 * 
	 */
	public CachedRowSet consultarPerfilDAO(PerfilIF oPerfilTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilDAO.consultarPerfilDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_perfil, desc_perfil ");
			strBuffquery.append("FROM perfil ");

			boolean procesar = false;
			if (!oPerfilTO.getIdRol().equalsIgnoreCase("")) {
				oParametros.add(oPerfilTO.getIdRol());
				strBuffquery.append(" WHERE id_perfil = ? ");
				procesar = true;
			} else if (!oPerfilTO.getDescPerfil().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_perfil) LIKE '%");
				strBuffquery.append(oPerfilTO.getDescPerfil().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilDAO.consultarPerfilDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla perfil
	 * 
	 */
	public boolean cargarPerfilDAO(PerfilIF oPerfilTO) throws Exception {
		log.info("Iniciando ejecucion de PerfilDAO.consultarPerfilDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_perfil, desc_perfil ");
			strBuffquery.append("FROM perfil ");

			boolean procesar = false;
			if (!oPerfilTO.getIdRol().equalsIgnoreCase("")) {
				oParametros.add(oPerfilTO.getIdRol());
				strBuffquery.append(" WHERE id_perfil = ? ");
				procesar = true;
			} else if (!oPerfilTO.getDescPerfil().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_perfil) LIKE '%");
				strBuffquery.append(oPerfilTO.getDescPerfil().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oPerfilTO.setIdPerfil(oCachedRowSet.getString("id_perfil"));
					oPerfilTO.setDescPerfil(oCachedRowSet.getString("desc_perfil"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de PerfilDAO.consultarPerfilDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
