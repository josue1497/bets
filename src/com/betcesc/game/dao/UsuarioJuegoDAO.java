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
import com.betcesc.game.interfaces.UsuarioJuegoIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla usuario_juego.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla usuario_juego
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class UsuarioJuegoDAO {
	private static final Log log = LogFactory.getLog(UsuarioJuegoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public UsuarioJuegoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}
	public UsuarioJuegoDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
	}

	/**
	 * 
	 * Inserta un registro en la tabla usuario_juego
	 * 
	 */
	public int insertarUsuarioJuegoDAO(UsuarioJuegoIF oUsuarioJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoDAO.insertarUsuarioJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			// iniciar parametros
			oUsuarioJuegoTO.setFechaSis(Constants.getFechaLargaSQL());

			oParametros.add(oUsuarioJuegoTO.getIdUsuario());
			oParametros.add(oUsuarioJuegoTO.getIdJuego());
			oParametros.add(oUsuarioJuegoTO.getFechaSis());
			oParametros.add(oUsuarioJuegoTO.getIdStatusJuego());

			strBuffquery.append("REPLACE INTO usuario_juego VALUES (?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoDAO.insertarUsuarioJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla usuario_juego
	 * 
	 */
	public int actualizarUsuarioJuegoDAO(UsuarioJuegoIF oUsuarioJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoDAO.actualizarUsuarioJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oUsuarioJuegoTO.setFechaSis(Constants.getFechaLargaSQL());

			oParametros.add(oUsuarioJuegoTO.getFechaSis());
			oParametros.add(oUsuarioJuegoTO.getIdStatusJuego());
			oParametros.add(oUsuarioJuegoTO.getIdUsuario());
			oParametros.add(oUsuarioJuegoTO.getIdJuego());

			strBuffquery.append("UPDATE usuario_juego SET ");
			strBuffquery.append("fecha_sis=?, id_status_juego=?  ");
			strBuffquery.append("WHERE  id_usuario = ? AND id_juego = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoDAO.actualizarUsuarioJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla usuario_juego
	 * 
	 */
	public int eliminarUsuarioJuegoDAO(UsuarioJuegoIF oUsuarioJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoDAO.eliminarUsuarioJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oUsuarioJuegoTO.getIdUsuario());
			oParametros.add(oUsuarioJuegoTO.getIdJuego());
			strBuffquery.append("DELETE FROM usuario_juego ");
			strBuffquery.append("WHERE  id_usuario = ? AND id_juego = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoDAO.eliminarUsuarioJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}
	
	/**
	 * 
	 * Elimina un registro en la tabla usuario_juego
	 * 
	 */
	public int eliminarUsuarioJuegoCompletoDAO(UsuarioJuegoIF oUsuarioJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoDAO.eliminarUsuarioJuegoCompletoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			strBuffquery.append("DELETE FROM usuario_juego ");
			if(oUsuarioJuegoTO.getIdJuego().indexOf("_")!=-1){
				strBuffquery.append("WHERE  id_juego IN (");
				strBuffquery.append(oUsuarioJuegoTO.getIdJuego().replaceAll("_",","));
				strBuffquery.append(") ");
			} else {
				oParametros.add(oUsuarioJuegoTO.getIdJuego()); // primary key
				strBuffquery.append("WHERE  id_juego = ? ");
			}
			
			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoDAO.eliminarUsuarioJuegoCompletoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}
	

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego
	 * 
	 */
	public CachedRowSet listaUsuarioJuegoDAO() throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoDAO.listaUsuarioJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_usuario, id_juego ");
			strBuffquery.append("FROM usuario_juego ");
			strBuffquery.append("ORDER BY UPPER(id_usuario) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoDAO.listaUsuarioJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla usuario_juego
	 * 
	 */
	public boolean cargarUsuarioJuegoDAO(UsuarioJuegoIF oUsuarioJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoDAO.consultarUsuarioJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT id_usuario, id_juego, ");
			strBuffquery.append("fecha_sis, id_status_juego ");
			strBuffquery.append("FROM usuario_juego ");

			boolean procesar = false;
			if (!oUsuarioJuegoTO.getIdUsuario().equalsIgnoreCase("") && !oUsuarioJuegoTO.getIdJuego().equalsIgnoreCase("")) {
				oParametros.add(oUsuarioJuegoTO.getIdUsuario());
				oParametros.add(oUsuarioJuegoTO.getIdJuego());
				strBuffquery.append("WHERE  id_usuario = ? AND id_juego = ? ");
				procesar = true;
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oUsuarioJuegoTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
					oUsuarioJuegoTO.setIdJuego(oCachedRowSet.getString("id_juego"));
					oUsuarioJuegoTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
					oUsuarioJuegoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoDAO.consultarUsuarioJuegoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
