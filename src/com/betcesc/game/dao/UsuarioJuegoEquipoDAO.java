/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.JuegoIF;
import com.betcesc.game.interfaces.UsuarioJuegoEquipoIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla usuario_juego_equipo.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla usuario_juego_equipo
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class UsuarioJuegoEquipoDAO {
	private static final Log log = LogFactory.getLog(UsuarioJuegoEquipoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public UsuarioJuegoEquipoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}
	public UsuarioJuegoEquipoDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
		
	}

	/**
	 * 
	 * Inserta un registro en la tabla usuario_juego_equipo
	 * 
	 */
	public int insertarUsuarioJuegoEquipoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.insertarUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oUsuarioJuegoEquipoTO.setIdUsuarioJuegoEquipo(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo()==null?"0":oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			oUsuarioJuegoEquipoTO.setDesactivado(Constants.isNull(oUsuarioJuegoEquipoTO.getDesactivado(), "0"));

			oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuario());
			oParametros.add(oUsuarioJuegoEquipoTO.getIdJuegoEquipo());
			oParametros.add(oUsuarioJuegoEquipoTO.getFechaSis());
			oParametros.add(oUsuarioJuegoEquipoTO.getSpread());
			oParametros.add(oUsuarioJuegoEquipoTO.getSpreadLogro());
			oParametros.add(oUsuarioJuegoEquipoTO.getTotal());
			oParametros.add(oUsuarioJuegoEquipoTO.getTotalLogro());
			oParametros.add(oUsuarioJuegoEquipoTO.getMoneyLine());
			oParametros.add(oUsuarioJuegoEquipoTO.getIdStatusJuego());
			oParametros.add(oUsuarioJuegoEquipoTO.getSuperSpread());
			oParametros.add(oUsuarioJuegoEquipoTO.getSuperSpreadLogro());
			oParametros.add(oUsuarioJuegoEquipoTO.getDesactivado());

			strBuffquery.append("INSERT INTO usuario_juego_equipo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.insertarUsuarioJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla usuario_juego_equipo
	 * 
	 */
	public int actualizarUsuarioJuegoEquipoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.actualizarUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oUsuarioJuegoEquipoTO.setDesactivado(Constants.isNull(oUsuarioJuegoEquipoTO.getDesactivado(), "0"));

			oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuario());
			oParametros.add(oUsuarioJuegoEquipoTO.getIdJuegoEquipo());
			oParametros.add(oUsuarioJuegoEquipoTO.getFechaSis());
			oParametros.add(oUsuarioJuegoEquipoTO.getSpread());
			oParametros.add(oUsuarioJuegoEquipoTO.getSpreadLogro());
			oParametros.add(oUsuarioJuegoEquipoTO.getTotal());
			oParametros.add(oUsuarioJuegoEquipoTO.getTotalLogro());
			oParametros.add(oUsuarioJuegoEquipoTO.getMoneyLine());
			oParametros.add(oUsuarioJuegoEquipoTO.getIdStatusJuego());
			oParametros.add(oUsuarioJuegoEquipoTO.getSuperSpread());
			oParametros.add(oUsuarioJuegoEquipoTO.getSuperSpreadLogro());
			oParametros.add(oUsuarioJuegoEquipoTO.getDesactivado());
			oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo()); // primary
																				// key

			strBuffquery.append("UPDATE usuario_juego_equipo SET ");
			strBuffquery.append("id_usuario=? , id_juego_equipo=?, fecha_sis=?, ");
			strBuffquery.append("spread=? , spread_logro=?, total=?, ");
			strBuffquery.append("total_logro=? , money_line=?, id_status_juego=?, super_spread=?, super_spread_logro=?, ");
			strBuffquery.append("desactivado=? ");
			strBuffquery.append("WHERE  id_usuario_juego_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.actualizarUsuarioJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla usuario_juego_equipo
	 * 
	 */
	public int eliminarUsuarioJuegoEquipoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.eliminarUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo());
			strBuffquery.append("DELETE FROM usuario_juego_equipo  WHERE  (id_usuario_juego_equipo = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.eliminarUsuarioJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego_equipo
	 * 
	 */
	public CachedRowSet listaUsuarioJuegoEquipoDAO() throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.listaUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_usuario_juego_equipo, UPPER(id_usuario) ");
			strBuffquery.append("FROM usuario_juego_equipo ");
			strBuffquery.append("ORDER BY UPPER(id_usuario) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.listaUsuarioJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla usuario_juego_equipo
	 * 
	 */
	public boolean cargarUsuarioJuegoEquipoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.consultarUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT * FROM usuario_juego_equipo ");

			boolean procesar = false;
			if (!oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo().equalsIgnoreCase("")) {
				oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuarioJuegoEquipo());
				strBuffquery.append(" WHERE id_usuario_juego_equipo = ? ");
				procesar = true;
			} else if (!oUsuarioJuegoEquipoTO.getIdUsuario().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(id_usuario) LIKE '%");
				strBuffquery.append(oUsuarioJuegoEquipoTO.getIdUsuario().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					load(oCachedRowSet,oUsuarioJuegoEquipoTO);
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.consultarUsuarioJuegoEquipoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta un registro en la tabla usuario_juego_equipo
	 * 
	 */
	public boolean cargarUsuarioJuegoEquipoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO, String idJuegoPadre, String idEquipo) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.cargarUsuarioJuegoEquipoDAO(1,2,3)");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = null;
			
			oParametros.add(idJuegoPadre);
			oParametros.add(idEquipo);

			strBuffquery.append("SELECT id_usuario_juego_equipo FROM usuario_juego_equipo WHERE id_juego_equipo = ");
			strBuffquery.append("(select id_juego_equipo from juego_equipo where id_juego = "); 
			strBuffquery.append("(select id_juego from juego where id_juego_padre=?) AND id_equipo=?) ");			
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			if(oCachedRowSet.next()) {
				oUsuarioJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCachedRowSet.getString("id_usuario_juego_equipo"));
				retorno = cargarUsuarioJuegoEquipoDAO(oUsuarioJuegoEquipoTO);
			} else {
				oUsuarioJuegoEquipoTO = null;				
			}
			

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.cargarUsuarioJuegoEquipoDAO(1,2,3)");
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
	public CachedRowSet listaUsuarioJuegoEquipoPorJuegoDAO(JuegoIF oJuegoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.listaUsuarioJuegoEquipoPorJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(oJuegoTO.getIdJuego());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_usuario_juego_equipo, id_juego_equipo ");
			strBuffquery.append("FROM usuario_juego_equipo ");
			strBuffquery.append("WHERE id_juego_equipo in (select id_juego_equipo from juego_equipo where id_juego = ?) ");
			strBuffquery.append("ORDER BY id_usuario_juego_equipo ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.listaUsuarioJuegoEquipoPorJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}
	
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario_juego_equipo solo del administrador
	 * 
	 */
	public void listaLogrosAdministradorUsuarioJuegoEquipoPorJuegoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de listaLogrosAdministradorUsuarioJuegoEquipoPorJuegoDAO.listaUsuarioJuegoEquipoPorJuegoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(oUsuarioJuegoEquipoTO.getIdJuegoEquipo());
			oParametros.add(Constants.ID_USUARIO_ADMINISTRADOR);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT * FROM usuario_juego_equipo WHERE id_juego_equipo=? AND id_usuario=? ORDER BY fecha_sis DESC LIMIT 1");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			if(oCachedRowSet.next()) {
				load(oCachedRowSet,oUsuarioJuegoEquipoTO);
			}


		} catch (Exception e) {
			log.info("Error en la ejecucion de listaLogrosAdministradorUsuarioJuegoEquipoPorJuegoDAO.listaUsuarioJuegoEquipoPorJuegoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 
	 * Retorna el ultimo logro registrado para una jugada 
	 * 
	 */
	public void obtenerUltimoLogroRegistradoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoPorJuegoDAO.obtenerUltimoLogroRegistradoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(oUsuarioJuegoEquipoTO.getIdJuegoEquipo());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("select a.* from usuario_juego_equipo a, usuario_juego_equipo b ");
			strBuffquery.append("where b.id_usuario_juego_equipo=? ");
			strBuffquery.append("and a.id_juego_equipo=b.id_juego_equipo ");
			strBuffquery.append("and a.id_usuario=b.id_usuario ");
			strBuffquery.append("order by a.fecha_sis desc ");
			strBuffquery.append("limit 1 ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			if(oCachedRowSet.next()) {
				load(oCachedRowSet,oUsuarioJuegoEquipoTO);
			}


		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoPorJuegoDAO.obtenerUltimoLogroRegistradoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * 
	 * Cierra los logros anteriores abiertos por los administradores de taquilla,
	 * si es otro usuario, cierra solo sus logros anteriores
	 * 
	 */
	public int cerrarLogrosAbiertosUsuarioJuegoEquipoDAO(UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioJuegoEquipoDAO.cerrarLogrosAbiertosUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oParametros.add(oUsuarioJuegoEquipoTO.getIdJuegoEquipo());

			strBuffquery.append("UPDATE usuario_juego_equipo SET desactivado=1 WHERE id_juego_equipo=?");
			//if(!oUsuarioJuegoEquipoTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
				oParametros.add(oUsuarioJuegoEquipoTO.getIdUsuario());
				strBuffquery.append("AND id_usuario=?");
			//}

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioJuegoEquipoDAO.cerrarLogrosAbiertosUsuarioJuegoEquipoDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}
	
	
	/**
	 * 
	 * Retorna el ultimo logro registrado para una jugada 
	 * 
	 */
	public boolean isOpenGameUsuarioJuegoEquipoDAO(String idUsuario, String idJuego) throws Exception {
		log.info("Iniciando ejecucion de isOpenGameUsuarioJuegoEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oParametros.add(idJuego);
			oParametros.add(idUsuario);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("select id_usuario_juego_equipo from usuario_juego_equipo ");
			strBuffquery.append("where id_juego_equipo in (select id_juego_equipo from juego_equipo where id_juego =  ?) and id_usuario=?");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			if(oCachedRowSet.size()>0) {
				return true;
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de isOpenGameUsuarioJuegoEquipoDAO");
			e.printStackTrace();
			throw e;
		}
		return false;
	}

	
	
	public void load(CachedRowSet oCachedRowSet, UsuarioJuegoEquipoIF oUsuarioJuegoEquipoTO) throws SQLException {
		oUsuarioJuegoEquipoTO.setIdUsuarioJuegoEquipo(oCachedRowSet.getString("id_usuario_juego_equipo"));
		oUsuarioJuegoEquipoTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
		oUsuarioJuegoEquipoTO.setIdJuegoEquipo(oCachedRowSet.getString("id_juego_equipo"));
		oUsuarioJuegoEquipoTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
		oUsuarioJuegoEquipoTO.setSpread(oCachedRowSet.getString("spread"));
		oUsuarioJuegoEquipoTO.setSpreadLogro(oCachedRowSet.getString("spread_logro"));
		oUsuarioJuegoEquipoTO.setTotal(oCachedRowSet.getString("total"));
		oUsuarioJuegoEquipoTO.setTotalLogro(oCachedRowSet.getString("total_logro"));
		oUsuarioJuegoEquipoTO.setMoneyLine(oCachedRowSet.getString("money_line"));
		oUsuarioJuegoEquipoTO.setIdStatusJuego(oCachedRowSet.getString("id_status_juego"));
		oUsuarioJuegoEquipoTO.setSuperSpread(oCachedRowSet.getString("super_spread"));
		oUsuarioJuegoEquipoTO.setSuperSpreadLogro(oCachedRowSet.getString("super_spread_logro"));
		oUsuarioJuegoEquipoTO.setDesactivado(oCachedRowSet.getString("desactivado"));
	}
	
}
