/*
 * Proyecto Betcesc.com - Sistema de Apuestas Deportivas.
 * Fecha: 23/04/2009 -  08:50:23
 * 
 * Copyright (C) Main Step, 2008. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.OpcionIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.OpcionTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla opcion.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla opcion
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class OpcionDAO {
	private static final Log log = LogFactory.getLog(OpcionDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public OpcionDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla opcion
	 * 
	 */
	public int insertarOpcionDAO(OpcionIF oOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.insertarOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oOpcionTO.getIdOpcion());
			oParametros.add(oOpcionTO.getDescOpcion());
			oParametros.add(oOpcionTO.getOrden());
			oParametros.add(oOpcionTO.getAccion());
			oParametros.add(oOpcionTO.getImagen());

			strBuffquery.append("INSERT INTO opcion VALUES (?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.insertarOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla opcion
	 * 
	 */
	public int actualizarOpcionDAO(OpcionIF oOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.actualizarOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oOpcionTO.getDescOpcion());
			oParametros.add(oOpcionTO.getOrden());
			oParametros.add(oOpcionTO.getAccion());
			oParametros.add(oOpcionTO.getImagen());
			oParametros.add(oOpcionTO.getIdOpcion()); // primary key

			strBuffquery.append("UPDATE opcion SET ");
			strBuffquery.append("desc_opcion=? , orden=?, accion=?, ");
			strBuffquery.append("imagen=?  ");
			strBuffquery.append("WHERE  id_opcion = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.actualizarOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla opcion
	 * 
	 */
	public int eliminarOpcionDAO(OpcionIF oOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.eliminarOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oOpcionTO.getIdOpcion());
			strBuffquery.append("DELETE FROM opcion  WHERE  (id_opcion = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.eliminarOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla opcion
	 * 
	 */
	public CachedRowSet listaOpcionDAO() throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.listaOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_opcion, UPPER(desc_opcion) ");
			strBuffquery.append("FROM opcion ");
			strBuffquery.append("ORDER BY UPPER(desc_opcion) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.listaOpcionDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla opcion
	 * 
	 */
	public CachedRowSet consultarOpcionDAO(OpcionIF oOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.consultarOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_opcion, desc_opcion, orden, ");
			strBuffquery.append("accion, imagen ");
			strBuffquery.append("FROM opcion ");

			boolean procesar = false;
			if (!oOpcionTO.getIdOpcion().equalsIgnoreCase("")) {
				oParametros.add(oOpcionTO.getIdOpcion());
				strBuffquery.append(" WHERE id_opcion = ? ");
				procesar = true;
			} else if (!oOpcionTO.getDescOpcion().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_opcion) LIKE '%");
				strBuffquery.append(oOpcionTO.getDescOpcion().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.consultarOpcionDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla opcion
	 * 
	 */
	public boolean cargarOpcionDAO(OpcionIF oOpcionTO) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.consultarOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_opcion, desc_opcion, orden, ");
			strBuffquery.append("accion, imagen ");
			strBuffquery.append("FROM opcion ");

			boolean procesar = false;
			if (!oOpcionTO.getIdOpcion().equalsIgnoreCase("")) {
				oParametros.add(oOpcionTO.getIdOpcion());
				strBuffquery.append(" WHERE id_opcion = ? ");
				procesar = true;
			} else if (!oOpcionTO.getDescOpcion().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_opcion) LIKE '%");
				strBuffquery.append(oOpcionTO.getDescOpcion().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oOpcionTO.setIdOpcion(oCachedRowSet.getString("id_opcion"));
					oOpcionTO.setDescOpcion(oCachedRowSet.getString("desc_opcion"));
					oOpcionTO.setOrden(oCachedRowSet.getString("orden"));
					oOpcionTO.setAccion(oCachedRowSet.getString("accion"));
					oOpcionTO.setImagen(oCachedRowSet.getString("imagen"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.consultarOpcionDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta un registro en la tabla opcion por perfil
	 * 
	 */
	public ArrayList consultarOpcionDAO(UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.consultarOpcionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		try {
			oCachedRowSet = new CachedRowSet();

			oParametros.add(oUsuarioTO.getIdUsuario());
			oParametros.add(oUsuarioTO.getIdRol());

			strBuffquery.append("SELECT  a.id_opcion, a.desc_opcion, a.orden, a.accion, a.imagen, if(c.id_opcion IS NOT NULL,c.id_opcion,0) As activo ");
			strBuffquery.append("FROM opcion a LEFT OUTER JOIN perfil_opcion b ON a.id_opcion=b.id_opcion ");
			strBuffquery.append("LEFT OUTER JOIN opcion_user c ON a.id_opcion=c.id_opcion AND c.id_usuario=? ");
			strBuffquery.append("WHERE b.id_perfil = ? ");
			strBuffquery.append("ORDER BY a.orden ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			while(oCachedRowSet.next()) {
				if(!oCachedRowSet.getString("activo").equals("0") || oCachedRowSet.getString("id_opcion").equals("1") || oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) || !oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR) ) {
					OpcionIF oOpcionTO = new OpcionTO();
					oOpcionTO.setIdOpcion(oCachedRowSet.getString("id_opcion"));
					oOpcionTO.setDescOpcion(oCachedRowSet.getString("desc_opcion"));
					oOpcionTO.setOrden(oCachedRowSet.getString("orden"));
					oOpcionTO.setAccion(oCachedRowSet.getString("accion"));
					oOpcionTO.setImagen(oCachedRowSet.getString("imagen"));
					
					lista.add(oOpcionTO);
				}
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.consultarOpcionDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return lista;
	}

	/**
	 * 
	 * Consulta los permisos segun las opciones del perfil administrador segun el usuario pasado como parametro
	 * 
	 */
	public CachedRowSet listaOpcionUsuarioDAO(String idUsuario) throws Exception {
		log.info("Iniciando ejecucion de OpcionDAO.listaOpcionUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();
			oParametros.add(idUsuario);
			oParametros.add(Constants.ROL_ADMINISTRADOR);

			strBuffquery.append("SELECT a.id_opcion,desc_opcion,if(c.id_opcion IS NOT NULL,c.id_opcion,0) As activo ");
			strBuffquery.append("FROM opcion a LEFT OUTER JOIN perfil_opcion b ON a.id_opcion=b.id_opcion ");
			strBuffquery.append("LEFT OUTER JOIN opcion_user c ON a.id_opcion=c.id_opcion AND c.id_usuario=? ");
			strBuffquery.append("WHERE b.id_perfil=? ");
			strBuffquery.append("ORDER BY a.orden ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de OpcionDAO.listaOpcionUsuarioDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

}
