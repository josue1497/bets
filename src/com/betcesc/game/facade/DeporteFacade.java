/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.facade;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.dao.DeporteDAO;
import com.betcesc.game.dao.LigaDAO;
import com.betcesc.game.interfaces.DeporteIF;
import com.betcesc.game.interfaces.LigaIF;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.LigaTO;

/**
 * 
 * @author jrivero
 * 
 */

public class DeporteFacade {
	private static final Log log = LogFactory.getLog(DeporteFacade.class);

	HttpServletRequest request = null;

	/**
	 * Constructor.
	 */
	public DeporteFacade(HttpServletRequest request) {
		super();
		this.request = request;
	}

	/*
	 * ...........................DEPORTE...........................
	 */

	/**
	 * Insertar registros en la tabla Deporte.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarDeporteFacade(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.insertarDeporteFacade");
		int act = 0;

		DeporteDAO oDeporteDAO = new DeporteDAO();

		try {
			act = oDeporteDAO.insertarDeporteDAO(oDeporteTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteFacade.insertarDeporteFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Actualizar registros en la tabla Deporte.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarDeporteFacade(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.actualizarDeporteFacade");
		int act = 0;
		DeporteDAO oDeporteDAO = new DeporteDAO();
		try {
			act = oDeporteDAO.actualizarDeporteDAO(oDeporteTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteFacade.actualizarDeporteFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Eliminar registros en la tabla Deporte.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int eliminarDeporteFacade(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.eliminarDeporteFacade");
		int act = 0;
		DeporteDAO oDeporteDAO = new DeporteDAO();
		try {
			act = oDeporteDAO.eliminarDeporteDAO(oDeporteTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteFacade.eliminarDeporteFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Cargar registros un registro de la tabla Deporte.
	 * 
	 * @return DeporteTO.
	 */
	public DeporteTO consultarDeporteFacade(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.cargarDeporteFacade");
		int act = 0;

		DeporteDAO oDeporteDAO = new DeporteDAO();
		DeporteTO deporteTO = new DeporteTO();
		deporteTO.setIdDeporte(oDeporteTO.getIdDeporte());

		try {
			oDeporteDAO.cargarDeporteDAO(deporteTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de cargarDeporteFacade.consultarDeporteFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		}
		return deporteTO;
	}

	/**
	 * Cargar registros un registro de la tabla Deporte.
	 * 
	 * @return DeporteTO.
	 */
	public LigaTO consultarLigaFacade(LigaIF oLigaTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.cargarLigaFacade");
		int act = 0;

		LigaDAO oLigaDAO = new LigaDAO();
		LigaTO ligaTO = new LigaTO();
		ligaTO.setIdDeporte(oLigaTO.getIdLiga());

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
	 * Construye la lista de los registros de la tabla deporte
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaDeporteFacade(boolean status) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.listaDeporteFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		DeporteDAO oDeporteDAO = new DeporteDAO();
		DeporteTO oDeporteTO = new DeporteTO();
		try {
			oCachedRowSet = oDeporteDAO.listaDeporteDAO(status);
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteFacade.listaDeporteFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

	/**
	 * Construye la consulta de lista de los registros de la tabla deporte
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet consultarDeporteListaFacade(DeporteIF oDeporteTO) throws Exception {
		log.info("Iniciando ejecucion de DeporteFacade.consultarDeporteListaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		DeporteDAO oDeporteDAO = new DeporteDAO();
		try {
			oCachedRowSet = oDeporteDAO.listaDeporteDAO(true);
		} catch (Exception e) {
			log.info("Error en la ejecucion de DeporteFacade.consultarDeporteListaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

}
