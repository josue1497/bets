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
import com.betcesc.game.interfaces.EquipoIF;
import com.betcesc.game.to.EquipoTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla equipo.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla equipo
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class EquipoDAO {
	private static final Log log = LogFactory.getLog(EquipoDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public EquipoDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla equipo
	 * 
	 */
	public int insertarEquipoDAO(EquipoIF oEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.insertarEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oEquipoTO.setIdEquipo(Constants.isNull(oEquipoTO.getIdEquipo(),"0"));

			if(Constants.isNull(oEquipoTO.getDescEquipo()) || Constants.isNull(oEquipoTO.getAbreviatura())) {
				throw new Exception("Los Datos deben estar completos");
			}

			oParametros.add(oEquipoTO.getIdEquipo());
			oParametros.add(oEquipoTO.getDescEquipo());
			oParametros.add(oEquipoTO.getAbreviatura().toUpperCase());
			oParametros.add(oEquipoTO.getDescCorta());

			strBuffquery.append("INSERT INTO equipo VALUES (?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			
			oEquipoTO.setIdEquipo(String.valueOf(oEjecutorSql.getGeneratedKey()));
		} catch (Exception e) {
			numRegAct = actualizarEquipoDAO(oEquipoTO);
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla equipo
	 * 
	 */
	public int actualizarEquipoDAO(EquipoIF oEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.actualizarEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			if(Constants.isNull(oEquipoTO.getDescEquipo()) || Constants.isNull(oEquipoTO.getAbreviatura())) {
				throw new Exception("Los Datos deben estar completos");
			}
			
			oParametros.add(oEquipoTO.getDescEquipo());
			oParametros.add(oEquipoTO.getAbreviatura().toUpperCase());
			oParametros.add(oEquipoTO.getDescCorta());
			oParametros.add(oEquipoTO.getIdEquipo()); // primary key

			strBuffquery.append("UPDATE equipo SET ");
			strBuffquery.append("desc_equipo=?, abreviatura=?, desc_corta=?  ");
			strBuffquery.append("WHERE  id_equipo = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.actualizarEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla equipo
	 * 
	 */
	public int eliminarEquipoDAO(EquipoIF oEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.eliminarEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oEquipoTO.getIdEquipo());
			strBuffquery.append("DELETE FROM equipo  WHERE  (id_equipo = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.eliminarEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla equipo
	 * 
	 */
	public CachedRowSet listaEquipoDAO() throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.listaEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_equipo, desc_equipo, UPPER(abreviatura), desc_corta ");
			strBuffquery.append("FROM equipo ");
			strBuffquery.append("ORDER BY UPPER(desc_equipo) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.listaEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla equipo
	 * 
	 */
	public boolean cargarEquipoDAO(EquipoIF oEquipoTO) throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.consultarEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_equipo, desc_equipo, abreviatura, desc_corta ");
			strBuffquery.append("FROM equipo ");

			boolean procesar = false;
			if (!oEquipoTO.getIdEquipo().equalsIgnoreCase("")) {
				oParametros.add(oEquipoTO.getIdEquipo());
				strBuffquery.append(" WHERE id_equipo = ? ");
				procesar = true;
			} else if (!oEquipoTO.getDescEquipo().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_equipo) LIKE '%");
				strBuffquery.append(oEquipoTO.getDescEquipo().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oEquipoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
					oEquipoTO.setDescEquipo(oCachedRowSet.getString("desc_equipo"));
					oEquipoTO.setAbreviatura(oCachedRowSet.getString("abreviatura"));
					oEquipoTO.setDescCorta(oCachedRowSet.getString("desc_corta"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.consultarEquipoDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;

	}
	

	/**
	 * 
	 * Consulta una lista de registros en la tabla equipo
	 * 
	 */
	public ArrayList equipoEmpateDAO() throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.equipoEmpateDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		EquipoIF oEquipoTO = null;
		try {
			oParametros.add(Constants.ID_EQUIPO_EMPATE);
			
			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_equipo, desc_equipo, abreviatura, desc_corta FROM equipo WHERE id_equipo=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			while(oCachedRowSet.next()) {
				oEquipoTO = new EquipoTO();
				oEquipoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
				oEquipoTO.setDescEquipo(oCachedRowSet.getString("desc_equipo"));
				oEquipoTO.setAbreviatura(oCachedRowSet.getString("abreviatura"));
				oEquipoTO.setDescCorta(oCachedRowSet.getString("desc_corta"));
				lista.add(oEquipoTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.equipoEmpateDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla equipo
	 * 
	 */
	public ArrayList listEquipoEmpateDAO() throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.listEquipoEmpateDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		EquipoIF oEquipoTO = null;
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_equipo, desc_equipo, desc_corta FROM equipo WHERE empate=1");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			while(oCachedRowSet.next()) {
				oEquipoTO = new EquipoTO();
				oEquipoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
				oEquipoTO.setDescEquipo(oCachedRowSet.getString("desc_equipo"));
				oEquipoTO.setAbreviatura(oCachedRowSet.getString("abreviatura"));
				oEquipoTO.setDescCorta(oCachedRowSet.getString("desc_corta"));
				lista.add(oEquipoTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.listEquipoEmpateDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	

	/**
	 * 
	 * Consulta una lista de registros en la tabla equipo
	 * 
	 */
	public ArrayList listEquipoDAO() throws Exception {
		log.info("Iniciando ejecucion de EquipoDAO.listEquipoDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		EquipoIF oEquipoTO = null;
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_equipo, desc_equipo, abreviatura, desc_corta FROM equipo ORDER BY desc_equipo");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			while(oCachedRowSet.next()) {
				oEquipoTO = new EquipoTO();
				oEquipoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
				oEquipoTO.setDescEquipo(oCachedRowSet.getString("desc_equipo"));
				oEquipoTO.setAbreviatura(oCachedRowSet.getString("abreviatura"));
				oEquipoTO.setDescCorta(oCachedRowSet.getString("desc_corta"));
				lista.add(oEquipoTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de EquipoDAO.listEquipoDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	
	

}
