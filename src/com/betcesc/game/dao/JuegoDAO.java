/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 *
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.bean.ResultadosBean;
import com.betcesc.game.common.Constants;
import com.betcesc.game.ctrl.back.RegisterGameActionForm;
import com.betcesc.game.interfaces.DeporteIF;
import com.betcesc.game.interfaces.JuegoIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JuegoTO;
import com.betcesc.game.to.ListaJuegoTO;
import com.jade.util.lbda.EjecutorSql;
import com.jade.util.lbda.EjecutorSqlManejaError;

/**
 * Administracion de la tabla juego.
 *
 * @author jrivero
 *
 *         Esta clase permite la actualizacion de la tabla juego
 *
 * @see EjecutorSql
 * @see CachedRowSet
 *
 */

public class JuegoDAO {
	private static final Log log = LogFactory.getLog(JuegoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene par�metros.
	 */
	public JuegoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();

	}

	public JuegoDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;

	}

	/**
	 *
	 * Inserta un registro en la tabla juego
	 *
	 */
	public int insertarJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.insertarJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			// iniciar parametros
			oJuegoTO.setIdJuego(oJuegoTO.getIdJuego() == null || oJuegoTO.getIdJuego().trim().equals("") ? "0" : oJuegoTO.getIdJuego());
			oJuegoTO.setFechaSis(Constants.getFechaLargaSQL());
			oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_BORRADOR);

			oJuegoTO.setSpreadActivo("1");
			oJuegoTO.setTotalActivo("1");
			oJuegoTO.setMoneyActivo("1");
			oJuegoTO.setSuperSpreadActivo("1");

			oParametros.add(oJuegoTO.getIdJuego());
			oParametros.add(oJuegoTO.getFechaSis());
			oParametros.add(oJuegoTO.getFechaIni());
			oParametros.add(oJuegoTO.getFechaFin());
			oParametros.add(oJuegoTO.getMinutosCierre());
			oParametros.add(oJuegoTO.getIdCampeonato());
			oParametros.add(oJuegoTO.getIdStatusJuego());
			oParametros.add(Constants.ID_USUARIO_ADMINISTRADOR); // los juegos seran almacenados on el id del admin
			oParametros.add(oJuegoTO.getIdDeporte());
			oParametros.add(oJuegoTO.getIdLiga());
			oParametros.add(oJuegoTO.getSpreadActivo());
			oParametros.add(oJuegoTO.getTotalActivo());
			oParametros.add(oJuegoTO.getMoneyActivo());
			oParametros.add(oJuegoTO.getIdJuegoPadre());
			oParametros.add(oJuegoTO.getIdUsuario()); // es el usuario actual en el sistema
			oParametros.add(oJuegoTO.getSuperSpreadActivo());
			oParametros.add(oJuegoTO.getDominioActual());
			oParametros.add(oJuegoTO.getDominioAnterior());
			oParametros.add(Constants.isNull(oJuegoTO.getIdUsuarioTotaliza(), Constants.ID_USUARIO_ADMINISTRADOR));

			strBuffquery.append("INSERT INTO juego VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			oJuegoTO.setIdJuego(String.valueOf(oEjecutorSql.getGeneratedKey()));
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.insertarJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Actualiza un registro en la tabla juego
	 *
	 */
	public int actualizarJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.actualizarJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			// iniciar parametros
			oJuegoTO.setFechaSis(Constants.getFechaLargaSQL());

			oParametros.add(oJuegoTO.getFechaSis());
			oParametros.add(oJuegoTO.getFechaIni());
			oParametros.add(oJuegoTO.getFechaFin());
			oParametros.add(oJuegoTO.getMinutosCierre());
			oParametros.add(oJuegoTO.getIdCampeonato());
			oParametros.add(oJuegoTO.getIdStatusJuego());
			oParametros.add(oJuegoTO.getIdDeporte());
			oParametros.add(oJuegoTO.getIdLiga());
			oParametros.add(Constants.isNull(oJuegoTO.getSpreadActivo(), "1"));
			oParametros.add(Constants.isNull(oJuegoTO.getTotalActivo(), "1"));
			oParametros.add(Constants.isNull(oJuegoTO.getMoneyActivo(), "1"));
			oParametros.add(Constants.isNull(oJuegoTO.getSuperSpreadActivo(), "1"));
			oParametros.add(oJuegoTO.getDominioActual());
			oParametros.add(Constants.isNull(oJuegoTO.getIdUsuarioTotaliza(), Constants.ID_USUARIO_ADMINISTRADOR));

			oParametros.add(oJuegoTO.getIdJuego()); // primary key

			strBuffquery.append("UPDATE juego SET ");
			strBuffquery.append("fecha_sis=? , fecha_ini=?, fecha_fin=?, ");
			strBuffquery.append("minutos_cierre=? , id_campeonato=?, id_status_juego=?, ");
			strBuffquery.append("id_deporte=?, id_liga=?, ");
			strBuffquery.append("spread_activo=? , total_activo=?, money_activo=?, super_spread_activo=?, ");
			strBuffquery.append("dominio_actual=?, id_usuario_totaliza=? ");
			strBuffquery.append("WHERE  id_juego = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.actualizarJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Actualiza un registro en la tabla juego
	 *
	 */
	public int actualizarStatusJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.actualizarStatusJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oParametros.add(oJuegoTO.getIdStatusJuego());
			strBuffquery.append("UPDATE juego SET id_status_juego=? ");

			if (oJuegoTO.getIdStatusJuego().equals(Constants.STATUS_JUEGO_TOTALIZADO)) {
				oJuegoTO.setFechaFin(Constants.getFechaLargaSQL());

				oParametros.add(oJuegoTO.getFechaFin());
				oParametros.add(oJuegoTO.getSpreadActivo());
				oParametros.add(oJuegoTO.getTotalActivo());
				oParametros.add(oJuegoTO.getMoneyActivo());
				oParametros.add(oJuegoTO.getSuperSpreadActivo());
				oParametros.add(oJuegoTO.getIdUsuarioTotaliza());
				strBuffquery.append(", fecha_fin=?, spread_activo=?, total_activo=?, money_activo=?, super_spread_activo=?, id_usuario_totaliza=? ");
			}

			if (oJuegoTO.getIdJuego().indexOf("_") != -1) {
				strBuffquery.append("WHERE  id_juego IN (");
				strBuffquery.append(oJuegoTO.getIdJuego().replaceAll("_", ","));
				strBuffquery.append(") ");
			} else {
				oParametros.add(oJuegoTO.getIdJuego()); // primary key
				strBuffquery.append("WHERE  id_juego = ? ");
			}

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.actualizarStatusJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Elimina un registro en la tabla juego
	 *
	 */
	public int eliminarJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.eliminarJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			strBuffquery.append("DELETE FROM juego WHERE ");

			if (oJuegoTO.getIdJuego().indexOf("_") != -1) {
				strBuffquery.append("id_juego IN (").append(oJuegoTO.getIdJuego().replaceAll("_", ",")).append(") ");
			} else {
				strBuffquery.append("id_juego = ").append(oJuegoTO.getIdJuego());
			}

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.eliminarJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Elimina un registro en la tabla juego
	 *
	 */
	public int eliminarDetalleJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.eliminarDetalleJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		StringBuffer where = new StringBuffer();
		StringBuffer ids = new StringBuffer();
		String sep = "";
		int numRegAct = 0;
		try {

			strBuffquery.setLength(0);
			strBuffquery.append("select id_juego_equipo from juego_equipo where ");
			if (oJuegoTO.getIdJuego().indexOf("_") != -1) {
				where.append("id_juego IN (").append(oJuegoTO.getIdJuego().replaceAll("_", ",")).append(") ");
			} else {
				where.append("id_juego = ").append(oJuegoTO.getIdJuego());
			}

			strBuffquery.append(where);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			while (oCachedRowSet.next()) {
				ids.append(sep).append(oCachedRowSet.getString(1));
				sep = Constants.COMA;
			}

			strBuffquery.setLength(0);
			strBuffquery.append("DELETE FROM juego_equipo_lanzador WHERE id_juego_equipo IN (").append(ids).append(") ");
			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			strBuffquery.setLength(0);
			strBuffquery.append("DELETE FROM usuario_juego_equipo WHERE id_juego_equipo IN (").append(ids).append(") ");
			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			strBuffquery.setLength(0);
			strBuffquery.append("DELETE FROM juego_equipo where ").append(where).append(" ");
			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.eliminarDetalleJuegoDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Consulta una lista de registros en la tabla juego
	 *
	 */
	public CachedRowSet listaJuegoDAO(UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.listaJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_juego, a.fecha_sis, a.fecha_ini, a.fecha_fin, ");
			strBuffquery.append("a.minutos_cierre, a.id_campeonato, a.id_status_juego, ");
			strBuffquery.append("a.id_usuario, a.id_deporte, a.id_liga, b.desc_campeonato, c.desc_deporte, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_ini,'%d/%m/%Y') dia, DATE_FORMAT(a.fecha_ini,'%h:%i%p') hora, ");
			strBuffquery.append("d.desc_status_juego, e.desc_liga, a.spread_activo, a.super_spread_activo, a.total_activo, a.money_activo, a.id_juego_padre ");
			strBuffquery.append("FROM juego a, campeonato b, deporte c, status_juego d, liga e ");
			strBuffquery.append("WHERE a.id_campeonato=b.id_campeonato ");
			strBuffquery.append("AND a.id_deporte=c.id_deporte ");
			strBuffquery.append("AND a.id_status_juego=d.id_status_juego ");
			strBuffquery.append("AND a.id_liga=e.id_liga ");

			if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && !oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oParametros.add(Constants.STATUS_JUEGO_BORRADOR);
				strBuffquery.append("AND a.id_status_juego != ? ");
			}

			strBuffquery.append("ORDER BY fecha_ini ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.listaJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	public CachedRowSet listaJuegoConEquipoPorCentroLigaDAO(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		return listaJuegoConEquipoDAO(oUsuarioTO, oJuegoTO, true, false, true);
	}

	public CachedRowSet listaJuegoConEquipoPorCentroDAO(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		return listaJuegoConEquipoDAO(oUsuarioTO, oJuegoTO, true, false, false);
	}

	public CachedRowSet listaJuegoConEquipoDAO(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		return listaJuegoConEquipoDAO(oUsuarioTO, oJuegoTO, false, false, false);
	}

	public CachedRowSet listaJuegoParaJugadaConEquipoDAO(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO) throws Exception {
		return listaJuegoConEquipoDAO(oUsuarioTO, oJuegoTO, false, true, false);
	}

	/**
	 *
	 * Consulta una lista de registros en la tabla juego
	 *
	 */
	public CachedRowSet listaJuegoConEquipoDAO(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO, boolean porCentro, boolean soloAbierto, boolean isLiga) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.listaJuegoConEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean isWhere = false;
		try {
			oParametros.add(Constants.ROL_ADMINISTRADOR);
			oParametros.add(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
			if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA))) {
				oParametros.add(oUsuarioTO.getIdSupervisor());
				oParametros.add(oUsuarioTO.getIdSupervisor());
			} else {
				oParametros.add(oUsuarioTO.getIdUsuario());
				oParametros.add(oUsuarioTO.getIdUsuario());
			}

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.*,b.*,c.*,d.*,e.*,f.*,g.*,h.*,i.*,j.*,k.*, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_ini,'%d/%m/%Y') dia, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_ini,'%h:%i%p') hora, ");
			strBuffquery.append("if(l.id_status_juego IS NOT NULL,'Abierto/Bloq.',concat(d.desc_status_juego,if(m.id_rol=?,'/Adm.',if(m.id_rol=?,'/Taq.','')))) AS status_real ");
			strBuffquery.append("FROM juego a ");
			strBuffquery.append("LEFT OUTER JOIN campeonato b ON a.id_campeonato=b.id_campeonato ");
			strBuffquery.append("LEFT OUTER JOIN deporte c ON a.id_deporte=c.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego l ON a.id_juego=l.id_juego AND l.id_usuario=?  ");
			strBuffquery.append("LEFT OUTER JOIN status_juego d ON a.id_status_juego=d.id_status_juego ");
			strBuffquery.append("LEFT OUTER JOIN liga e ON a.id_liga=e.id_liga ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo f ON a.id_juego=f.id_juego ");
			strBuffquery.append("LEFT OUTER JOIN equipo g ON f.id_equipo=g.id_equipo ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo_lanzador h ON f.id_juego_equipo=h.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN lanzador i ON h.id_lanzador=i.id_lanzador ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego_equipo j ON f.id_juego_equipo=j.id_juego_equipo AND j.id_usuario = ? AND j.desactivado=0 ");
			strBuffquery.append("LEFT OUTER JOIN deporte k ON a.id_deporte=k.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN usuario m ON j.id_usuario=m.id_usuario ");
			strBuffquery.append("WHERE j.desactivado=0 ");
			if (soloAbierto) {
				oParametros.add(Constants.STATUS_JUEGO_ABIERTO);
				strBuffquery.append("AND a.id_status_juego = ? ");
			}

			/*
			 * if(isDiario) {
			 * strBuffquery.append(!isWhere?"WHERE":"AND").append(
			 * " a.fecha_ini_corta=DATE_FORMAT(now(),'%Y-%m-%d') ");
			 * isWhere=true; }
			 */

			strBuffquery.append("AND ( g.id_equipo!='EMP' OR g.id_equipo='EMP' && k.empate=1 ) ");

			if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && !oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oParametros.add(Constants.STATUS_JUEGO_BORRADOR);
				strBuffquery.append("AND a.id_status_juego != ? ");
			}

			if (isLiga && oJuegoTO.getIdLiga() != null && !oJuegoTO.getIdLiga().trim().equals("")) {
				strBuffquery.append("AND a.id_liga IN (").append(oJuegoTO.getIdLiga()).append(") ");
			}

			if (oJuegoTO != null && !Constants.isNull(oJuegoTO.getIdDeporte())) {
				if (oJuegoTO.getIdDeporte().indexOf("-") != -1) {
					strBuffquery.append("AND a.id_deporte IN (").append(oJuegoTO.getIdDeporte().replaceAll("-", ",")).append(") ");
				} else {
					oParametros.add(oJuegoTO.getIdDeporte());
					strBuffquery.append("AND a.id_deporte = ? ");
				}
			}

			// if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA))) {
			// oParametros.add(Constants.STATUS_JUEGO_ABIERTO);
			// strBuffquery.append("AND a.id_status_juego = ? AND l.id_status_juego IS NULL ");
			// }

			if (oJuegoTO != null && oJuegoTO.getFechaIni() != null && !oJuegoTO.getFechaIni().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJuegoTO.getFechaIni(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJuegoTO.getFechaIni(), false));
				strBuffquery.append("AND a.fecha_ini>= ? AND a.fecha_ini<= ? ");
			}
			if (oJuegoTO != null && oJuegoTO.getIdStatusJuego() != null && !oJuegoTO.getIdStatusJuego().trim().equals("")) {
				if (!oJuegoTO.getIdStatusJuego().equals("0")) {
					oParametros.add(oJuegoTO.getIdStatusJuego());
					strBuffquery.append("AND a.id_status_juego=? ");
				}
			}

			// strBuffquery.append("ORDER BY DATE_FORMAT(a.fecha_ini,'%Y%m%d'),e.desc_liga,a.fecha_ini,a.id_juego,j.id_juego_equipo  ");
			// strBuffquery.append("ORDER BY a.id_deporte,f.referencia,DATE_FORMAT(a.fecha_ini,'%Y%m%d'),e.desc_liga,a.fecha_ini,a.id_juego,j.id_juego_equipo  ");
			// strBuffquery.append("ORDER BY a.id_deporte,DATE_FORMAT(a.fecha_ini,'%Y%m%d'),e.desc_liga,a.fecha_ini,a.id_juego,f.referencia,j.id_juego_equipo  ");
			if (isLiga) {
				strBuffquery.append("ORDER BY e.id_liga,a.id_deporte,DATE_FORMAT(a.fecha_ini,'%Y%m%d'),e.desc_liga,a.fecha_ini,a.id_juego,f.referencia,j.id_juego_equipo  ");
			} else {
				strBuffquery.append("ORDER BY a.id_deporte,DATE_FORMAT(a.fecha_ini,'%Y%m%d'),e.desc_liga,a.fecha_ini,a.id_juego,f.referencia,j.id_juego_equipo  ");
			}

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (!oCachedRowSet.next()) {
				if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && !oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR) && !oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
					oParametros.set(1, oUsuarioTO.getIdSupervisor());
					oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				}
			}
			oCachedRowSet.beforeFirst();

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.listaJuegoConEquipoDAO");
			e.printStackTrace();
			throw e;
		}
		return oCachedRowSet;
	}

	public String getValues(String query) throws EjecutorSqlManejaError, Exception {
		return getValues(query, null, null);
	}

	public String getValues(String query, String p1) throws EjecutorSqlManejaError, Exception {
		return getValues(query, p1, null);
	}

	public String getValues(String query, String p1, String p2) throws EjecutorSqlManejaError, Exception {
		StringBuffer sb = new StringBuffer(query);
		String separador = "";
		String value = "";
		HashMap map = new HashMap();
		ArrayList p = new ArrayList();
		if (p1 != null) {
			p.add(p1);
		}
		if (p2 != null) {
			p.add(p2);
		}
		oCachedRowSet = oEjecutorSql.ejecutaQuery(sb.toString(), p);
		sb.setLength(0);

		while (oCachedRowSet.next()) {
			value = oCachedRowSet.getString(1);
			if (!map.containsKey(value)) {
				map.put(value, value);
				sb.append(separador);
				sb.append("'");
				sb.append(value);
				sb.append("'");
				separador = ",";
			}
		}
		return separador.equals("") ? "' '" : sb.toString();
	}

	/**
	 *
	 * Consulta una lista de registros en la tabla juego
	 *
	 */
	public CachedRowSet listaJuegoAbiertoDAO(UsuarioIF oUsuarioTO, DeporteIF oDeporteTO, boolean isTotal) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.listaJuegoAbiertoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(oUsuarioTO.getIdSupervisor());
			oParametros.add(Constants.STATUS_JUEGO_ABIERTO);
			oParametros.add(Constants.getFechaLargaSQL());

			oCachedRowSet = new CachedRowSet();
			if (isTotal) {
				strBuffquery.append("SELECT count(a.id_juego) ");
			} else {
				strBuffquery.append("SELECT a.*, DATE_FORMAT(a.fecha_ini,'%d/%m/%Y') dia, DATE_FORMAT(a.fecha_ini,'%h:%i%p') hora ,b.* ");
			}
			strBuffquery.append("FROM juego a LEFT OUTER JOIN usuario_juego b ON a.id_juego=b.id_juego and b.id_usuario=? ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo c ON a.id_juego=c.id_juego ");
			strBuffquery.append("WHERE a.id_status_juego = ? ");
			strBuffquery.append("AND now()<date_sub(a.fecha_ini,interval (a.minutos_cierre) minute) ");
			if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
				strBuffquery.append("AND b.id_status_juego IS NULL ");
			}
			strBuffquery.append("AND a.fecha_ini>=? ");

			if (!isTotal) {
				if (oDeporteTO.getIdDeporte() != null && !oDeporteTO.getIdDeporte().trim().equals("")) {
					oParametros.add(oDeporteTO.getIdDeporte());
					strBuffquery.append("AND a.id_deporte = ? ");
				}
			}

			// strBuffquery.append("ORDER BY a.id_liga,a.fecha_ini ");
			strBuffquery.append("GROUP BY a.id_juego ");
			if (!isTotal) {
				strBuffquery.append("ORDER BY a.id_liga,c.referencia,a.fecha_ini ");
			}
			
			log.info(strBuffquery.toString());

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.listaJuegoAbiertoDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			System.err.println(e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 *
	 * Consulta un registro en la tabla juego
	 *
	 */
	public boolean cargarJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.consultarJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_juego, fecha_sis, fecha_ini, ");
			strBuffquery.append("fecha_fin, minutos_cierre, id_campeonato, ");
			strBuffquery.append("id_status_juego, id_usuario, id_deporte, id_liga, ");
			strBuffquery.append("spread_activo, super_spread_activo, total_activo, money_activo, id_juego_padre ");
			strBuffquery.append("FROM juego ");

			boolean procesar = false;
			if (!oJuegoTO.getIdJuego().equalsIgnoreCase("")) {
				oParametros.add(oJuegoTO.getIdJuego());
				strBuffquery.append(" WHERE id_juego = ? ");
				procesar = true;
			} else if (!oJuegoTO.getFechaSis().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(fecha_sis) LIKE '%");
				strBuffquery.append(oJuegoTO.getFechaSis().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oJuegoTO.setIdJuego(oCachedRowSet.getString("id_juego"));
					oJuegoTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
					oJuegoTO.setFechaIni(oCachedRowSet.getString("fecha_ini"));
					oJuegoTO.setFechaFin(oCachedRowSet.getString("fecha_fin"));
					oJuegoTO.setMinutosCierre(oCachedRowSet.getString("minutos_cierre"));
					oJuegoTO.setIdCampeonato(oCachedRowSet.getString("id_campeonato"));
					oJuegoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
					oJuegoTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
					oJuegoTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
					oJuegoTO.setIdLiga(oCachedRowSet.getString("id_liga"));
					oJuegoTO.setSpreadActivo(Constants.isNull(oCachedRowSet.getString("spread_activo"), "1"));
					oJuegoTO.setTotalActivo(Constants.isNull(oCachedRowSet.getString("total_activo"), "1"));
					oJuegoTO.setMoneyActivo(Constants.isNull(oCachedRowSet.getString("money_activo"), "1"));
					oJuegoTO.setIdJuegoPadre(oCachedRowSet.getString("id_juego_padre"));
					oJuegoTO.setSuperSpreadActivo(Constants.isNull(oCachedRowSet.getString("super_spread_activo"), "1"));
					oJuegoTO.setIdUsuarioTotaliza(oCachedRowSet.getString("id_usuario_totaliza"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.consultarJuegoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 *
	 * Consulta un registro en la tabla juego
	 *
	 */
	public HashMap cargarJuegoPorIdDAO(String idJuegos) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.cargarJuegoPorIdDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		HashMap retorno = new HashMap();
		JuegoTO oJuegoTO = null;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_juego, fecha_sis, fecha_ini, ");
			strBuffquery.append("fecha_fin, minutos_cierre, id_campeonato, ");
			strBuffquery.append("id_status_juego, id_usuario, id_deporte, id_liga, ");
			strBuffquery.append("spread_activo, super_spread_activo, total_activo, money_activo, id_juego_padre, ");
			strBuffquery.append("id_usuario_totaliza ");
			strBuffquery.append("FROM juego ");
			strBuffquery.append("WHERE id_juego IN (").append(idJuegos).append(")");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			while (oCachedRowSet.next()) {
				oJuegoTO = new JuegoTO();
				oJuegoTO.setIdJuego(oCachedRowSet.getString("id_juego"));
				oJuegoTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
				oJuegoTO.setFechaIni(oCachedRowSet.getString("fecha_ini"));
				oJuegoTO.setFechaFin(oCachedRowSet.getString("fecha_fin"));
				oJuegoTO.setMinutosCierre(oCachedRowSet.getString("minutos_cierre"));
				oJuegoTO.setIdCampeonato(oCachedRowSet.getString("id_campeonato"));
				oJuegoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
				oJuegoTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
				oJuegoTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
				oJuegoTO.setIdLiga(oCachedRowSet.getString("id_liga"));
				oJuegoTO.setSpreadActivo(Constants.isNull(oCachedRowSet.getString("spread_activo"), "1"));
				oJuegoTO.setTotalActivo(Constants.isNull(oCachedRowSet.getString("total_activo"), "1"));
				oJuegoTO.setMoneyActivo(Constants.isNull(oCachedRowSet.getString("money_activo"), "1"));
				oJuegoTO.setIdJuegoPadre(oCachedRowSet.getString("id_juego_padre"));
				oJuegoTO.setSuperSpreadActivo(Constants.isNull(oCachedRowSet.getString("super_spread_activo"), "1"));
				oJuegoTO.setIdUsuarioTotaliza(oCachedRowSet.getString("id_usuario_totaliza"));
				retorno.put(oJuegoTO.getIdJuego(), oJuegoTO);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.cargarJuegoPorIdDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		log.info("Finaliado ejecucion de JuegoDAO.cargarJuegoPorIdDAO");
		return retorno;
	}

	/**
	 *
	 * Consulta un registro en la tabla juego
	 *
	 */
	public boolean cargarJuegoCompletoDAO(RegisterGameActionForm oJuegoTO, UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.cargarJuegoCompletoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();
			oParametros.add(oJuegoTO.getIdJuego());
			// oParametros.add(oJuegoTO.getIdUsuario());

			strBuffquery.setLength(0);
			strBuffquery.append("SELECT  a.*,b.*,c.*,d.*,e.*,f.*,g.*,h.*,i.*,j.*,k.*,j.id_usuario As id_usuario_detalle ");
			strBuffquery.append("FROM juego a ");
			strBuffquery.append("LEFT OUTER JOIN campeonato b ON a.id_campeonato=b.id_campeonato ");
			strBuffquery.append("LEFT OUTER JOIN deporte c ON a.id_deporte=c.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN status_juego d ON a.id_status_juego=d.id_status_juego ");
			strBuffquery.append("LEFT OUTER JOIN liga e ON a.id_liga=e.id_liga ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo f ON a.id_juego=f.id_juego ");
			strBuffquery.append("LEFT OUTER JOIN equipo g ON f.id_equipo=g.id_equipo ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo_lanzador h ON f.id_juego_equipo=h.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN lanzador i ON h.id_lanzador=i.id_lanzador ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego_equipo j ON f.id_juego_equipo=j.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN deporte k ON a.id_deporte=k.id_deporte ");
			strBuffquery.append("WHERE a.id_juego = ? ");
			strBuffquery.append("AND date_format(j.fecha_sis,'%Y%m%d%H%i%s') in (select date_format(max(xx.fecha_sis),'%Y%m%d%H%i%s') from usuario_juego_equipo xx where xx.id_usuario=").append(oJuegoTO.getIdUsuario()).append(" and xx.id_juego_equipo=f.id_juego_equipo group by xx.id_juego_equipo) ");
			strBuffquery.append("AND j.id_usuario=").append(oJuegoTO.getIdUsuario()).append(" ");
			strBuffquery.append("AND ( g.id_equipo!='EMP' OR g.id_equipo='EMP' && k.empate=1 ) ");
			strBuffquery.append("ORDER BY a.fecha_ini,a.id_juego,f.id_juego_equipo ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			String values = "";
			if (!oCachedRowSet.next()) {
				oParametros.removeAll(oParametros);
				oParametros.add(oJuegoTO.getIdJuego());

				strBuffquery.setLength(0);
				strBuffquery.append("SELECT  a.*,b.*,c.*,d.*,e.*,f.*,g.*,h.*,i.*,j.*,k.*,j.id_usuario As id_usuario_detalle ");
				strBuffquery.append("FROM juego a ");
				strBuffquery.append("LEFT OUTER JOIN campeonato b ON a.id_campeonato=b.id_campeonato ");
				strBuffquery.append("LEFT OUTER JOIN deporte c ON a.id_deporte=c.id_deporte ");
				strBuffquery.append("LEFT OUTER JOIN status_juego d ON a.id_status_juego=d.id_status_juego ");
				strBuffquery.append("LEFT OUTER JOIN liga e ON a.id_liga=e.id_liga ");
				strBuffquery.append("LEFT OUTER JOIN juego_equipo f ON a.id_juego=f.id_juego ");
				strBuffquery.append("LEFT OUTER JOIN equipo g ON f.id_equipo=g.id_equipo ");
				strBuffquery.append("LEFT OUTER JOIN juego_equipo_lanzador h ON f.id_juego_equipo=h.id_juego_equipo ");
				strBuffquery.append("LEFT OUTER JOIN lanzador i ON h.id_lanzador=i.id_lanzador ");
				strBuffquery.append("LEFT OUTER JOIN usuario_juego_equipo j ON f.id_juego_equipo=j.id_juego_equipo ");
				strBuffquery.append("LEFT OUTER JOIN deporte k ON a.id_deporte=k.id_deporte ");
				strBuffquery.append("WHERE a.id_juego = ? ");
				if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) { // || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
					values = getValues("select usu.id_supervisor from usuario usu where usu.id_rol=? group by usu.id_supervisor", Constants.ROL_ADMINISTRADOR);
					values = getValues("select date_format(max(xx.fecha_sis),'%Y%m%d%H%i%s') from usuario_juego_equipo xx where xx.id_usuario IN (".concat(values).concat(") and xx.id_juego_equipo in (select id_juego_equipo from juego_equipo where id_juego=?) group by xx.id_juego_equipo"),
							oJuegoTO.getIdJuego());
					strBuffquery.append("AND date_format(j.fecha_sis,'%Y%m%d%H%i%s') in (").append(values).append(") ");
					// strBuffquery.append("AND j.fecha_sis in (select max(xx.fecha_sis) from usuario_juego_equipo xx where xx.id_usuario IN (").append(values).append(") group by xx.id_juego_equipo) ");
				} else {
					values = getValues("select usu.id_supervisor from usuario usu where usu.id_usuario=?", oJuegoTO.getIdUsuario());

					strBuffquery.append("AND date_format(j.fecha_sis,'%Y%m%d%H%i%s') in (select date_format(max(xx.fecha_sis),'%Y%m%d%H%i%s') from usuario_juego_equipo xx where xx.id_usuario=(").append(values).append(")  and f.id_juego_equipo=xx.id_juego_equipo group by xx.id_juego_equipo) ");
				}
				strBuffquery.append("AND ( g.id_equipo!='EMP' OR g.id_equipo='EMP' && k.empate=1 ) ");
				strBuffquery.append("ORDER BY a.fecha_ini,a.id_juego,f.id_juego_equipo ");
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			}

			oCachedRowSet.beforeFirst();
			if (oCachedRowSet.next()) {
				// oCachedRowSet.absolute(oCachedRowSet.size() - 2);

				oJuegoTO.setIdJuego(oCachedRowSet.getString("id_juego"));
				oJuegoTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
				oJuegoTO.setFechaIni(oCachedRowSet.getString("fecha_ini"));
				oJuegoTO.setFechaFin(oCachedRowSet.getString("fecha_fin"));
				oJuegoTO.setMinutosCierre(oCachedRowSet.getString("minutos_cierre"));
				oJuegoTO.setIdCampeonato(oCachedRowSet.getString("id_campeonato"));
				oJuegoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
				oJuegoTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
				oJuegoTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
				oJuegoTO.setIdLiga(oCachedRowSet.getString("id_liga"));
				oJuegoTO.setDescLiga(oCachedRowSet.getString("desc_liga"));
				oJuegoTO.setIniciales(oCachedRowSet.getString("iniciales"));
				oJuegoTO.setDescStatusJuego(oCachedRowSet.getString("desc_status_juego"));
				oJuegoTO.setNombre(oCachedRowSet.getString("desc_campeonato"));
				oJuegoTO.setSpreadActivo(Constants.isNull(oCachedRowSet.getString("spread_activo"), "1"));
				oJuegoTO.setSuperSpreadActivo(Constants.isNull(oCachedRowSet.getString("super_spread_activo"), "1"));
				oJuegoTO.setTotalActivo(Constants.isNull(oCachedRowSet.getString("total_activo"), "1"));
				oJuegoTO.setMoneyActivo(Constants.isNull(oCachedRowSet.getString("money_activo"), "1"));
				oJuegoTO.setIdJuegoPadre(oCachedRowSet.getString("id_juego_padre"));
				oJuegoTO.setEmpate(oCachedRowSet.getString("empate"));
				oJuegoTO.setDominioActual(oCachedRowSet.getString("dominio_actual"));
				oJuegoTO.setDominioAnterior(oCachedRowSet.getString("dominio_anterior"));
				oJuegoTO.setIdUsuarioTotaliza(oCachedRowSet.getString("id_usuario_totaliza"));

				int i = 0;
				do {
					String spread = oCachedRowSet.getString("spread");
					String superSpread = oCachedRowSet.getString("super_spread") == null ? "0.0" : oCachedRowSet.getString("super_spread");
					String total = oCachedRowSet.getString("total");

					oJuegoTO.get_IdUsuario()[i] = oCachedRowSet.getString("id_usuario_detalle");
					oJuegoTO.get_IdUsuarioJuegoEquipo()[i] = oCachedRowSet.getString("id_usuario_juego_equipo");
					oJuegoTO.get_IdJuegoEquipo()[i] = oCachedRowSet.getString("id_juego_equipo");
					oJuegoTO.get_Referencia()[i] = oCachedRowSet.getString("referencia");
					oJuegoTO.get_ReferenciaRunline()[i] = oCachedRowSet.getString("referencia_runline");
					oJuegoTO.get_ReferenciaSuperRunline()[i] = oCachedRowSet.getString("referencia_super_runline");
					oJuegoTO.get_ReferenciaAB()[i] = oCachedRowSet.getString("referencia_ab");
					oJuegoTO.get_IdEquipo()[i] = oCachedRowSet.getString("id_equipo");
					oJuegoTO.get_Abreviatura()[i] = oCachedRowSet.getString("abreviatura");
					oJuegoTO.get_DescEquipo()[i] = oCachedRowSet.getString("desc_equipo");
					oJuegoTO.get_NombreLanzador()[i] = oCachedRowSet.getString("nombre_lanzador");
					oJuegoTO.get_IdLanzador()[i] = Constants.parseInt(oCachedRowSet.getString("id_lanzador"));
					oJuegoTO.get_Spread()[i] = Constants.parseInt(spread.substring(0, spread.indexOf(".")));
					oJuegoTO.get_SpreadDecimal()[i] = Constants.parseInt(spread.substring(spread.indexOf(".") + 1));
					oJuegoTO.get_SpreadLogro()[i] = Constants.parseInt(oCachedRowSet.getString("spread_logro"));
					oJuegoTO.get_Total()[i] = Constants.parseInt(total.substring(0, total.indexOf(".")));
					oJuegoTO.get_TotalDecimal()[i] = Constants.parseInt(total.substring(total.indexOf(".") + 1));
					oJuegoTO.get_TotalLogro()[i] = Constants.parseInt(oCachedRowSet.getString("total_logro"));
					oJuegoTO.get_MoneyLine()[i] = Constants.parseInt(oCachedRowSet.getString("money_line"));
					oJuegoTO.get_SuperSpread()[i] = Constants.parseInt(superSpread.substring(0, superSpread.indexOf(".")));
					oJuegoTO.get_SuperSpreadDecimal()[i] = Constants.parseInt(superSpread.substring(superSpread.indexOf(".") + 1));
					oJuegoTO.get_SuperSpreadLogro()[i] = Constants.parseInt(oCachedRowSet.getString("super_spread_logro"));

					i++;
				} while (oCachedRowSet.next());

				retorno = true;
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.cargarJuegoCompletoDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return retorno;
	}

	public static void main(String[] args) throws ParseException {
		// SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String f = new String("2008-07-02 10:56:30");

		// Date date = new Date();
		// log.info(sdf3.format(date));
		//log.info(Constants.getFechaLargaSQL());
		// log.info(sdf3.parse(f));
	}

	/**
	 *
	 * Consulta una lista de registros en la tabla juego
	 *
	 */
	public ArrayList listaJuegoConEquipoNewDAO(UsuarioIF oUsuarioTO, JuegoIF oJuegoTO, boolean porCentro, boolean soloAbierto) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.listaJuegoConEquipoDAO");
		ArrayList oParametros = new ArrayList();
		ArrayList lista = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(Constants.ROL_ADMINISTRADOR);
			oParametros.add(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
			if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA))) {
				oParametros.add(oUsuarioTO.getIdSupervisor());
			} else {
				oParametros.add(oUsuarioTO.getIdUsuario());
			}

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT a.id_juego,e.desc_liga,e.iniciales,a.id_status_juego, ");
			strBuffquery.append("a.id_deporte,f.referencia,g.abreviatura,g.desc_equipo,i.nombre_lanzador, ");
			strBuffquery.append("j.money_line,g.id_equipo,j.spread,j.spread_logro,j.super_spread,j.super_spread_logro,j.total, ");
			strBuffquery.append("j.total_logro,f.puntos,a.id_juego_padre, k.empate, a.dominio_actual, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_ini,'%d/%m/%Y') dia, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_ini,'%h:%i%p') hora, ");
			strBuffquery.append("if(l.id_status_juego IS NOT NULL,'Abierto/Bloq.',concat(d.desc_status_juego,if(m.id_rol=?,'/Adm.',if(m.id_rol=?,'/Taq.','')))) AS status_real, ");
			strBuffquery.append("a.id_usuario_totaliza, o.usuario As usuario_totaliza ");
			strBuffquery.append("FROM juego a ");
			strBuffquery.append("LEFT OUTER JOIN campeonato b ON a.id_campeonato=b.id_campeonato ");
			strBuffquery.append("LEFT OUTER JOIN deporte c ON a.id_deporte=c.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego l ON a.id_juego=l.id_juego AND l.id_usuario=?  ");
			strBuffquery.append("LEFT OUTER JOIN status_juego d ON a.id_status_juego=d.id_status_juego ");
			strBuffquery.append("LEFT OUTER JOIN liga e ON a.id_liga=e.id_liga ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo f ON a.id_juego=f.id_juego ");
			strBuffquery.append("LEFT OUTER JOIN equipo g ON f.id_equipo=g.id_equipo ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo_lanzador h ON f.id_juego_equipo=h.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN lanzador i ON h.id_lanzador=i.id_lanzador ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego_equipo j ON f.id_juego_equipo=j.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN deporte k ON a.id_deporte=k.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN usuario m ON j.id_usuario=m.id_usuario ");
			strBuffquery.append("LEFT OUTER JOIN usuario o ON a.id_usuario_totaliza=o.id_usuario ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
				strBuffquery.append("WHERE (a.dominio_actual='000' ||  a.dominio_actual='").append(oUsuarioTO.getDominio()).append("') ");
			} else {
				strBuffquery.append("WHERE 1=1 ");
			}

			if (soloAbierto) {
				oParametros.add(Constants.STATUS_JUEGO_ABIERTO);
				strBuffquery.append("AND a.id_status_juego = ? ");
			}

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
				String values = "";
				strBuffquery.append("AND ");

				if (porCentro) {
					values = getValues("select id_usuario from usuario where id_rol=?", Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
					values = getValues("select date_format(max(xx.fecha_sis),'%Y%m%d%H%i%s') from usuario_juego_equipo xx where xx.id_usuario=? and j.id_usuario=xx.id_usuario and xx.id_juego_equipo=f.id_juego_equipo or xx.id_usuario IN (".concat(values).concat(") group by xx.id_juego_equipo"),
							oUsuarioTO.getIdUsuario());
					strBuffquery.append("date_format(j.fecha_sis,'%Y%m%d%H%i%s') in (".concat(values).concat(") "));
				} else {
					values = getValues("select id_usuario from usuario where id_rol=?", Constants.ROL_ADMINISTRADOR);
					String values1;
					if (oJuegoTO != null && oJuegoTO.getIdStatusJuego() != null && !oJuegoTO.getIdStatusJuego().trim().equals("0")) {
						// values1 =
						// getValues("select yy.id_juego_equipo from usuario_juego_equipo yy, juego_equipo je, juego ju where yy.id_juego_equipo=je.id_juego_equipo and je.id_juego=ju.id_juego and ju.id_status_juego=? and yy.id_usuario=?",oJuegoTO.getIdStatusJuego(),oUsuarioTO.getIdUsuario());
						values1 = "select yy.id_juego_equipo from usuario_juego_equipo yy, juego_equipo je, juego ju where yy.id_juego_equipo=je.id_juego_equipo and je.id_juego=ju.id_juego and ju.id_status_juego=" + oJuegoTO.getIdStatusJuego() + " and yy.id_usuario=" + oUsuarioTO.getIdUsuario();
					} else {
						values1 = "select id_juego_equipo from usuario_juego_equipo yy where yy.id_usuario=" + oUsuarioTO.getIdUsuario();
					}

					oParametros.add(oUsuarioTO.getIdUsuario());
					strBuffquery.append("date_format(j.fecha_sis,'%Y%m%d%H%i%s') in (select date_format(max(xx.fecha_sis),'%Y%m%d%H%i%s') from usuario_juego_equipo xx where xx.id_usuario=? and j.id_usuario=xx.id_usuario and xx.id_juego_equipo=f.id_juego_equipo or xx.id_usuario IN (".concat(values)
							.concat(") and xx.id_juego_equipo=f.id_juego_equipo and f.id_juego_equipo not in (").concat(values1).concat(") group by xx.id_juego_equipo) "));
				}
			} else {
				strBuffquery.append("AND ");

				String values = "select usu.id_supervisor from usuario usu where usu.id_usuario=" + oUsuarioTO.getIdUsuario();

				values = "select date_format(max(xx.fecha_sis),'%Y%m%d%H%i%s') from usuario_juego_equipo xx where xx.id_usuario=(".concat(values).concat(") group by xx.id_juego_equipo");

				strBuffquery.append(" date_format(j.fecha_sis,'%Y%m%d%H%i%s') in (").append(values).append(") ");

				if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
					oParametros.add(Constants.ROL_ADMINISTRADOR);
					strBuffquery.append("AND j.id_usuario=? ");
				}
			}
			strBuffquery.append("AND ( g.id_equipo!='EMP' OR g.id_equipo='EMP' && k.empate=1 ) ");

			if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && !oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				oParametros.add(Constants.STATUS_JUEGO_BORRADOR);
				strBuffquery.append("AND a.id_status_juego != ? ");
			}

			if (oJuegoTO != null && !Constants.isNull(oJuegoTO.getIdDeporte())) {
				oParametros.add(oJuegoTO.getIdDeporte());
				strBuffquery.append("AND a.id_deporte = ? ");
			}

			// if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA))) {
			// oParametros.add(Constants.STATUS_JUEGO_ABIERTO);
			// strBuffquery.append("AND a.id_status_juego = ? AND l.id_status_juego IS NULL ");
			// }

			if (oJuegoTO != null && oJuegoTO.getFechaIni() != null && !oJuegoTO.getFechaIni().trim().equals("")) {
				oParametros.add(Constants.getFechaFormatoSQL(oJuegoTO.getFechaIni(), true));
				oParametros.add(Constants.getFechaFormatoSQL(oJuegoTO.getFechaIni(), false));
				strBuffquery.append("AND a.fecha_ini>= ? AND a.fecha_ini<= ? ");
			}
			if (oJuegoTO != null && oJuegoTO.getIdStatusJuego() != null && !oJuegoTO.getIdStatusJuego().trim().equals("")) {
				if (!oJuegoTO.getIdStatusJuego().equals("0")) {
					oParametros.add(oJuegoTO.getIdStatusJuego());
					strBuffquery.append("AND a.id_status_juego=? ");
				}
			}

			strBuffquery.append("ORDER BY DATE_FORMAT(a.fecha_ini,'%Y%m%d'),e.desc_liga,a.fecha_ini,a.id_juego,j.id_juego_equipo  ");

			log.info("Ejecutando " + strBuffquery.toString());

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (!oCachedRowSet.next()) {
				if (oUsuarioTO != null && oUsuarioTO.getIdRol() != null && !oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR) && !oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
					oParametros.set(1, oUsuarioTO.getIdSupervisor());
					oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				}
			}
			oCachedRowSet.beforeFirst();
			ListaJuegoTO oListaJuegoTO;
			while (oCachedRowSet.next()) {
				oListaJuegoTO = new ListaJuegoTO();
				oListaJuegoTO.setIdJuego(oCachedRowSet.getString("id_juego"));
				oListaJuegoTO.setDescLiga(oCachedRowSet.getString("desc_liga"));
				oListaJuegoTO.setIniciales(oCachedRowSet.getString("iniciales"));
				oListaJuegoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
				oListaJuegoTO.setIdDeporte(oCachedRowSet.getString("id_deporte"));
				oListaJuegoTO.setReferencia(oCachedRowSet.getString("referencia"));
				oListaJuegoTO.setAbreviatura(oCachedRowSet.getString("abreviatura"));
				oListaJuegoTO.setDescEquipo(oCachedRowSet.getString("desc_equipo"));
				oListaJuegoTO.setNombreLanzador(oCachedRowSet.getString("nombre_lanzador"));
				oListaJuegoTO.setMoneyLine(oCachedRowSet.getString("money_line"));
				oListaJuegoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
				oListaJuegoTO.setSpread(oCachedRowSet.getString("spread"));
				oListaJuegoTO.setSpreadLogro(oCachedRowSet.getString("spread_logro"));
				oListaJuegoTO.setTotal(oCachedRowSet.getString("total"));
				oListaJuegoTO.setTotalLogro(oCachedRowSet.getString("total_logro"));
				oListaJuegoTO.setPuntos(oCachedRowSet.getString("puntos"));
				oListaJuegoTO.setDia(oCachedRowSet.getString("dia"));
				oListaJuegoTO.setHora(oCachedRowSet.getString("hora"));
				oListaJuegoTO.setStatusReal(oCachedRowSet.getString("status_real"));
				oListaJuegoTO.setIdJuegoPadre(oCachedRowSet.getString("id_juego_padre"));
				oListaJuegoTO.setEmpate(oCachedRowSet.getString("empate"));
				oListaJuegoTO.setSuperSpread(oCachedRowSet.getString("super_spread"));
				oListaJuegoTO.setSuperSpreadLogro(oCachedRowSet.getString("super_spread_logro"));
				oListaJuegoTO.setDominioActual(oCachedRowSet.getString("dominio_actual"));
				oListaJuegoTO.setUsuarioTotaliza(oCachedRowSet.getString("usuario_totaliza"));
				lista.add(oListaJuegoTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error en la ejecucion de JuegoDAO.listaJuegoConEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

	/**
	 *
	 * Consulta un registro en la tabla juego
	 *
	 */
	public ArrayList consultarResultadosDAO(String fecha) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.consultarResultadosDAO");
		ArrayList oParametros = new ArrayList();
		ArrayList lista = new ArrayList();
		ResultadosBean resultado = null;
		boolean found = false;
		String idDeporte = "";
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("select date_format(a.fecha_ini,'%W %d/%m/%Y %h:%i %p') As fecha, a.id_juego As juego, ");
			strBuffquery.append("c.desc_deporte As deporte, d.desc_equipo As equipo, b.puntos As puntos, a.id_juego_padre As juego_padre, ");
			strBuffquery.append("a.id_deporte ");
			strBuffquery.append("from juego a, juego_equipo b, deporte c, equipo d ");
			strBuffquery.append("where a.id_juego=b.id_juego ");
			strBuffquery.append("and a.id_deporte=c.id_deporte ");
			strBuffquery.append("and b.id_equipo=d.id_equipo ");
			strBuffquery.append("and a.id_status_juego=5 ");
			if (fecha == null) {
				strBuffquery.append("and date_format(a.fecha_ini,'%Y%m%d')=(select date_format(max(x.fecha_ini),'%Y%m%d')  from juego x where x.id_status_juego=5) ");
			} else {
				strBuffquery.append("and date_format(a.fecha_ini,'%Y-%m-%d')='").append(Constants.getFechaCortaSQL(fecha)).append("' ");
			}
			strBuffquery.append("order by c.id_deporte,a.fecha_ini,b.id_juego_equipo ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			while (oCachedRowSet.next()) {
				idDeporte = oCachedRowSet.getString("id_deporte");
				if (idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_0) || idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) || idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
					continue;
				}

				resultado = new ResultadosBean();
				resultado.setFecha(oCachedRowSet.getString("fecha"));
				resultado.setJuego(oCachedRowSet.getString("juego"));
				resultado.setDeporte(oCachedRowSet.getString("deporte"));
				resultado.setEquipo(oCachedRowSet.getString("equipo"));
				resultado.setJuegoPadre(oCachedRowSet.getString("juego_padre"));
				resultado.setPuntos(oCachedRowSet.getString("puntos"));

				lista.add(resultado);
			}

			oCachedRowSet.beforeFirst();
			while (oCachedRowSet.next()) {
				idDeporte = oCachedRowSet.getString("id_deporte");
				if (!(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1) || idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_0) || idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) || idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD))) {
					continue;
				}
				for (int i = 0; i < lista.size(); i++) {
					resultado = (ResultadosBean) lista.get(i);
					if (resultado.getJuego().equals(oCachedRowSet.getString("juego_padre")) && resultado.getEquipo().equals(oCachedRowSet.getString("equipo"))) {
						found = true;
						break;
					}
					found = false;
				}
				if (!found) {
					resultado = new ResultadosBean();
					resultado.setFecha(oCachedRowSet.getString("fecha"));
					resultado.setJuego(oCachedRowSet.getString("juego"));
					resultado.setDeporte(oCachedRowSet.getString("deporte"));
					resultado.setEquipo(oCachedRowSet.getString("equipo"));
					resultado.setJuegoPadre(oCachedRowSet.getString("juego_padre"));
				}
				if (idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_1)) {
					resultado.setPuntos1er(oCachedRowSet.getString("puntos"));
				} else if (idDeporte.equals(Constants.ID_EQUIPO_BEISBOL_5) || idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL_MITAD) || idDeporte.equals(Constants.ID_EQUIPO_SOCCER_MITAD)) {
					resultado.setPuntos5to(oCachedRowSet.getString("puntos"));
				}

				if (!found) {
					lista.add(resultado);
				}
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.consultarResultadosDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return lista;
	}

	/**
	 *
	 * Actualiza un registro en la tabla juego
	 *
	 */
	public int actualizarFechaHoraJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.actualizarFechaHoraJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			// iniciar parametros
			oJuegoTO.setFechaSis(Constants.getFechaLargaSQL());

			oParametros.add(oJuegoTO.getFechaIni());
			oParametros.add(oJuegoTO.getIdJuego()); // primary key

			strBuffquery.append("UPDATE juego SET fecha_ini=? ");
			strBuffquery.append("WHERE  id_juego = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.actualizarFechaHoraJuegoDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}

	/**
	 *
	 * Consulta un registro en la tabla juego
	 *
	 */
	public CachedRowSet cargarListaJuegoApuestaCompletoDAO(UsuarioIF oUsuarioTO, boolean isOnlyTotal) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.cargarJuegoCompletoDAO");
		ArrayList oParametros = new ArrayList();
		ArrayList lista = new ArrayList();
		RegisterGameActionForm oJuegoTO = null;
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();
			oParametros.add(oUsuarioTO.getIdSupervisor()); // para el usuario supervisor
			oParametros.add(oUsuarioTO.getIdSupervisor()); // para el usuario supervisor
			oParametros.add(Constants.STATUS_JUEGO_ABIERTO);

			strBuffquery.setLength(0);
			strBuffquery.append("SELECT ");
			if (isOnlyTotal) {
				strBuffquery.append("count(a.id_juego) ");
			} else {
				strBuffquery.append("a.id_juego,a.fecha_sis,a.fecha_ini,a.fecha_fin,a.minutos_cierre,a.id_campeonato ");
				strBuffquery.append(",a.id_status_juego,a.id_usuario,a.id_deporte,a.id_liga,e.desc_liga ");
				strBuffquery.append(",e.iniciales,d.desc_status_juego,b.desc_campeonato,a.spread_activo ");
				strBuffquery.append(",a.total_activo,a.money_activo,a.id_juego_padre,j.spread,j.super_spread,j.total ");
				strBuffquery.append(",j.id_usuario As id_usuario_detalle,j.id_usuario_juego_equipo,f.id_juego_equipo ");
				strBuffquery.append(",f.referencia,f.referencia_runline,f.referencia_super_runline,f.referencia_ab,f.id_equipo,g.abreviatura ");
				strBuffquery.append(",g.desc_equipo,i.nombre_lanzador,h.id_lanzador,j.spread_logro,j.super_spread_logro,j.total_logro ");
				strBuffquery.append(",j.money_line ");
			}
			strBuffquery.append("FROM juego a ");
			strBuffquery.append("LEFT OUTER JOIN campeonato b ON a.id_campeonato=b.id_campeonato ");
			strBuffquery.append("LEFT OUTER JOIN status_juego d ON a.id_status_juego=d.id_status_juego ");
			strBuffquery.append("LEFT OUTER JOIN liga e ON a.id_liga=e.id_liga ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo f ON a.id_juego=f.id_juego ");
			strBuffquery.append("LEFT OUTER JOIN equipo g ON f.id_equipo=g.id_equipo ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo_lanzador h ON f.id_juego_equipo=h.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN lanzador i ON h.id_lanzador=i.id_lanzador ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego_equipo j ON f.id_juego_equipo=j.id_juego_equipo AND j.id_usuario = ? AND j.desactivado=0  ");
			strBuffquery.append("LEFT OUTER JOIN deporte k ON a.id_deporte=k.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego m ON a.id_juego=m.id_juego and m.id_usuario=? ");
			strBuffquery.append("WHERE j.desactivado=0 ");
			strBuffquery.append("AND ( g.id_equipo!='EMP' OR g.id_equipo='EMP' && k.empate=1 ) ");
			strBuffquery.append("AND a.id_status_juego=? ");
			strBuffquery.append("AND now()<date_sub(a.fecha_ini,interval (a.minutos_cierre) minute) ");
			if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
				strBuffquery.append("AND m.id_status_juego IS NULL ");
			}

			if (!isOnlyTotal) {
				// strBuffquery.append("ORDER BY SUBSTRING(a.fecha_ini,1,10),a.id_deporte,a.fecha_ini,a.id_juego,f.id_juego_equipo ");
				strBuffquery.append("ORDER BY SUBSTRING(a.fecha_ini,1,10),e.desc_liga,a.fecha_ini,a.id_juego,f.id_juego_equipo ");
			}

			// oEjecutorSql.printQuery(strBuffquery.toString(), oParametros);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.cargarJuegoCompletoDAO");
			e.printStackTrace();
			throw e;
		}

		// return lista;
		return oCachedRowSet;
	}
	/**
	 *
	 * Consulta un registro en la tabla juego
	 *
	 */
	public CachedRowSet cargarListaJuegoApuestaCompletoHoyDAO(UsuarioIF oUsuarioTO, boolean isOnlyTotal) throws Exception {
		log.info("Iniciando ejecucion de JuegoDAO.cargarJuegoCompletoDAO");
		ArrayList oParametros = new ArrayList();
		ArrayList lista = new ArrayList();
		RegisterGameActionForm oJuegoTO = null;
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();
			
			oCachedRowSet = oEjecutorSql.ejecutaQuery("select now() as ahora",null);
			
			
			oCachedRowSet.next();
			log.info("NOWWWW "+oCachedRowSet.getString("ahora"));
			
			
			oParametros.add(oUsuarioTO.getIdSupervisor()); // para el usuario supervisor
			oParametros.add(oUsuarioTO.getIdSupervisor()); // para el usuario supervisor
			oParametros.add(Constants.STATUS_JUEGO_ABIERTO);

			strBuffquery.setLength(0);
			strBuffquery.append("SELECT ");
			if (isOnlyTotal) {
				strBuffquery.append("count(a.id_juego) ");
			} else {
				strBuffquery.append("a.id_juego,a.fecha_sis,a.fecha_ini,a.fecha_fin,a.minutos_cierre,a.id_campeonato ");
				strBuffquery.append(",a.id_status_juego,a.id_usuario,a.id_deporte,a.id_liga,e.desc_liga ");
				strBuffquery.append(",e.iniciales,d.desc_status_juego,b.desc_campeonato,a.spread_activo ");
				strBuffquery.append(",a.total_activo,a.money_activo,a.id_juego_padre,j.spread,j.super_spread,j.total ");
				strBuffquery.append(",j.id_usuario As id_usuario_detalle,j.id_usuario_juego_equipo,f.id_juego_equipo ");
				strBuffquery.append(",f.referencia,f.referencia_runline,f.referencia_super_runline,f.referencia_ab,f.id_equipo,g.abreviatura ");
				strBuffquery.append(",g.desc_equipo,i.nombre_lanzador,h.id_lanzador,j.spread_logro,j.super_spread_logro,j.total_logro ");
				strBuffquery.append(",j.money_line ");
			}
			strBuffquery.append("FROM juego a ");
			strBuffquery.append("LEFT OUTER JOIN campeonato b ON a.id_campeonato=b.id_campeonato ");
			strBuffquery.append("LEFT OUTER JOIN status_juego d ON a.id_status_juego=d.id_status_juego ");
			strBuffquery.append("LEFT OUTER JOIN liga e ON a.id_liga=e.id_liga ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo f ON a.id_juego=f.id_juego ");
			strBuffquery.append("LEFT OUTER JOIN equipo g ON f.id_equipo=g.id_equipo ");
			strBuffquery.append("LEFT OUTER JOIN juego_equipo_lanzador h ON f.id_juego_equipo=h.id_juego_equipo ");
			strBuffquery.append("LEFT OUTER JOIN lanzador i ON h.id_lanzador=i.id_lanzador ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego_equipo j ON f.id_juego_equipo=j.id_juego_equipo AND j.id_usuario = ? AND j.desactivado=0  ");
			strBuffquery.append("LEFT OUTER JOIN deporte k ON a.id_deporte=k.id_deporte ");
			strBuffquery.append("LEFT OUTER JOIN usuario_juego m ON a.id_juego=m.id_juego and m.id_usuario=? ");
			strBuffquery.append("WHERE j.desactivado=0 ");
			strBuffquery.append("AND ( g.id_equipo!='EMP' OR g.id_equipo='EMP' && k.empate=1 ) ");
			strBuffquery.append("AND a.id_status_juego=? ");
			strBuffquery.append("AND now()<date_sub(a.fecha_ini,interval (a.minutos_cierre) minute) ");
			strBuffquery.append("AND DATE(a.fecha_ini) = DATE(curdate())");
			if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA) || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR)) {
				strBuffquery.append("AND m.id_status_juego IS NULL ");
			}

			if (!isOnlyTotal) {
				// strBuffquery.append("ORDER BY SUBSTRING(a.fecha_ini,1,10),a.id_deporte,a.fecha_ini,a.id_juego,f.id_juego_equipo ");
				strBuffquery.append("ORDER BY SUBSTRING(a.fecha_ini,1,10),e.desc_liga,a.fecha_ini,a.id_juego,f.id_juego_equipo ");
			}

			// oEjecutorSql.printQuery(strBuffquery.toString(), oParametros);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			
			

		} catch (Exception e) {
			log.info("Error en la ejecucion de JuegoDAO.cargarJuegoCompletoDAO");
			e.printStackTrace();
			throw e;
		}

		// return lista;
		return oCachedRowSet;
	}


}
