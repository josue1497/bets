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
import com.betcesc.game.interfaces.LogrosIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla lanzador.
 * 
 * @author jrivero Esta clase permite la actualizacion de la tabla lanzador
 * @see EjecutorSql
 * @see CachedRowSet
 */

public class LogrosDAO {
	private static final Log	log				= LogFactory.getLog(LogrosDAO.class);
	private EjecutorSql			oEjecutorSql	= new EjecutorSql();
	private StringBuffer		strBuffquery	= new StringBuffer();
	public CachedRowSet			oCachedRowSet	= null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public LogrosDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * Consulta una lista de registros en la tabla lanzador
	 */
	public int actualizarLogrosDAO(LogrosIF logrosTO) throws Exception
	{
		log.info("Iniciando ejecucion de LogrosDAO.actualizarLogrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int act = 0;
		try {

			oParametros.add(logrosTO.getHembra());
			oParametros.add(logrosTO.getFRL0());
			oParametros.add(logrosTO.getLRL0());
			oParametros.add(logrosTO.getFRL1());
			oParametros.add(logrosTO.getLRL1());
			oParametros.add(logrosTO.getFSRL0());
			oParametros.add(logrosTO.getLSRL0());
			oParametros.add(logrosTO.getFSRL1());
			oParametros.add(logrosTO.getLSRL1());
			oParametros.add(logrosTO.getFAB0());
			oParametros.add(logrosTO.getLAB0());
			oParametros.add(logrosTO.getFAB1());
			oParametros.add(logrosTO.getLAB1());
			oParametros.add(logrosTO.getLAP0());
			oParametros.add(logrosTO.getLAP1());

			oParametros.add(logrosTO.getIdDeporte());
			oParametros.add(logrosTO.getFavorito()); // key

			strBuffquery.setLength(0);
			strBuffquery.append("UPDATE logros SET ");
			strBuffquery.append("hembra=?,FRL0=?,LRL0=?,FRL1=?,LRL1=?,FSRL0=?,LSRL0=?,FSRL1=?,LSRL1=?,FAB0=?,LAB0=?,FAB1=?,LAB1=?,LAP0=?,LAP1=? ");
			strBuffquery.append("WHERE id_deporte=? AND favorito=?");

			act = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			if (act == 0) {
				strBuffquery.setLength(0);
				strBuffquery.append("INSERT INTO logros (hembra,FRL0,LRL0,FRL1,LRL1,FSRL0,LSRL0,FSRL1,LSRL1,FAB0,LAB0,FAB1,LAB1,LAP0,LAP1,id_deporte,favorito) ");
				strBuffquery.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

				act = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			}

		}
		catch (Exception e) {
			log.info("Error en la ejecucion de LogrosDAO.actualizarLogrosDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return act;
	}

	/**
	 * Consulta una lista de registros en la tabla lanzador
	 */
	public CachedRowSet listaLogrosDAO() throws Exception
	{
		log.info("Iniciando ejecucion de LogrosDAO.listaLogrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT favorito, hembra, logros.id_deporte, desc_deporte, ");
			strBuffquery.append("FRL0,LRL0,FRL1,LRL1,FSRL0,LSRL0,FSRL1,LSRL1,FAB0,LAB0,FAB1,LAB1,LAP0,LAP1 ");
			strBuffquery.append("FROM logros, deporte ");
			strBuffquery.append("WHERE deporte.id_deporte=logros.id_deporte ");
			strBuffquery.append("ORDER BY logros.id_deporte,favorito ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		}
		catch (Exception e) {
			log.info("Error en la ejecucion de LogrosDAO.listaLogrosDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * Consulta una lista de registros en la tabla lanzador
	 */
	public String[] getAnotaPrimeroSegunTablaLogrosDAO(String logroMoneyLine) throws Exception
	{
		log.info("Iniciando ejecucion de LogrosDAO.getAnotaPrimeroLogrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		String[] logroAP = null;
		try {

			oParametros.add(logroMoneyLine);
			oParametros.add(Constants.ID_EQUIPO_BEISBOL);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT LAP0,LAP1 ");
			strBuffquery.append("FROM logros ");
			strBuffquery.append("WHERE favorito=? ");
			strBuffquery.append("AND id_deporte=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (oCachedRowSet.next()) {
				logroAP = new String[2];
				logroAP[0] = oCachedRowSet.getString("LAP0");
				logroAP[1] = oCachedRowSet.getString("LAP1");
			}

		}
		catch (Exception e) {
			log.info("Error en la ejecucion de LogrosDAO.getAnotaPrimeroLogrosDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return logroAP;
	}

	/**
	 * Consulta una lista de registros en la tabla lanzador
	 */
	public String[] getAnotaPrimeroLogrosDAO(String logroML, boolean isVisit) throws Exception
	{
		log.info("Iniciando ejecucion de LogrosDAO.getAnotaPrimeroLogrosDAO");
		int logro = Integer.parseInt(logroML);
		String[] logroAP = null;
		try {
			if (isVisit) {
				if (logro <= -115 && logro >= -130) {
					logroAP = new String[] { "-130", "100" };
				}
				else if (logro <= -135 && logro >= -155) {
					logroAP = new String[] { "-140", "110" };
				}
				else if (logro <= -160 && logro >= -180) {
					logroAP = new String[] { "-165", "135" };
				}
				else if (logro <= -185 && logro >= -200) {
					logroAP = new String[] { "-175", "145" };
				}
				else if (logro < -200) {
					logroAP = new String[] { "-185", "155" };
				}
			}
			else {
				if (logro <= -115 && logro >= -130) {
					logroAP = new String[] { "-105", "-125" };
				}
				else if (logro <= -135 && logro >= -155) {
					logroAP = new String[] { "-110", "-120" };
				}
				else if (logro <= -160 && logro >= -180) {
					logroAP = new String[] { "-120", "-110" };
				}
				else if (logro <= -185 && logro >= -200) {
					logroAP = new String[] { "-130", "100" };
				}
				else if (logro < -200) {
					logroAP = new String[] { "-140", "110" };
				}
			}

		}
		catch (Exception e) {
			log.info("Error en la ejecucion de LogrosDAO.getAnotaPrimeroLogrosDAO");
			e.printStackTrace();
			throw e;
		}
		return logroAP;
	}

}
