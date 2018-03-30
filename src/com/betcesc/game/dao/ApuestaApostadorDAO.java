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

import com.betcesc.game.interfaces.ApuestaApostadorIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla apuesta_apostador.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla apuesta_apostador
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class ApuestaApostadorDAO {
	private static final Log log = LogFactory.getLog(ApuestaApostadorDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public ApuestaApostadorDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla apuesta_apostador
	 * 
	 */
	public int insertarApuestaApostadorDAO(ApuestaApostadorIF oApuestaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaApostadorDAO.insertarApuestaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oApuestaApostadorTO.getIdApuesta());
			oParametros.add(oApuestaApostadorTO.getIdApostador());

			strBuffquery.append("INSERT INTO apuesta_apostador VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaApostadorDAO.insertarApuestaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla apuesta_apostador
	 * 
	 */
	public int actualizarApuestaApostadorDAO(ApuestaApostadorIF oApuestaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaApostadorDAO.actualizarApuestaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oApuestaApostadorTO.getIdApostador());
			oParametros.add(oApuestaApostadorTO.getIdApuesta()); // primary key

			strBuffquery.append("UPDATE apuesta_apostador SET ");
			strBuffquery.append("id_apostador=?  ");
			strBuffquery.append("WHERE  id_apuesta = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaApostadorDAO.actualizarApuestaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla apuesta_apostador
	 * 
	 */
	public int eliminarApuestaApostadorDAO(ApuestaApostadorIF oApuestaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaApostadorDAO.eliminarApuestaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oApuestaApostadorTO.getIdApuesta());
			strBuffquery.append("DELETE FROM apuesta_apostador  WHERE  (id_apuesta = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaApostadorDAO.eliminarApuestaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla apuesta_apostador
	 * 
	 */
	public CachedRowSet listaApuestaApostadorDAO() throws Exception {
		log.info("Iniciando ejecucion de ApuestaApostadorDAO.listaApuestaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_apuesta, UPPER(id_apostador) ");
			strBuffquery.append("FROM apuesta_apostador ");
			strBuffquery.append("ORDER BY UPPER(id_apostador) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaApostadorDAO.listaApuestaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla apuesta_apostador
	 * 
	 */
	public boolean cargarApuestaApostadorDAO(ApuestaApostadorIF oApuestaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaApostadorDAO.consultarApuestaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_apuesta, id_apostador ");
			strBuffquery.append("FROM apuesta_apostador ");

			boolean procesar = false;
			if (!oApuestaApostadorTO.getIdApuesta().equalsIgnoreCase("")) {
				oParametros.add(oApuestaApostadorTO.getIdApuesta());
				strBuffquery.append(" WHERE id_apuesta = ? ");
				procesar = true;
			} else if (!oApuestaApostadorTO.getIdApostador().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_apostador) LIKE '%");
				strBuffquery.append(oApuestaApostadorTO.getIdApostador().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oApuestaApostadorTO.setIdApuesta(oCachedRowSet.getString("id_apuesta"));
					oApuestaApostadorTO.setIdApostador(oCachedRowSet.getString("id_apostador"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaApostadorDAO.consultarApuestaApostadorDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
