/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.facade;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.dao.LigaDAO;
import com.betcesc.game.interfaces.LigaIF;
import com.betcesc.game.to.EquipoTO;
import com.betcesc.game.to.LigaTO;

/**
 * 
 * @author jrivero
 * 
 */

public class LigaFacade {
	private static final Log log = LogFactory.getLog(LigaFacade.class);

	HttpServletRequest request = null;

	/**
	 * Constructor.
	 */
	public LigaFacade(HttpServletRequest request) {
		super();
		this.request = request;
	}

	/*
	 * ...........................LIGA...........................
	 */

	/**
	 * Insertar registros en la tabla Liga.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.insertarLigaFacade");
		int act = 0;

		LigaDAO oLigaDAO = new LigaDAO();

		try {
			act = oLigaDAO.insertarLigaDAO(oLigaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaFacade.insertarLigaFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Actualizar registros en la tabla Liga.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.actualizarLigaFacade");
		int act = 0;
		LigaDAO oLigaDAO = new LigaDAO();
		try {
			act = oLigaDAO.actualizarLigaDAO(oLigaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaFacade.actualizarLigaFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Eliminar registros en la tabla Liga.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int eliminarLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.eliminarLigaFacade");
		int act = 0;
		LigaDAO oLigaDAO = new LigaDAO();
		try {
			act = oLigaDAO.eliminarLigaDAO(oLigaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaFacade.eliminarLigaFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Cargar registros un registro de la tabla Liga.
	 * 
	 * @return LigaTO.
	 */
	public LigaTO consultarLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.cargarLigaFacade");
		int act = 0;

		LigaDAO oLigaDAO = new LigaDAO();
		LigaTO ligaTO = new LigaTO();
		ligaTO.setIdLiga(oLigaTO.getIdLiga());

		try {
			oLigaDAO.cargarLigaDAO(ligaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de cargarLigaFacade.consultarLigaFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		}
		return ligaTO;
	}

	/**
	 * Construye la lista de los registros de la tabla liga
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaLigaFacade() throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.listaLigaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		LigaDAO oLigaDAO = new LigaDAO();
		LigaTO oLigaTO = new LigaTO();
		try {
			oCachedRowSet = oLigaDAO.listaLigaDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaFacade.listaLigaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

	/**
	 * Construye la consulta de lista de los registros de la tabla liga
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet consultarListaLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.consultarLigaListaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		LigaDAO oLigaDAO = new LigaDAO();
		try {
			oCachedRowSet = oLigaDAO.listaLigaPorDeporteDAO(oLigaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaFacade.consultarLigaListaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	/**
	 * Construye la consulta de lista de los registros de la tabla liga
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet consultarListaActivaLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de LigaFacade.consultarLigaListaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		LigaDAO oLigaDAO = new LigaDAO();
		try {
			oCachedRowSet = oLigaDAO.listaLigaActivaPorDeporteDAO(oLigaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de LigaFacade.consultarLigaListaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaLigaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		LigaDAO oLigaDAO = new LigaDAO();
		LigaTO ligaTO = new LigaTO();
		ArrayList lista = new ArrayList();
		try {
			oCachedRowSet = oLigaDAO.listaLigaPorDeporteDAO(oLigaTO);
			while (oCachedRowSet.next()) {
				ligaTO = new LigaTO();
				ligaTO.setIdLiga(oCachedRowSet.getString("id_liga"));
				ligaTO.setDescLiga(oCachedRowSet.getString("desc_liga"));
				lista.add(ligaTO);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaLigaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	
	/**
	 * Devuelve una lista de objetos de la tabla status
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaEquipoPorLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de UsuarioFacade.listaEquipoPorLigaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		LigaDAO oLigaDAO = new LigaDAO();
		EquipoTO equipoTO = new EquipoTO();
		ArrayList lista = new ArrayList();
		try {
			oCachedRowSet = oLigaDAO.listaEquipoPorLigaDAO(oLigaTO);
			while (oCachedRowSet.next()) {
				equipoTO = new EquipoTO();
				equipoTO.setIdEquipo(oCachedRowSet.getString("id_equipo"));
				equipoTO.setDescEquipo(oCachedRowSet.getString("desc_equipo"));
				lista.add(equipoTO);
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de UsuarioFacade.listaEquipoPorLigaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}

}
