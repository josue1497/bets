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

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.JugadaJuegoEquipoIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla jugada_juego_equipo.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla jugada_juego_equipo
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class JugadaJuegoEquipoDAO {
	private static final Log log = LogFactory.getLog(JugadaJuegoEquipoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public JugadaJuegoEquipoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public JugadaJuegoEquipoDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
	}

	/**
	 * 
	 * Inserta un registro en la tabla jugada_juego_equipo
	 * 
	 */
	public int insertarJugadaJuegoEquipoDAO(JugadaJuegoEquipoIF oJugadaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaJuegoEquipoDAO.insertarJugadaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oJugadaJuegoEquipoTO.setIdJugadaJuegoEquipo(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo() == null || oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo().trim().equals("") ? "0" : oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo());
			oJugadaJuegoEquipoTO.setIdStatusJugada(oJugadaJuegoEquipoTO.getIdStatusJugada() == null || oJugadaJuegoEquipoTO.getIdStatusJugada().trim().equals("") ? Constants.STATUS_JUGADA_EN_JUEGO : oJugadaJuegoEquipoTO.getIdStatusJugada());
			

			oParametros.add(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo());
			oParametros.add(oJugadaJuegoEquipoTO.getIdJugada());
			oParametros.add(oJugadaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			oParametros.add(oJugadaJuegoEquipoTO.getTipo());
			oParametros.add(oJugadaJuegoEquipoTO.getIdStatusJugada());

			strBuffquery.append("INSERT INTO jugada_juego_equipo VALUES (?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaJuegoEquipoDAO.insertarJugadaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla jugada_juego_equipo
	 * 
	 */
	public int actualizarJugadaJuegoEquipoDAO(JugadaJuegoEquipoIF oJugadaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaJuegoEquipoDAO.actualizarJugadaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oJugadaJuegoEquipoTO.getIdJugada());
			oParametros.add(oJugadaJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			oParametros.add(oJugadaJuegoEquipoTO.getTipo());
			oParametros.add(oJugadaJuegoEquipoTO.getIdStatusJugada());
			oParametros.add(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo()); // primary
			// key

			strBuffquery.append("UPDATE jugada_juego_equipo SET ");
			strBuffquery.append("id_jugada=? , id_usuario_juego_equipo=?, tipo=?, id_status_jugada=? ");
			strBuffquery.append("WHERE  id_jugada_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaJuegoEquipoDAO.actualizarJugadaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}
	
	
	/**
	 * 
	 * Actualiza un registro en la tabla jugada_juego_equipo
	 * 
	 */
	public int actualizarJugadaJuegoEquipoStatusDAO(JugadaJuegoEquipoIF oJugadaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaJuegoEquipoDAO.actualizarJugadaJuegoEquipoStatusDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA); // esto no indica que voy a eliminar la jugada, ver el query
			oParametros.add(oJugadaJuegoEquipoTO.getIdStatusJugada());
			oParametros.add(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo()); // primary
			// key

			strBuffquery.append("UPDATE jugada_juego_equipo SET id_status_jugada=if(id_status_jugada=?,id_status_jugada,?) ");
			strBuffquery.append("WHERE  id_jugada_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaJuegoEquipoDAO.actualizarJugadaJuegoEquipoStatusDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}
	

	/**
	 * 
	 * Elimina un registro en la tabla jugada_juego_equipo
	 * 
	 */
	public int eliminarJugadaJuegoEquipoDAO(JugadaJuegoEquipoIF oJugadaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaJuegoEquipoDAO.eliminarJugadaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo());
			strBuffquery.append("DELETE FROM jugada_juego_equipo  WHERE  (id_jugada_juego_equipo = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaJuegoEquipoDAO.eliminarJugadaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla jugada_juego_equipo
	 * 
	 */
	public CachedRowSet listaJugadaJuegoEquipoDAO() throws Exception {
		log.info("Iniciando ejecucion de JugadaJuegoEquipoDAO.listaJugadaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_jugada_juego_equipo, UPPER(id_jugada) ");
			strBuffquery.append("FROM jugada_juego_equipo ");
			strBuffquery.append("ORDER BY UPPER(id_jugada) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaJuegoEquipoDAO.listaJugadaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla jugada_juego_equipo
	 * 
	 */
	public boolean cargarJugadaJuegoEquipoDAO(JugadaJuegoEquipoIF oJugadaJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JugadaJuegoEquipoDAO.consultarJugadaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_jugada_juego_equipo, id_jugada, id_usuario_juego_equipo, ");
			strBuffquery.append("tipo ");
			strBuffquery.append("FROM jugada_juego_equipo ");

			boolean procesar = false;
			if (!oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo().equalsIgnoreCase("")) {
				oParametros.add(oJugadaJuegoEquipoTO.getIdJugadaJuegoEquipo());
				strBuffquery.append(" WHERE id_jugada_juego_equipo = ? ");
				procesar = true;
			} else if (!oJugadaJuegoEquipoTO.getIdJugada().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_jugada) LIKE '%");
				strBuffquery.append(oJugadaJuegoEquipoTO.getIdJugada().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oJugadaJuegoEquipoTO.setIdJugadaJuegoEquipo(oCachedRowSet.getString("id_jugada_juego_equipo"));
					oJugadaJuegoEquipoTO.setIdJugada(oCachedRowSet.getString("id_jugada"));
					oJugadaJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCachedRowSet.getString("id_usuario_juego_equipo"));
					oJugadaJuegoEquipoTO.setTipo(oCachedRowSet.getString("tipo"));
					oJugadaJuegoEquipoTO.setIdStatusJugada(oCachedRowSet.getString("id_status_jugada"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JugadaJuegoEquipoDAO.consultarJugadaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
