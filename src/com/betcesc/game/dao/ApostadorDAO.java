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

import com.betcesc.game.interfaces.ApostadorIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla apostador.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla apostador
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class ApostadorDAO {
	private static final Log log = LogFactory.getLog(ApostadorDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public ApostadorDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla apostador
	 * 
	 */
	public int insertarApostadorDAO(ApostadorIF oApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApostadorDAO.insertarApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oApostadorTO.getIdApostador());
			oParametros.add(oApostadorTO.getNombreApostador());
			oParametros.add(oApostadorTO.getTelefono());
			oParametros.add(oApostadorTO.getCorreo());
			oParametros.add(oApostadorTO.getDireccion());

			strBuffquery.append("INSERT INTO apostador VALUES (?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de ApostadorDAO.insertarApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla apostador
	 * 
	 */
	public int actualizarApostadorDAO(ApostadorIF oApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApostadorDAO.actualizarApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oApostadorTO.getNombreApostador());
			oParametros.add(oApostadorTO.getTelefono());
			oParametros.add(oApostadorTO.getCorreo());
			oParametros.add(oApostadorTO.getDireccion());
			oParametros.add(oApostadorTO.getIdApostador()); // primary key

			strBuffquery.append("UPDATE apostador SET ");
			strBuffquery.append("nombre_apostador=? , telefono=?, correo=?, ");
			strBuffquery.append("direccion=?  ");
			strBuffquery.append("WHERE  id_apostador = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApostadorDAO.actualizarApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla apostador
	 * 
	 */
	public int eliminarApostadorDAO(ApostadorIF oApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApostadorDAO.eliminarApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oApostadorTO.getIdApostador());
			strBuffquery.append("DELETE FROM apostador  WHERE  (id_apostador = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApostadorDAO.eliminarApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla apostador
	 * 
	 */
	public CachedRowSet listaApostadorDAO() throws Exception {
		log.info("Iniciando ejecucion de ApostadorDAO.listaApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_apostador, UPPER(nombre_apostador) ");
			strBuffquery.append("FROM apostador ");
			strBuffquery.append("ORDER BY UPPER(nombre_apostador) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ApostadorDAO.listaApostadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla apostador
	 * 
	 */
	public boolean cargarApostadorDAO(ApostadorIF oApostadorTO) throws Exception {
		log.info("Iniciando ejecucion de ApostadorDAO.consultarApostadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_apostador, nombre_apostador, telefono, ");
			strBuffquery.append("correo, direccion ");
			strBuffquery.append("FROM apostador ");

			boolean procesar = false;
			if (!oApostadorTO.getIdApostador().equalsIgnoreCase("")) {
				oParametros.add(oApostadorTO.getIdApostador());
				strBuffquery.append(" WHERE id_apostador = ? ");
				procesar = true;
			} else if (!oApostadorTO.getNombreApostador().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(nombre_apostador) LIKE '%");
				strBuffquery.append(oApostadorTO.getNombreApostador().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oApostadorTO.setIdApostador(oCachedRowSet.getString("id_apostador"));
					oApostadorTO.setNombreApostador(oCachedRowSet.getString("nombre_apostador"));
					oApostadorTO.setTelefono(oCachedRowSet.getString("telefono"));
					oApostadorTO.setCorreo(oCachedRowSet.getString("correo"));
					oApostadorTO.setDireccion(oCachedRowSet.getString("direccion"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de ApostadorDAO.consultarApostadorDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

}
