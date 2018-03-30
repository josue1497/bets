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
import com.betcesc.game.interfaces.DeporteIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.DeporteUserTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla deporte.
 * 
 * @author jrivero
 * 
 *         Esta clase permite la actualizacion de la tabla deporte
 *
 * @see EjecutorSql
 * @see CachedRowSet
 *
 */

public class DeporteDAO {
	private static final Log log = LogFactory.getLog(DeporteDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene par�metros.
	 */
	public DeporteDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 *
	 * Inserta un registro en la tabla deporte
	 *
	 */
	public int insertarDeporteDAO(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.insertarDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oDeporteTO.getIdDeporte());
			oParametros.add(oDeporteTO.getDescDeporte());
			oParametros.add(oDeporteTO.getEmpate());
			oParametros.add(oDeporteTO.getIdStatusDeporte());
			oParametros.add(oDeporteTO.getReferenciaInicio());
			oParametros.add(oDeporteTO.getRunlineInicio());
			oParametros.add(oDeporteTO.getCombinacion());
			oParametros.add(oDeporteTO.getItems());
			oParametros.add(oDeporteTO.getRunlineLogroInicio0());
			oParametros.add(oDeporteTO.getRunlineLogroInicio1());
			oParametros.add(oDeporteTO.getAltabajaLogroInicio0());
			oParametros.add(oDeporteTO.getAltabajaLogroInicio1());

			strBuffquery.append("INSERT INTO deporte VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.insertarDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Actualiza un registro en la tabla deporte
	 *
	 */
	public int actualizarDeporteDAO(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.actualizarDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oDeporteTO.getDescDeporte());
			oParametros.add(oDeporteTO.getEmpate());
			oParametros.add(oDeporteTO.getIdStatusDeporte());
			oParametros.add(oDeporteTO.getReferenciaInicio());
			oParametros.add(oDeporteTO.getRunlineInicio());
			oParametros.add(oDeporteTO.getCombinacion());
			oParametros.add(oDeporteTO.getItems());
			oParametros.add(oDeporteTO.getRunlineLogroInicio0());
			oParametros.add(oDeporteTO.getRunlineLogroInicio1());
			oParametros.add(oDeporteTO.getAltabajaLogroInicio0());
			oParametros.add(oDeporteTO.getAltabajaLogroInicio1());

			oParametros.add(oDeporteTO.getIdDeporte()); // primary key 

			strBuffquery.append("UPDATE deporte SET ");
			strBuffquery.append("desc_deporte=? , empate=?, id_status_deporte=?, referencia_inicio=?, runline_inicio=?, combinacion=?, items=?, ");
			strBuffquery.append("runline_logro_inicio0=?,runline_logro_inicio1=?,altabaja_logro_inicio0=?,altabaja_logro_inicio1=?");
			strBuffquery.append("WHERE  id_deporte = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.actualizarDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Elimina un registro en la tabla deporte
	 *
	 */
	public int eliminarDeporteDAO(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.eliminarDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oDeporteTO.getIdDeporte());
			strBuffquery.append("DELETE FROM deporte  WHERE  (id_deporte = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.eliminarDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Consulta una lista de registros en la tabla deporte
	 *
	 */
	public CachedRowSet listaDeporteDAO(boolean status) throws Exception {
		return listaDeporteDAO(status, true);
	}

	public CachedRowSet listaDeporteDAO(boolean status, boolean listar5toIning) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.listaDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			oParametros.add(status ? Constants.STATUS_DEPORTE_ACTIVO : Constants.STATUS_DEPORTE_INACTIVO);

			strBuffquery.append("SELECT id_deporte, desc_deporte, if(combinacion IS NULL,'',combinacion) As combinacion, items, empate ");
			strBuffquery.append("FROM deporte ");
			strBuffquery.append("WHERE id_status_deporte=? ");
			if (!listar5toIning) {
				strBuffquery.append(" AND id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_5);
				strBuffquery.append(" AND id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BASKETBALL_MITAD);
				strBuffquery.append(" AND id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_SOCCER_MITAD);
				strBuffquery.append(" AND id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_1);
				strBuffquery.append(" AND id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_0);
			}

			strBuffquery.append(" ORDER BY UPPER(desc_deporte) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.listaDeporteDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		log.info("Finalizado ejecucion de DeporteDAO.listaDeporteDAO");
		return oCachedRowSet;
	}

	public ArrayList listaDeporteConJuegoAbiertoDAO(boolean status, boolean listar5toIning) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.listaDeporteConJuegoAbiertoDAO");
		ArrayList oParametros = new ArrayList();
		ArrayList lista = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = listaDeporteConJuegoAbiertoRowSetDAO(status, listar5toIning);

			DeporteTO deporteTO;
			while (oCachedRowSet.next()) {
				deporteTO = new DeporteTO();
				deporteTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
				deporteTO.setDescDeporte(oCachedRowSet.getString("desc_deporte"));
				deporteTO.setCombinacion(oCachedRowSet.getString("combinacion"));
				deporteTO.setItems(oCachedRowSet.getString("items"));
				deporteTO.setEmpate(oCachedRowSet.getString("empate"));
				deporteTO.setRunlineInicio(oCachedRowSet.getString("runline_inicio"));
				deporteTO.setRunlineLogroInicio0(oCachedRowSet.getString("runline_logro_inicio0"));
				deporteTO.setRunlineLogroInicio1(oCachedRowSet.getString("runline_logro_inicio1"));
				deporteTO.setAltabajaLogroInicio0(oCachedRowSet.getString("altabaja_logro_inicio0"));
				deporteTO.setAltabajaLogroInicio1(oCachedRowSet.getString("altabaja_logro_inicio1"));

				lista.add(deporteTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.listaDeporteConJuegoAbiertoDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		log.info("Finalizado ejecucion de DeporteDAO.listaDeporteConJuegoAbiertoDAO");
		return lista;
	}

	/**
	 *
	 * Consulta un registro en la tabla deporte
	 *
	 */
	public boolean cargarDeporteDAO(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.consultarDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_deporte, desc_deporte, empate, ");
			strBuffquery.append("id_status_deporte, if(referencia_inicio is null,0,referencia_inicio) As referencia_inicio, ");
			strBuffquery.append("if(runline_inicio is null,0,runline_inicio) As runline_inicio, ");
			strBuffquery.append("if(runline_logro_inicio0 is null,0,runline_logro_inicio0) As runline_logro_inicio0, ");
			strBuffquery.append("if(runline_logro_inicio1 is null,0,runline_logro_inicio1) As runline_logro_inicio1, ");
			strBuffquery.append("if(altabaja_logro_inicio0 is null,0,altabaja_logro_inicio0) As altabaja_logro_inicio0, ");
			strBuffquery.append("if(altabaja_logro_inicio1 is null,0,altabaja_logro_inicio1) As altabaja_logro_inicio1, ");
			strBuffquery.append("if(combinacion is null,'',combinacion) As combinacion, ");
			strBuffquery.append("if(items is null,'0',items) As items ");
			strBuffquery.append("FROM deporte ");

			boolean procesar = false;
			if (!oDeporteTO.getIdDeporte().equalsIgnoreCase("")) {
				oParametros.add(oDeporteTO.getIdDeporte());
				strBuffquery.append(" WHERE id_deporte = ? ");
				procesar = true;
			} else if (!oDeporteTO.getDescDeporte().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_deporte) LIKE '%");
				strBuffquery.append(oDeporteTO.getDescDeporte().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oDeporteTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
					oDeporteTO.setDescDeporte(oCachedRowSet.getString("desc_deporte"));
					oDeporteTO.setEmpate(oCachedRowSet.getString("empate"));
					oDeporteTO.setIdStatusDeporte(oCachedRowSet.getString("id_status_deporte"));
					oDeporteTO.setReferenciaInicio(oCachedRowSet.getString("referencia_inicio"));
					oDeporteTO.setRunlineInicio(oCachedRowSet.getString("runline_inicio"));
					oDeporteTO.setCombinacion(oCachedRowSet.getString("combinacion"));
					oDeporteTO.setItems(oCachedRowSet.getString("items"));
					oDeporteTO.setRunlineLogroInicio0(oCachedRowSet.getString("runline_logro_inicio0"));
					oDeporteTO.setRunlineLogroInicio1(oCachedRowSet.getString("runline_logro_inicio1"));
					oDeporteTO.setAltabajaLogroInicio0(oCachedRowSet.getString("altabaja_logro_inicio0"));
					oDeporteTO.setAltabajaLogroInicio1(oCachedRowSet.getString("altabaja_logro_inicio1"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.consultarDeporteDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta las combinaciones por usuario
	 * 
	 */
	public CachedRowSet listDeportePorUsuarioDAO(UsuarioIF usuarioTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.listDeportePorUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		CachedRowSet lista = new CachedRowSet();
		String sep = "";
		try {

			oParametros.add(usuarioTO.getIdUsuario());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_deporte, a.desc_deporte,b.combinacion_user ");
			strBuffquery.append("FROM deporte a ");
			strBuffquery.append("LEFT OUTER JOIN deporte_user b ON (a.id_deporte=b.id_deporte AND b.id_usuario=?) ");

			lista = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.listDeportePorUsuarioDAO");
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	/**
	 * 
	 * Consulta las combinaciones por usuario
	 * 
	 */
	public void registerDeportePorUsuarioDAO(ArrayList lista) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.registerDeportePorUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		StringBuffer insert = new StringBuffer();
		StringBuffer update = new StringBuffer();
		String sep = "";
		try {
			insert.append("INSERT INTO deporte_user (combinacion_user,id_deporte,id_usuario) values(?,?,?)");
			update.append("UPDATE deporte_user SET combinacion_user=? WHERE id_deporte=? AND id_usuario=?");

			DeporteUserTO deporteUserTO = null;
			for (int i = 0; i < lista.size(); i++) {
				deporteUserTO = (DeporteUserTO) lista.get(i);
				oParametros = new ArrayList();
				oParametros.add(deporteUserTO.getCombinacionUser());
				oParametros.add(deporteUserTO.getIdDeporte());
				oParametros.add(deporteUserTO.getIdUsuario());

				int act = oEjecutorSql.ejecutaSqlRetornaNumRegAct(update.toString(), oParametros);
				if (act == 0) {
					oEjecutorSql.ejecutaSqlRetornaNumRegAct(insert.toString(), oParametros);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla equipo
	 * 
	 */
	public String listDeporteEmpateJavaScriptDAO() throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.listDeporteEmpateJavaScriptDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		StringBuffer lista = new StringBuffer();
		String sep = "";
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT  id_deporte, desc_deporte ");
			strBuffquery.append("FROM deporte ");
			strBuffquery.append("WHERE empate=1 AND id_status_deporte=1 ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			lista.append("[");
			while (oCachedRowSet.next()) {
				lista.append(sep);
				lista.append("{idDeporte:'");
				lista.append(oCachedRowSet.getString("id_deporte"));
				lista.append("',descDeporte:'");
				lista.append(oCachedRowSet.getString("desc_deporte"));
				lista.append("'}");
				sep = ",";
			}
			lista.append("]");

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.listDeporteEmpateJavaScriptDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista.toString();
	}

	public CachedRowSet listaDeporteCombinacionDAO(boolean status, boolean listar5toIning, UsuarioIF usuario) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.listaDeporteDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		String sep = "";
		try {

			oCachedRowSet = listaDeporteConJuegoAbiertoRowSetDAO(true, false);

			oParametros = new ArrayList();
			strBuffquery.setLength(0);

			oParametros.add(usuario.getIdUsuario());
			oParametros.add(status ? Constants.STATUS_DEPORTE_ACTIVO : Constants.STATUS_DEPORTE_INACTIVO);

			strBuffquery.append("SELECT a.id_deporte, a.desc_deporte, ");
			strBuffquery.append("if(b.combinacion_user IS NULL or b.combinacion_user='',if(a.combinacion IS NULL,'',a.combinacion),b.combinacion_user) As combinacion, ");
			strBuffquery.append("a.items, a.empate ");
			strBuffquery.append("FROM deporte a LEFT OUTER JOIN deporte_user b ON a.id_deporte=b.id_deporte AND b.id_usuario=? ");
			strBuffquery.append("WHERE a.id_status_deporte=? ");
			if (oCachedRowSet.size() > 0) {
				strBuffquery.append("AND a.id_deporte IN (");
				while (oCachedRowSet.next()) {
					strBuffquery.append(sep).append(oCachedRowSet.getInt("id_deporte"));
					sep = Constants.COMA;
				}
				strBuffquery.append(") ");
			}
			if (!listar5toIning) {
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_5);
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BASKETBALL_MITAD);
				strBuffquery.append(" AND a.id_deporte!=");
				//strBuffquery.append(Constants.ID_EQUIPO_SOCCER_MITAD);
				//strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_1);
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_0);
			}

			strBuffquery.append(" ORDER BY UPPER(a.desc_deporte) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.listaDeporteDAO");
			e.printStackTrace();
			throw e;
		}
		log.info("Finalizado ejecucion de DeporteDAO.listaDeporteDAO");
		return oCachedRowSet;
	}

	public CachedRowSet listaDeporteConJuegoAbiertoRowSetDAO(boolean status, boolean listar5toIning) throws Exception {
		log.info("Iniciando ejecucion de DeporteDAO.listaDeporteConJuegoAbiertoRowSetDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			oParametros.add(Constants.ID_EQUIPO_BEISBOL_5);
			oParametros.add(Constants.ID_EQUIPO_BEISBOL_1);
			oParametros.add(Constants.ID_EQUIPO_BEISBOL_0);
			oParametros.add(Constants.ID_EQUIPO_BASKETBALL_MITAD);
			//oParametros.add(Constants.ID_EQUIPO_SOCCER_MITAD);
			oParametros.add(status ? Constants.STATUS_DEPORTE_ACTIVO : Constants.STATUS_DEPORTE_INACTIVO);

			strBuffquery.append("SELECT a.id_deporte, a.desc_deporte, if(a.combinacion IS NULL,'',a.combinacion) As combinacion, a.items, a.empate, ");
			strBuffquery.append("runline_inicio,runline_logro_inicio0,runline_logro_inicio1,altabaja_logro_inicio0,altabaja_logro_inicio1 ");
			strBuffquery.append("FROM deporte a, juego b ");
			strBuffquery.append("WHERE a.id_deporte = b.id_deporte ");
			//strBuffquery.append("AND a.id_deporte NOT IN (?,?,?,?,?) ");
			strBuffquery.append("AND a.id_deporte NOT IN (?,?,?,?) ");
			strBuffquery.append("AND id_status_deporte=? ");
			strBuffquery.append("AND b.id_status_juego=2 ");
			if (!listar5toIning) {
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_5);
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BASKETBALL_MITAD);
				//strBuffquery.append(" AND a.id_deporte!=");
				//strBuffquery.append(Constants.ID_EQUIPO_SOCCER_MITAD);
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_1);
				strBuffquery.append(" AND a.id_deporte!=");
				strBuffquery.append(Constants.ID_EQUIPO_BEISBOL_0);
			}
			strBuffquery.append(" GROUP BY a.id_deporte, a.desc_deporte, if(a.combinacion IS NULL,'',a.combinacion), a.items, a.empate ");
			strBuffquery.append(" ORDER BY UPPER(a.desc_deporte) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteDAO.listaDeporteConJuegoAbiertoDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		log.info("Finalizado ejecucion de DeporteDAO.listaDeporteConJuegoAbiertoRowSetDAO");
		return oCachedRowSet;
	}
}
