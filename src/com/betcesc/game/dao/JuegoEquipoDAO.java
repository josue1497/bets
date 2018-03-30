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
import com.betcesc.game.interfaces.JuegoEquipoIF;
import com.betcesc.game.interfaces.JuegoIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla juego_equipo.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla juego_equipo
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class JuegoEquipoDAO {
	private static final Log log = LogFactory.getLog(JuegoEquipoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public JuegoEquipoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public JuegoEquipoDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;

	}

	/**
	 * 
	 * Inserta un registro en la tabla juego_equipo
	 * 
	 */
	public int insertarJuegoEquipoDAO(JuegoEquipoIF oJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.insertarJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oJuegoEquipoTO.setIdJuegoEquipo(oJuegoEquipoTO.getIdJuegoEquipo() == null ? "0" : oJuegoEquipoTO.getIdJuegoEquipo());
			oJuegoEquipoTO.setGanador(oJuegoEquipoTO.getGanador() == null ? "0" : oJuegoEquipoTO.getGanador());
			oJuegoEquipoTO.setReferenciaRunline(oJuegoEquipoTO.getReferenciaRunline() == null ? "0" : oJuegoEquipoTO.getReferenciaRunline());
			oJuegoEquipoTO.setPuntos(oJuegoEquipoTO.getPuntos() == null ? "0" : oJuegoEquipoTO.getPuntos());
			oJuegoEquipoTO.setReferenciaSuperRunline(oJuegoEquipoTO.getReferenciaSuperRunline() == null ? "0" : oJuegoEquipoTO.getReferenciaSuperRunline());

			oParametros.add(oJuegoEquipoTO.getIdJuegoEquipo());
			oParametros.add(oJuegoEquipoTO.getIdJuego());
			oParametros.add(oJuegoEquipoTO.getIdEquipo());
			oParametros.add(oJuegoEquipoTO.getGanador());
			oParametros.add(oJuegoEquipoTO.getPuntos());
			oParametros.add(oJuegoEquipoTO.getReferencia());
			oParametros.add(oJuegoEquipoTO.getReferenciaRunline());
			oParametros.add(oJuegoEquipoTO.getReferenciaAB());
			oParametros.add(oJuegoEquipoTO.getReferenciaSuperRunline());

			strBuffquery.append("INSERT INTO juego_equipo VALUES (?,?,?,?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			oJuegoEquipoTO.setIdJuegoEquipo(String.valueOf(oEjecutorSql.getGeneratedKey()));
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.insertarJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla juego_equipo
	 * 
	 */
	public int actualizarJuegoEquipoDAO(JuegoEquipoIF oJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.actualizarJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oJuegoEquipoTO.setReferenciaRunline(oJuegoEquipoTO.getReferenciaRunline() == null ? "0" : oJuegoEquipoTO.getReferenciaRunline());
			oJuegoEquipoTO.setPuntos(oJuegoEquipoTO.getPuntos() == null ? "0" : oJuegoEquipoTO.getPuntos());
			oJuegoEquipoTO.setReferenciaSuperRunline(oJuegoEquipoTO.getReferenciaSuperRunline() == null ? "0" : oJuegoEquipoTO.getReferenciaSuperRunline());

			oParametros.add(oJuegoEquipoTO.getIdJuego());
			oParametros.add(oJuegoEquipoTO.getIdEquipo());
			oParametros.add(oJuegoEquipoTO.getGanador());
			oParametros.add(oJuegoEquipoTO.getPuntos());
			oParametros.add(oJuegoEquipoTO.getReferencia());
			oParametros.add(oJuegoEquipoTO.getReferenciaRunline());
			oParametros.add(oJuegoEquipoTO.getReferenciaAB());
			oParametros.add(oJuegoEquipoTO.getReferenciaSuperRunline());
			oParametros.add(oJuegoEquipoTO.getIdJuegoEquipo()); // primary key

			strBuffquery.append("UPDATE juego_equipo SET ");
			strBuffquery.append("id_juego=? , id_equipo=?, ganador=?, puntos=?, referencia=?, ");
			strBuffquery.append("referencia_runline=?, referencia_ab=?, referencia_super_runline=? ");
			strBuffquery.append("WHERE  id_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.actualizarJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla juego_equipo
	 * 
	 */
	public int actualizarGanadorJuegoEquipoDAO(JuegoEquipoIF oJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.actualizarGanadorJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oJuegoEquipoTO.getGanador());
			oParametros.add(oJuegoEquipoTO.getPuntos());
			oParametros.add(oJuegoEquipoTO.getIdJuegoEquipo()); // primary key

			strBuffquery.append("UPDATE juego_equipo SET ");
			strBuffquery.append("ganador=?, puntos=? ");
			strBuffquery.append("WHERE  id_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.actualizarGanadorJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla juego_equipo
	 * 
	 */
	public int eliminarJuegoEquipoDAO(JuegoEquipoIF oJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.eliminarJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oJuegoEquipoTO.getIdJuegoEquipo());
			strBuffquery.append("DELETE FROM juego_equipo  WHERE  (id_juego_equipo = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.eliminarJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla juego_equipo
	 * 
	 */
	public CachedRowSet listaJuegoEquipoDAO() throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.listaJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_juego_equipo, UPPER(id_juego) ");
			strBuffquery.append("FROM juego_equipo ");
			strBuffquery.append("ORDER BY UPPER(id_juego) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.listaJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla juego_equipo
	 * 
	 */
	public boolean cargarJuegoEquipoDAO(JuegoEquipoIF oJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.consultarJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_juego_equipo, id_juego, id_equipo, ");
			strBuffquery.append("ganador,puntos, referencia, referencia_runline, referencia_ab, referencia_super_runline ");
			strBuffquery.append("FROM juego_equipo ");

			boolean procesar = false;
			if (!oJuegoEquipoTO.getIdJuegoEquipo().equalsIgnoreCase("")) {
				oParametros.add(oJuegoEquipoTO.getIdJuegoEquipo());
				strBuffquery.append(" WHERE id_juego_equipo = ? ");
				procesar = true;
			} else if (!oJuegoEquipoTO.getIdJuego().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_juego) LIKE '%");
				strBuffquery.append(oJuegoEquipoTO.getIdJuego().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oJuegoEquipoTO.setIdJuegoEquipo(oCachedRowSet.getString("id_juego_equipo"));
					oJuegoEquipoTO.setIdJuego(oCachedRowSet.getString("id_juego"));
					oJuegoEquipoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
					oJuegoEquipoTO.setGanador(oCachedRowSet.getString("ganador"));
					oJuegoEquipoTO.setPuntos(oCachedRowSet.getString("puntos"));
					oJuegoEquipoTO.setReferencia(oCachedRowSet.getString("referencia"));
					oJuegoEquipoTO.setReferenciaRunline(oCachedRowSet.getString("referencia_runline"));
					oJuegoEquipoTO.setReferenciaAB(oCachedRowSet.getString("referencia_ab"));
					oJuegoEquipoTO.setReferenciaSuperRunline(oCachedRowSet.getString("referencia_super_runline"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.consultarJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego_equipo
	 * 
	 */
	public CachedRowSet listaJuegoEquipoPorJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_juego_equipo, id_juego ");
			strBuffquery.append("FROM juego_equipo ");

			if(oJuegoTO.getIdJuego().indexOf("_")!=-1){ 
				strBuffquery.append("WHERE  id_juego IN (");
				strBuffquery.append(oJuegoTO.getIdJuego().replaceAll("_",","));
				strBuffquery.append(") ");
			} else {
				oParametros.add(oJuegoTO.getIdJuego()); // primary key
				strBuffquery.append("WHERE  id_juego = ? ");
			}
			
			strBuffquery.append("ORDER BY id_juego_equipo ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego_equipo
	 * 
	 */
	public CachedRowSet listaJugadaPorJuegoEquipoDAO(String[] idJuegoEquipo) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		StringBuffer in = new StringBuffer();
		String sep = "";
		String sepIn = "";
		String para = "";
		try {
			for(int i=0; i<idJuegoEquipo.length;i++) {
				if(Constants.isNullEntero(idJuegoEquipo[i])) {
					break;
				}
				oParametros.add(idJuegoEquipo[i]);
				in.append(sepIn);
				in.append("?");
				sepIn=",";
			}

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_juego_equipo,c.id_jugada_juego_equipo,a.id_equipo,a.referencia,a.referencia_runline,a.referencia_super_runline,a.referencia_ab,b.id_usuario_juego_equipo, c.tipo, c.id_jugada, ");
			strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total,if(c.tipo='RL',b.spread,if(c.tipo='SR',b.super_spread,0))) As cantidad, ");
			strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total_logro,if(c.tipo='RL',b.spread_logro,if(c.tipo='SR',b.super_spread_logro,if(c.tipo IN ('ML','E','SI','NO'),b.money_line,0)))) As logro ");
			strBuffquery.append("FROM juego_equipo a, usuario_juego_equipo b, jugada_juego_equipo c ");
			strBuffquery.append("WHERE a.id_juego_equipo IN (").append(in).append(") ");
			strBuffquery.append("AND a.id_juego_equipo = b.id_juego_equipo ");
			strBuffquery.append("AND b.id_usuario_juego_equipo = c.id_usuario_juego_equipo ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	/**
	 * 
	 * Consulta una lista de jugadas pendientes
	 */
	public CachedRowSet listaJugadaPendientePorJuegoEquipoDAO(String[] idJuegoEquipo) throws Exception {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.listaJugadaPendientePorJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		StringBuffer in = new StringBuffer();
		String sep = "";
		String sepIn = "";
		String para = "";
		try {
			for(int i=0; i<idJuegoEquipo.length;i++) {
				if(Constants.isNullEntero(idJuegoEquipo[i])) {
					break;
				}
				oParametros.add(idJuegoEquipo[i]);
				in.append(sepIn);
				in.append("?");
				sepIn=",";
			}
			oParametros.add(Constants.STATUS_JUGADA_ELIMINADA);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_juego_equipo,c.id_jugada_juego_equipo,a.id_equipo,a.referencia,a.referencia_runline,a.referencia_super_runline,a.referencia_ab,b.id_usuario_juego_equipo, c.tipo, c.id_jugada, ");
			strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total,if(c.tipo='RL',b.spread,if(c.tipo='SR',b.super_spread,0))) As cantidad, ");
			strBuffquery.append("if(c.tipo='A' or c.tipo='B',b.total_logro,if(c.tipo='RL',b.spread_logro,if(c.tipo='SR',b.super_spread_logro,if(c.tipo IN ('ML','E','SI','NO'),b.money_line,0)))) As logro ");
			strBuffquery.append("FROM juego_equipo a, usuario_juego_equipo b, jugada_juego_equipo c, jugada d, usuario e  ");
			strBuffquery.append("WHERE a.id_juego_equipo IN (").append(in).append(") ");
			strBuffquery.append("AND a.id_juego_equipo = b.id_juego_equipo ");
			strBuffquery.append("AND b.id_usuario_juego_equipo = c.id_usuario_juego_equipo ");
			strBuffquery.append("AND c.id_jugada=d.id_jugada ");
			strBuffquery.append("AND e.id_usuario=d.id_usuario ");
			strBuffquery.append("AND d.id_status_jugada!=? ");
			strBuffquery.append("AND (e.id_rol=4 OR (d.id_status_jugada!=4 AND e.id_rol=3) ) ");


			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.listaJugadaPendientePorJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego_equipo
	 * 
	 */
	public boolean isReferenciaValidEquipoDAO(JuegoEquipoIF oJuegoEquipoIF, String fechaJuego) {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(oJuegoEquipoIF.getReferencia());
			oParametros.add(Constants.STATUS_JUEGO_SUSPENDIDO);
			oParametros.add(fechaJuego);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_juego ");
			strBuffquery.append("FROM juego a, juego_equipo b ");
			strBuffquery.append("WHERE a.id_juego = b.id_juego ");
			strBuffquery.append("AND b.referencia = ? ");
			strBuffquery.append("AND a.id_status_juego!=? ");
			strBuffquery.append("AND DATE_FORMAT(a.fecha_ini,'%Y-%m-%d')=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			return !oCachedRowSet.next();

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			return false; 
		}
	}
	
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego_equipo
	 * 
	 */
	public String getReferenciaDAO(String fechaJuego, String idDeporte) {
		log.info("Iniciando ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		String referencia = "0";
		try {
			
			oParametros.add(idDeporte);
			strBuffquery.append("select referencia from juego_equipo x, juego y where x.id_juego=y.id_juego and y.id_deporte=? order by x.id_juego_equipo desc limit 1");

			CachedRowSet cache = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (cache.next()) {
				referencia = cache.getString(1);
			}

			strBuffquery.setLength(0);
			strBuffquery.append("SELECT if(referencia_inicio is null,0,referencia_inicio) As referencia ");
			strBuffquery.append("FROM deporte ");
			strBuffquery.append("WHERE id_deporte = ? ");
			
			cache = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (cache.next()) {
				if(referencia.equals("0")) {
					referencia = cache.getString(1);
				}
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoEquipoDAO.listaJuegoEquipoPorJuegoDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			return "0"; 
		}
		return referencia;
	}
	
	
	
	

}
