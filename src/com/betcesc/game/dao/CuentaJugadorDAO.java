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
import com.betcesc.game.interfaces.CuentaJugadorIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla cuenta_jugador.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla cuenta_jugador
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class CuentaJugadorDAO {
	private static final Log log = LogFactory.getLog(CuentaJugadorDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public CuentaJugadorDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public CuentaJugadorDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
	}

	/**
	 * 
	 * Inserta un registro en la tabla cuenta_jugador
	 * 
	 */
	public int insertarCuentaJugadorDAO(CuentaJugadorIF oCuentaJugadorTO) throws Exception {
		log.info("Iniciando ejecucion de CuentaJugadorDAO.insertarCuentaJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			// iniciar parametros
			oCuentaJugadorTO.setIdCuenta(oCuentaJugadorTO.getIdCuenta() == null || oCuentaJugadorTO.getIdCuenta().trim().equals("") ? "0" : oCuentaJugadorTO.getIdCuenta());
			oCuentaJugadorTO.setFechaSis(Constants.getFechaLargaSQL());
			oCuentaJugadorTO.setTipo(oCuentaJugadorTO.getTipo() == null || oCuentaJugadorTO.getTipo().trim().equals("") ? Constants.CUENTA_JUGADOR_TIPO_SIMPLE : oCuentaJugadorTO.getTipo());

			oParametros.add(oCuentaJugadorTO.getIdCuenta());
			oParametros.add(oCuentaJugadorTO.getFechaSis());
			oParametros.add(oCuentaJugadorTO.getReferencia());
			oParametros.add(oCuentaJugadorTO.getOperacion());
			oParametros.add(oCuentaJugadorTO.getMonto());
			oParametros.add(oCuentaJugadorTO.getConcepto());
			oParametros.add(oCuentaJugadorTO.getIdJugador());
			oParametros.add(oCuentaJugadorTO.getIdUsuario());
			oParametros.add(oCuentaJugadorTO.getTipo());

			strBuffquery.append("INSERT INTO cuenta_jugador VALUES (?,?,?,?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de CuentaJugadorDAO.insertarCuentaJugadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla cuenta_jugador
	 * 
	 */
	public int actualizarCuentaJugadorDAO(CuentaJugadorIF oCuentaJugadorTO) throws Exception {
		log.info("Iniciando ejecucion de CuentaJugadorDAO.actualizarCuentaJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oCuentaJugadorTO.getFechaSis());
			oParametros.add(oCuentaJugadorTO.getReferencia());
			oParametros.add(oCuentaJugadorTO.getOperacion());
			oParametros.add(oCuentaJugadorTO.getMonto());
			oParametros.add(oCuentaJugadorTO.getConcepto());
			oParametros.add(oCuentaJugadorTO.getIdJugador());
			oParametros.add(oCuentaJugadorTO.getIdUsuario());
			oParametros.add(oCuentaJugadorTO.getTipo());
			oParametros.add(oCuentaJugadorTO.getIdCuenta()); // primary key

			strBuffquery.append("UPDATE cuenta_jugador SET ");
			strBuffquery.append("fecha_sis=? , referencia=?, operacion=?, ");
			strBuffquery.append("monto=? , concepto=?, id_jugador=?, id_usuario=?, tipo=? ");
			strBuffquery.append("WHERE  id_cuenta = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CuentaJugadorDAO.actualizarCuentaJugadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla cuenta_jugador
	 * 
	 */
	public int eliminarCuentaJugadorDAO(CuentaJugadorIF oCuentaJugadorTO) throws Exception {
		log.info("Iniciando ejecucion de CuentaJugadorDAO.eliminarCuentaJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oCuentaJugadorTO.getIdCuenta());
			strBuffquery.append("DELETE FROM cuenta_jugador  WHERE  (id_cuenta = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CuentaJugadorDAO.eliminarCuentaJugadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla cuenta_jugador
	 * 
	 */
	public CachedRowSet listaCuentaJugadorDAO(CuentaJugadorIF oCuentaJugadorTO) throws Exception {
		log.info("Iniciando ejecucion de CuentaJugadorDAO.listaCuentaJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			strBuffquery.setLength(0);
			strBuffquery.append("SET @acumulado:=0; ");
			oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			oParametros.add(oCuentaJugadorTO.getIdJugador());

			strBuffquery.setLength(0);
			strBuffquery.append("SELECT  a.id_cuenta, a.fecha_sis, a.referencia, ");
			strBuffquery.append("a.operacion, a.monto, a.concepto, a.id_jugador, ");
			strBuffquery.append("a.id_usuario, a.tipo, b.usuario, c.usuario AS nombreJugador, ");
			strBuffquery.append("DATE_FORMAT(a.fecha_sis,'%d/%m/%Y') dia, DATE_FORMAT(a.fecha_sis,'%h:%i%p') hora, ");
			strBuffquery.append("format(if(operacion='I',a.monto,''),2) ingreso, format(if(operacion='E',a.monto,''),2) egreso, ");
			strBuffquery.append("format(@acumulado:=@acumulado+if(a.operacion='I',1,-1)*a.monto,2) As acumulado ");
			strBuffquery.append("FROM cuenta_jugador a, usuario b, usuario c ");
			strBuffquery.append("WHERE a.id_usuario=b.id_usuario ");
			strBuffquery.append("AND a.id_jugador=c.id_usuario ");
			strBuffquery.append("AND a.id_jugador=? ");
			strBuffquery.append("ORDER BY UPPER(a.fecha_sis) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CuentaJugadorDAO.listaCuentaJugadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla cuenta_jugador
	 * 
	 */
	public boolean cargarCuentaJugadorDAO(CuentaJugadorIF oCuentaJugadorTO) throws Exception {
		log.info("Iniciando ejecucion de CuentaJugadorDAO.consultarCuentaJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_cuenta, fecha_sis, referencia, ");
			strBuffquery.append("operacion, monto, concepto, id_jugador, ");
			strBuffquery.append("id_usuario, tipo ");
			strBuffquery.append("FROM cuenta_jugador ");

			boolean procesar = false;
			if (!oCuentaJugadorTO.getIdCuenta().equalsIgnoreCase("")) {
				oParametros.add(oCuentaJugadorTO.getIdCuenta());
				strBuffquery.append(" WHERE id_cuenta = ? ");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oCuentaJugadorTO.setIdCuenta(oCachedRowSet.getString("id_cuenta"));
					oCuentaJugadorTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
					oCuentaJugadorTO.setReferencia(oCachedRowSet.getString("referencia"));
					oCuentaJugadorTO.setOperacion(oCachedRowSet.getString("operacion"));
					oCuentaJugadorTO.setMonto(oCachedRowSet.getString("monto"));
					oCuentaJugadorTO.setConcepto(oCachedRowSet.getString("concepto"));
					oCuentaJugadorTO.setIdJugador(oCachedRowSet.getString("id_jugador"));
					oCuentaJugadorTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
					oCuentaJugadorTO.setTipo(oCachedRowSet.getString("tipo"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de CuentaJugadorDAO.consultarCuentaJugadorDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta un registro en la tabla cuenta_jugador
	 * 
	 */
	public boolean cargarCuentaJugadorPorReferenciaJugadaDAO(CuentaJugadorIF oCuentaJugadorTO) throws Exception { 
		log.info("Iniciando ejecucion de CuentaJugadorDAO.consultarCuentaJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_cuenta, fecha_sis, referencia, ");
			strBuffquery.append("operacion, monto, concepto, id_jugador, ");
			strBuffquery.append("id_usuario, tipo ");
			strBuffquery.append("FROM cuenta_jugador ");

			boolean procesar = false;
			oParametros.add(Constants.OPERACION_INGRESO);
			oParametros.add(oCuentaJugadorTO.getReferencia());
			oParametros.add(Constants.CUENTA_JUGADOR_TIPO_PREMIO);
			strBuffquery.append(" WHERE operacion = ? AND referencia = ? AND tipo = ? ");
			procesar = true;
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			if (oCachedRowSet.size() > 1) {
				log.error("existe mas de un registro relacionado a esta jugada");
			}
			if (oCachedRowSet.next()) {
				oCuentaJugadorTO.setIdCuenta(oCachedRowSet.getString("id_cuenta"));
				oCuentaJugadorTO.setFechaSis(oCachedRowSet.getString("fecha_sis"));
				oCuentaJugadorTO.setReferencia(oCachedRowSet.getString("referencia"));
				oCuentaJugadorTO.setOperacion(oCachedRowSet.getString("operacion"));
				oCuentaJugadorTO.setMonto(oCachedRowSet.getString("monto"));
				oCuentaJugadorTO.setConcepto(oCachedRowSet.getString("concepto"));
				oCuentaJugadorTO.setIdJugador(oCachedRowSet.getString("id_jugador"));
				oCuentaJugadorTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
				oCuentaJugadorTO.setTipo(oCachedRowSet.getString("tipo"));
				retorno = true;
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de CuentaJugadorDAO.consultarCuentaJugadorDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
