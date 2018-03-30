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

import com.betcesc.game.interfaces.JugadaApostadorIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla jugada_apostador.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla jugada_apostador
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class JugadaApostadorDAO {
	private static final Log log = LogFactory.getLog(JugadaApostadorDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public JugadaApostadorDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla jugada_apostador
	 * 
	 */
	public int insertarJugadaApostadorDAO(JugadaApostadorIF oJugadaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaApostadorDAO.insertarJugadaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oJugadaApostadorTO.getIdJugada());
			oParametros.add(oJugadaApostadorTO.getIdApostador());

			strBuffquery.append("INSERT INTO jugada_apostador VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaApostadorDAO.insertarJugadaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla jugada_apostador
	 * 
	 */
	public int actualizarJugadaApostadorDAO(JugadaApostadorIF oJugadaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaApostadorDAO.actualizarJugadaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oJugadaApostadorTO.getIdApostador());
			oParametros.add(oJugadaApostadorTO.getIdJugada()); // primary key

			strBuffquery.append("UPDATE jugada_apostador SET ");
			strBuffquery.append("id_apostador=?  ");
			strBuffquery.append("WHERE  id_jugada = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaApostadorDAO.actualizarJugadaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla jugada_apostador
	 * 
	 */
	public int eliminarJugadaApostadorDAO(JugadaApostadorIF oJugadaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaApostadorDAO.eliminarJugadaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oJugadaApostadorTO.getIdJugada());
			strBuffquery.append("DELETE FROM jugada_apostador  WHERE  (id_jugada = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaApostadorDAO.eliminarJugadaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada_apostador
	 * 
	 */
	public CachedRowSet listaJugadaApostadorDAO() throws Exception {
		log.info("Iniciando ejecucion de JugadaApostadorDAO.listaJugadaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_jugada, UPPER(id_apostador) ");
			strBuffquery.append("FROM jugada_apostador ");
			strBuffquery.append("ORDER BY UPPER(id_apostador) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaApostadorDAO.listaJugadaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla jugada_apostador
	 * 
	 */
	public boolean cargarJugadaApostadorDAO(JugadaApostadorIF oJugadaApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaApostadorDAO.consultarJugadaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_jugada, id_apostador ");
			strBuffquery.append("FROM jugada_apostador ");

			boolean procesar = false;
			if (!oJugadaApostadorTO.getIdJugada().equalsIgnoreCase("")) {
				oParametros.add(oJugadaApostadorTO.getIdJugada());
				strBuffquery.append(" WHERE id_jugada = ? ");
				procesar = true;
			} else if (!oJugadaApostadorTO.getIdApostador().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_apostador) LIKE '%");
				strBuffquery.append(oJugadaApostadorTO.getIdApostador().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oJugadaApostadorTO.setIdJugada(oCachedRowSet.getString("id_jugada"));
					oJugadaApostadorTO.setIdApostador(oCachedRowSet.getString("id_apostador"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaApostadorDAO.consultarJugadaApostadorDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
