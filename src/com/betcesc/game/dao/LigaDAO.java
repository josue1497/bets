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
import com.betcesc.game.interfaces.LigaIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla liga.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla liga
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class LigaDAO {
	private static final Log log = LogFactory.getLog(LigaDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public LigaDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla liga
	 * 
	 */
	public int insertarLigaDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.insertarLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oLigaTO.setIdLiga(Constants.isNull(oLigaTO.getIdLiga(),"0"));

			oParametros.add(oLigaTO.getIdLiga());
			oParametros.add(oLigaTO.getDescLiga());
			oParametros.add(oLigaTO.getIniciales());
			if(oLigaTO.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_5) || oLigaTO.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_1)  || oLigaTO.getIdDeporte().equals(Constants.ID_EQUIPO_BEISBOL_0)){
				oParametros.add(Constants.ID_EQUIPO_BEISBOL);
			} else if(oLigaTO.getIdDeporte().equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
				oParametros.add(Constants.ID_EQUIPO_BASKETBALL);
			} else if(oLigaTO.getIdDeporte().equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
				oParametros.add(Constants.ID_EQUIPO_SOCCER);
			} else {
				oParametros.add(oLigaTO.getIdDeporte());
			}

			strBuffquery.append("INSERT INTO liga VALUES (?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.insertarLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla liga
	 * 
	 */
	public int actualizarLigaDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.actualizarLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oLigaTO.getDescLiga());
			oParametros.add(oLigaTO.getIniciales());
			oParametros.add(oLigaTO.getIdDeporte());
			oParametros.add(oLigaTO.getIdLiga()); // primary key

			strBuffquery.append("UPDATE liga SET ");
			strBuffquery.append("desc_liga=? , iniciales=?, id_deporte=? ");
			strBuffquery.append("WHERE  id_liga = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.actualizarLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla liga
	 * 
	 */
	public int eliminarLigaDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.eliminarLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oLigaTO.getIdLiga());
			strBuffquery.append("DELETE FROM liga  WHERE  (id_liga = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.eliminarLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla liga
	 * 
	 */
	public CachedRowSet listaLigaDAO() throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.listaLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_liga, UPPER(desc_liga) ");
			strBuffquery.append("FROM liga ");
			strBuffquery.append("ORDER BY UPPER(desc_liga) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.listaLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla liga
	 * 
	 */
	public CachedRowSet listaLigaPorDeporteDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.listaLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			String idDeporte = oLigaTO.getIdDeporte();
			
			if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_0)) {
				idDeporte = Constants.ID_EQUIPO_BEISBOL;
			} else if(idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
				idDeporte = Constants.ID_EQUIPO_BASKETBALL;
			} else if(idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
				idDeporte = Constants.ID_EQUIPO_SOCCER;
			}

			oParametros.add(idDeporte);
			
			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_liga, desc_liga ");
			strBuffquery.append("FROM liga ");
			strBuffquery.append("WHERE id_deporte = ? ");
			strBuffquery.append("ORDER BY UPPER(desc_liga) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.listaLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla liga
	 * 
	 */
	public CachedRowSet listaLigaActivaPorDeporteDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.listaLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			String idDeporte = oLigaTO.getIdDeporte();
			
			if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_0)) {
				idDeporte = Constants.ID_EQUIPO_BEISBOL;
			} else if(idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD)) {
				idDeporte = Constants.ID_EQUIPO_BASKETBALL;
			} else if(idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
				idDeporte = Constants.ID_EQUIPO_SOCCER;
			}

			oParametros.add(idDeporte);
			
			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_liga, b.desc_liga ");
			strBuffquery.append("FROM juego a, liga b ");
			strBuffquery.append("WHERE a.id_liga=b.id_liga ");
			strBuffquery.append("AND a.id_deporte=? ");
			strBuffquery.append("AND a.fecha_ini > now() ");
			strBuffquery.append("GROUP BY a.id_liga ");
			
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.listaLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla liga
	 * 
	 */
	public CachedRowSet listaEquipoPorLigaDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.listaEquipoPorLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oParametros.add(oLigaTO.getIdLiga());
			
			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_equipo, a.desc_equipo  ");
			strBuffquery.append("FROM equipo a, equipo_liga b ");
			strBuffquery.append("WHERE a.id_equipo=b.id_equipo ");
			strBuffquery.append("AND b.id_liga = ? ");
			strBuffquery.append("ORDER BY upper(a.desc_equipo) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.listaEquipoPorLigaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	
	/**
	 * 
	 * Consulta un registro en la tabla liga
	 * 
	 */
	public boolean cargarLigaDAO(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaDAO.consultarLigaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_liga, desc_liga, iniciales, ");
			strBuffquery.append("id_deporte ");
			strBuffquery.append("FROM liga ");

			boolean procesar = false;
			if (!oLigaTO.getIdLiga().equalsIgnoreCase("")) {
				oParametros.add(oLigaTO.getIdLiga());
				strBuffquery.append(" WHERE id_liga = ? ");
				procesar = true;
			} else if (!oLigaTO.getDescLiga().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_liga) LIKE '%");
				strBuffquery.append(oLigaTO.getDescLiga().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oLigaTO.setIdLiga(oCachedRowSet.getString("id_liga"));
					oLigaTO.setDescLiga(oCachedRowSet.getString("desc_liga"));
					oLigaTO.setIniciales(oCachedRowSet.getString("iniciales"));
					oLigaTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaDAO.consultarLigaDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
