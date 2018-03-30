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
import com.betcesc.game.interfaces.MenuIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.MenuTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla menu.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla menu
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class MenuDAO {
	private static final Log log = LogFactory.getLog(MenuDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public MenuDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla menu
	 * 
	 */
	public int insertarMenuDAO(MenuIF oMenuTO) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.insertarMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oMenuTO.getIdMenu());
			oParametros.add(oMenuTO.getDescMenu());
			oParametros.add(oMenuTO.getOrden());
			oParametros.add(oMenuTO.getAccion());
			oParametros.add(oMenuTO.getImagen());

			strBuffquery.append("INSERT INTO menu VALUES (?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.insertarMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla menu
	 * 
	 */
	public int actualizarMenuDAO(MenuIF oMenuTO) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.actualizarMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {

			oParametros.add(oMenuTO.getDescMenu());
			oParametros.add(oMenuTO.getOrden());
			oParametros.add(oMenuTO.getAccion());
			oParametros.add(oMenuTO.getImagen());
			oParametros.add(oMenuTO.getIdMenu()); // primary key

			strBuffquery.append("UPDATE menu SET ");
			strBuffquery.append("desc_menu=? , orden=?, accion=?, ");
			strBuffquery.append("imagen=?  ");
			strBuffquery.append("WHERE  id_menu = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.actualizarMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla menu
	 * 
	 */
	public int eliminarMenuDAO(MenuIF oMenuTO) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.eliminarMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {

			oParametros.add(oMenuTO.getIdMenu());
			strBuffquery.append("DELETE FROM menu  WHERE  (id_menu = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.eliminarMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla menu
	 * 
	 */
	public CachedRowSet listaMenuDAO() throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.listaMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_menu, UPPER(desc_menu) ");
			strBuffquery.append("FROM menu ");
			strBuffquery.append("ORDER BY UPPER(desc_menu) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.listaMenuDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla menu
	 * 
	 */
	public CachedRowSet consultarMenuDAO(MenuIF oMenuTO) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.consultarMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_menu, desc_menu, orden, ");
			strBuffquery.append("accion, imagen ");
			strBuffquery.append("FROM menu ");

			boolean procesar = false;
			if (!oMenuTO.getIdMenu().equalsIgnoreCase("")) {
				oParametros.add(oMenuTO.getIdMenu());
				strBuffquery.append(" WHERE id_menu = ? ");
				procesar = true;
			} else if (!oMenuTO.getDescMenu().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_menu) LIKE '%");
				strBuffquery.append(oMenuTO.getDescMenu().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.consultarMenuDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla menu
	 * 
	 */
	public boolean cargarMenuDAO(MenuIF oMenuTO) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.consultarMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  id_menu, desc_menu, orden, ");
			strBuffquery.append("accion, imagen ");
			strBuffquery.append("FROM menu ");

			boolean procesar = false;
			if (!oMenuTO.getIdMenu().equalsIgnoreCase("")) {
				oParametros.add(oMenuTO.getIdMenu());
				strBuffquery.append(" WHERE id_menu = ? ");
				procesar = true;
			} else if (!oMenuTO.getDescMenu().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(desc_menu) LIKE '%");
				strBuffquery.append(oMenuTO.getDescMenu().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oMenuTO.setIdMenu(oCachedRowSet.getString("id_menu"));
					oMenuTO.setDescMenu(oCachedRowSet.getString("desc_menu"));
					oMenuTO.setOrden(oCachedRowSet.getString("orden"));
					oMenuTO.setAccion(oCachedRowSet.getString("accion"));
					oMenuTO.setImagen(oCachedRowSet.getString("imagen"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.consultarMenuDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}

		return retorno;
	}

	/**
	 * 
	 * Consulta un registro en la tabla menu por rol
	 * 
	 */
	public ArrayList consultarMenuDAO(UsuarioIF oUsuarioTO) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.consultarMenuDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		try {
			oCachedRowSet = new CachedRowSet();

			oParametros.add(oUsuarioTO.getIdUsuario());
			oParametros.add(oUsuarioTO.getIdRol());

			strBuffquery.append("SELECT  a.id_menu, a.desc_menu, a.orden, a.accion, a.imagen, if(c.id_menu IS NOT NULL,c.id_menu,0) As activo ");
			strBuffquery.append("FROM menu a LEFT OUTER JOIN rol_menu b ON a.id_menu=b.id_menu ");
			strBuffquery.append("LEFT OUTER JOIN menu_user c ON a.id_menu=c.id_menu AND c.id_usuario=? ");
			strBuffquery.append("WHERE b.id_rol = ? ");
			strBuffquery.append("ORDER BY a.orden ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			while(oCachedRowSet.next()) {
				if(!oCachedRowSet.getString("activo").equals("0") || oCachedRowSet.getString("id_menu").equals("1") || oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR) || !oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR) ) {
					MenuIF oMenuTO = new MenuTO();
					oMenuTO.setIdMenu(oCachedRowSet.getString("id_menu"));
					oMenuTO.setDescMenu(oCachedRowSet.getString("desc_menu"));
					oMenuTO.setOrden(oCachedRowSet.getString("orden"));
					oMenuTO.setAccion(oCachedRowSet.getString("accion"));
					oMenuTO.setImagen(oCachedRowSet.getString("imagen"));
					
					lista.add(oMenuTO);
				}
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.consultarMenuDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return lista;
	}

	/**
	 * 
	 * Consulta los permisos segun las menues del rol administrador segun el usuario pasado como parametro
	 * 
	 */
	public CachedRowSet listaMenuUsuarioDAO(String idUsuario) throws Exception {
		log.info("Iniciando ejecucion de MenuDAO.listaMenuUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			oCachedRowSet = new CachedRowSet();
			oParametros.add(idUsuario);
			oParametros.add(Constants.ROL_ADMINISTRADOR);

			strBuffquery.append("SELECT a.id_menu,desc_menu,if(c.id_menu IS NOT NULL,c.id_menu,0) As activo ");
			strBuffquery.append("FROM menu a LEFT OUTER JOIN rol_menu b ON a.id_menu=b.id_menu ");
			strBuffquery.append("LEFT OUTER JOIN menu_user c ON a.id_menu=c.id_menu AND c.id_usuario=? ");
			strBuffquery.append("WHERE b.id_rol=? ");
			strBuffquery.append("ORDER BY a.orden ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de MenuDAO.listaMenuUsuarioDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		}
		return oCachedRowSet;
	}

}
