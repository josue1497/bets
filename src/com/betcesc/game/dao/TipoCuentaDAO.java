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

import com.betcesc.game.interfaces.TipoCuentaIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla tipo_cuenta.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla tipo_cuenta
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class TipoCuentaDAO {
	private static final Log log = LogFactory.getLog(TipoCuentaDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public TipoCuentaDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla tipo_cuenta
	 * 
	 */
	public int insertarTipoCuentaDAO(TipoCuentaIF oTipoCuentaTO) throws Exception {
		log.info("Iniciando ejecucion de TipoCuentaDAO.insertarTipoCuentaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oTipoCuentaTO.getIdTipoCuenta());
			oParametros.add(oTipoCuentaTO.getDescTipoCuenta());

			strBuffquery.append("INSERT INTO tipo_cuenta VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de TipoCuentaDAO.insertarTipoCuentaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla tipo_cuenta
	 * 
	 */
	public int actualizarTipoCuentaDAO(TipoCuentaIF oTipoCuentaTO) throws Exception {
		log.info("Iniciando ejecucion de TipoCuentaDAO.actualizarTipoCuentaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oTipoCuentaTO.getDescTipoCuenta());
			oParametros.add(oTipoCuentaTO.getIdTipoCuenta()); // primary key

			strBuffquery.append("UPDATE tipo_cuenta SET ");
			strBuffquery.append("desc_tipo_cuenta=?  ");
			strBuffquery.append("WHERE  id_tipo_cuenta = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de TipoCuentaDAO.actualizarTipoCuentaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla tipo_cuenta
	 * 
	 */
	public int eliminarTipoCuentaDAO(TipoCuentaIF oTipoCuentaTO) throws Exception {
		log.info("Iniciando ejecucion de TipoCuentaDAO.eliminarTipoCuentaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oTipoCuentaTO.getIdTipoCuenta());
			strBuffquery.append("DELETE FROM tipo_cuenta  WHERE  (id_tipo_cuenta = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de TipoCuentaDAO.eliminarTipoCuentaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla tipo_cuenta
	 * 
	 */
	public CachedRowSet listaTipoCuentaDAO() throws Exception {
		log.info("Iniciando ejecucion de TipoCuentaDAO.listaTipoCuentaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_tipo_cuenta, UPPER(desc_tipo_cuenta) As desc_tipo_cuenta ");
			strBuffquery.append("FROM tipo_cuenta ");
			strBuffquery.append("ORDER BY UPPER(desc_tipo_cuenta) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de TipoCuentaDAO.listaTipoCuentaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla tipo_cuenta
	 * 
	 */
	public CachedRowSet consultarTipoCuentaDAO(TipoCuentaIF oTipoCuentaTO) throws Exception {
		log.info("Iniciando ejecucion de TipoCuentaDAO.consultarTipoCuentaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_tipo_cuenta, desc_tipo_cuenta ");
			strBuffquery.append("FROM tipo_cuenta ");

			boolean procesar = false;
			if (!oTipoCuentaTO.getIdTipoCuenta().equalsIgnoreCase("")) {
				oParametros.add(oTipoCuentaTO.getIdTipoCuenta());
				strBuffquery.append(" WHERE id_tipo_cuenta = ? ");
				procesar = true;
			} else if (!oTipoCuentaTO.getDescTipoCuenta().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_tipo_cuenta) LIKE '%");
				strBuffquery.append(oTipoCuentaTO.getDescTipoCuenta().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de TipoCuentaDAO.consultarTipoCuentaDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla tipo_cuenta
	 * 
	 */
	public boolean cargarTipoCuentaDAO(TipoCuentaIF oTipoCuentaTO) throws Exception {
		log.info("Iniciando ejecucion de TipoCuentaDAO.consultarTipoCuentaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_tipo_cuenta, desc_tipo_cuenta ");
			strBuffquery.append("FROM tipo_cuenta ");

			boolean procesar = false;
			if (!oTipoCuentaTO.getIdTipoCuenta().equalsIgnoreCase("")) {
				oParametros.add(oTipoCuentaTO.getIdTipoCuenta());
				strBuffquery.append(" WHERE id_tipo_cuenta = ? ");
				procesar = true;
			} else if (!oTipoCuentaTO.getDescTipoCuenta().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_tipo_cuenta) LIKE '%");
				strBuffquery.append(oTipoCuentaTO.getDescTipoCuenta().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oTipoCuentaTO.setIdTipoCuenta(oCachedRowSet.getString("id_tipo_cuenta"));
					oTipoCuentaTO.setDescTipoCuenta(oCachedRowSet.getString("desc_tipo_cuenta"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de TipoCuentaDAO.consultarTipoCuentaDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
