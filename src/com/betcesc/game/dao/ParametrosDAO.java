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

import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.to.ParametrosTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla parametros.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla parametros
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class ParametrosDAO {
	private static final Log log = LogFactory.getLog(ParametrosDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public ParametrosDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla parametros
	 * 
	 */
	public int insertarParametrosDAO(ParametrosIF oParametrosTO) throws Exception {
		log.info("Iniciando ejecucion de ParametrosDAO.insertarParametrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oParametros.add(oParametrosTO.getNombre());
			oParametros.add(oParametrosTO.getValor());

			strBuffquery.append("INSERT INTO parametros VALUES (?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			
		} catch (Exception e) {
			numRegAct = actualizarParametrosDAO(oParametrosTO);
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla parametros
	 * 
	 */
	public int actualizarParametrosDAO(ParametrosIF oParametrosTO) throws Exception {
		log.info("Iniciando ejecucion de ParametrosDAO.actualizarParametrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oParametros.add(oParametrosTO.getValor());
			oParametros.add(oParametrosTO.getNombre()); // primary key

			strBuffquery.append("UPDATE parametros SET valor=? WHERE  nombre = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ParametrosDAO.actualizarParametrosDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla parametros
	 * 
	 */
	public int eliminarParametrosDAO(ParametrosIF oParametrosTO) throws Exception {
		log.info("Iniciando ejecucion de ParametrosDAO.eliminarParametrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oParametrosTO.getNombre());
			strBuffquery.append("DELETE FROM parametros  WHERE  id_parametros = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ParametrosDAO.eliminarParametrosDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla parametros
	 * 
	 */
	public CachedRowSet listaParametrosDAO() throws Exception {
		log.info("Iniciando ejecucion de ParametrosDAO.listaParametrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT nombre, valor ");
			strBuffquery.append("FROM parametros ");
			strBuffquery.append("ORDER BY UPPER(nombre) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de ParametrosDAO.listaParametrosDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla parametros
	 * 
	 */
	public boolean cargarParametrosDAO(ParametrosIF oParametrosTO) throws Exception {
		log.info("Iniciando ejecucion de ParametrosDAO.consultarParametrosDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  nombre, valor ");
			strBuffquery.append("FROM parametros ");

			boolean procesar = false;
			if (!oParametrosTO.getNombre().equalsIgnoreCase("")) {
				oParametros.add(oParametrosTO.getNombre());
				strBuffquery.append(" WHERE nombre = ? ");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oParametrosTO.setNombre(oCachedRowSet.getString("nombre"));
					oParametrosTO.setValor(oCachedRowSet.getString("valor"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de ParametrosDAO.consultarParametrosDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;

	}
	

	/**
	 * 
	 * Consulta una lista de registros en la tabla parametros
	 * 
	 */
	public ArrayList listParametrosEmpateDAO() throws Exception {
		log.info("Iniciando ejecucion de ParametrosDAO.listParametrosEmpateDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		ParametrosIF oParametrosTO = null;
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT nombre, valor FROM parametros");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			while(oCachedRowSet.next()) {
				oParametrosTO = new ParametrosTO();
				oParametrosTO.setNombre(oCachedRowSet.getString("nombre"));
				oParametrosTO.setValor(oCachedRowSet.getString("valor"));
				lista.add(oParametrosTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de ParametrosDAO.listParametrosEmpateDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	
	
	

}
