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

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.ApuestaJuegoEquipoIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla apuesta_juego_equipo.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla apuesta_juego_equipo
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class ApuestaJuegoEquipoDAO {
	private static final Log log = LogFactory.getLog(ApuestaJuegoEquipoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public ApuestaJuegoEquipoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public ApuestaJuegoEquipoDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
	}

	/**
	 * 
	 * Inserta un registro en la tabla apuesta_juego_equipo
	 * 
	 */
	public int insertarApuestaJuegoEquipoDAO(ApuestaJuegoEquipoIF oApuestaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaJuegoEquipoDAO.insertarApuestaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oApuestaJuegoEquipoTO.setIdApuestaJuegoEquipo(oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo() == null || oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo().trim().equals("") ? "0" : oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo());
			oApuestaJuegoEquipoTO.setIdStatusApuesta(oApuestaJuegoEquipoTO.getIdStatusApuesta() == null || oApuestaJuegoEquipoTO.getIdStatusApuesta().trim().equals("") ? Constants.STATUS_JUGADA_EN_JUEGO : oApuestaJuegoEquipoTO.getIdStatusApuesta());
			

			oParametros.add(oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo());
			oParametros.add(oApuestaJuegoEquipoTO.getIdApuesta());
			oParametros.add(oApuestaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			oParametros.add(oApuestaJuegoEquipoTO.getTipo());
			oParametros.add(oApuestaJuegoEquipoTO.getIdStatusApuesta());

			strBuffquery.append("INSERT INTO apuesta_juego_equipo VALUES (?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaJuegoEquipoDAO.insertarApuestaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla apuesta_juego_equipo
	 * 
	 */
	public int actualizarApuestaJuegoEquipoDAO(ApuestaJuegoEquipoIF oApuestaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaJuegoEquipoDAO.actualizarApuestaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oApuestaJuegoEquipoTO.getIdApuesta());
			oParametros.add(oApuestaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			oParametros.add(oApuestaJuegoEquipoTO.getTipo());
			oParametros.add(oApuestaJuegoEquipoTO.getIdStatusApuesta());
			oParametros.add(oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo()); // primary
			// key

			strBuffquery.append("UPDATE apuesta_juego_equipo SET ");
			strBuffquery.append("id_apuesta=? , id_usuario_juego_equipo=?, tipo=?, id_status_apuesta=? ");
			strBuffquery.append("WHERE  id_apuesta_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaJuegoEquipoDAO.actualizarApuestaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}
	
	
	/**
	 * 
	 * Actualiza un registro en la tabla apuesta_juego_equipo
	 * 
	 */
	public int actualizarApuestaJuegoEquipoStatusDAO(ApuestaJuegoEquipoIF oApuestaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaJuegoEquipoDAO.actualizarApuestaJuegoEquipoStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA); // esto no indica que voy a eliminar la apuesta, ver el query
			oParametros.add(oApuestaJuegoEquipoTO.getIdStatusApuesta());
			oParametros.add(oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo()); // primary
			// key

			strBuffquery.append("UPDATE apuesta_juego_equipo SET id_status_apuesta=if(id_status_apuesta=?,id_status_apuesta,?) ");
			strBuffquery.append("WHERE  id_apuesta_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaJuegoEquipoDAO.actualizarApuestaJuegoEquipoStatusDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}
	

	/**
	 * 
	 * Elimina un registro en la tabla apuesta_juego_equipo
	 * 
	 */
	public int eliminarApuestaJuegoEquipoDAO(ApuestaJuegoEquipoIF oApuestaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaJuegoEquipoDAO.eliminarApuestaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo());
			strBuffquery.append("DELETE FROM apuesta_juego_equipo  WHERE  (id_apuesta_juego_equipo = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaJuegoEquipoDAO.eliminarApuestaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla apuesta_juego_equipo
	 * 
	 */
	public CachedRowSet listaApuestaJuegoEquipoDAO() throws Exception {
		log.info("Iniciando ejecucion de ApuestaJuegoEquipoDAO.listaApuestaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_apuesta_juego_equipo, UPPER(id_apuesta) ");
			strBuffquery.append("FROM apuesta_juego_equipo ");
			strBuffquery.append("ORDER BY UPPER(id_apuesta) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaJuegoEquipoDAO.listaApuestaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla apuesta_juego_equipo
	 * 
	 */
	public boolean cargarApuestaJuegoEquipoDAO(ApuestaJuegoEquipoIF oApuestaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de ApuestaJuegoEquipoDAO.consultarApuestaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_apuesta_juego_equipo, id_apuesta, id_usuario_juego_equipo, ");
			strBuffquery.append("tipo ");
			strBuffquery.append("FROM apuesta_juego_equipo ");

			boolean procesar = false;
			if (!oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo().equalsIgnoreCase("")) {
				oParametros.add(oApuestaJuegoEquipoTO.getIdApuestaJuegoEquipo());
				strBuffquery.append(" WHERE id_apuesta_juego_equipo = ? ");
				procesar = true;
			} else if (!oApuestaJuegoEquipoTO.getIdApuesta().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_apuesta) LIKE '%");
				strBuffquery.append(oApuestaJuegoEquipoTO.getIdApuesta().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oApuestaJuegoEquipoTO.setIdApuestaJuegoEquipo(oCachedRowSet.getString("id_apuesta_juego_equipo"));
					oApuestaJuegoEquipoTO.setIdApuesta(oCachedRowSet.getString("id_apuesta"));
					oApuestaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCachedRowSet.getString("id_usuario_juego_equipo"));
					oApuestaJuegoEquipoTO.setTipo(oCachedRowSet.getString("tipo"));
					oApuestaJuegoEquipoTO.setIdStatusApuesta(oCachedRowSet.getString("id_status_apuesta"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de ApuestaJuegoEquipoDAO.consultarApuestaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
