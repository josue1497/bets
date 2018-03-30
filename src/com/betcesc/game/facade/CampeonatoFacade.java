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

import com.betcesc.game.dao.CampeonatoDAO;
import com.betcesc.game.interfaces.CampeonatoIF;
import com.betcesc.game.to.CampeonatoTO;

import com.jade.util.lweb.FormInput;
import com.jade.util.lweb.FormInputDinamic;
import com.jade.util.lweb.FormInputJs;

/**
 * 
 * @author jrivero
 * 
 */

public class CampeonatoFacade {
	private static final Log log = LogFactory.getLog(CampeonatoFacade.class);

	HttpServletRequest request = null;

	/**
	 * Constructor.
	 */
	public CampeonatoFacade(HttpServletRequest request) {
		super();
		this.request = request;
	}

	/*
	 * ...........................CAMPEONATO...........................
	 */

	/**
	 * Insertar registros en la tabla Campeonato.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarCampeonatoFacade(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoFacade.insertarCampeonatoFacade");
		int act = 0;

		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();

		try {
			act = oCampeonatoDAO.insertarCampeonatoDAO(oCampeonatoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoFacade.insertarCampeonatoFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Actualizar registros en la tabla Campeonato.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarCampeonatoFacade(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoFacade.actualizarCampeonatoFacade");
		int act = 0;
		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();
		try {
			act = oCampeonatoDAO.actualizarCampeonatoDAO(oCampeonatoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoFacade.actualizarCampeonatoFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Eliminar registros en la tabla Campeonato.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int eliminarCampeonatoFacade(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoFacade.eliminarCampeonatoFacade");
		int act = 0;
		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();
		try {
			act = oCampeonatoDAO.eliminarCampeonatoDAO(oCampeonatoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoFacade.eliminarCampeonatoFacade");
			log.error("Error:" + e.getMessage());
		}
		return (act);
	}

	/**
	 * Cargar registros un registro de la tabla Campeonato.
	 * 
	 * @return CampeonatoTO.
	 */
	public CampeonatoTO consultarCampeonatoFacade(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoFacade.cargarCampeonatoFacade");
		int act = 0;

		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();
		CampeonatoTO campeonatoTO = new CampeonatoTO();
		campeonatoTO.setIdCampeonato(oCampeonatoTO.getIdCampeonato());

		try {
			oCampeonatoDAO.cargarCampeonatoDAO(campeonatoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de cargarCampeonatoFacade.consultarCampeonatoFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		}
		return campeonatoTO;
	}

	/**
	 * Construye la lista de los registros de la tabla campeonato
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaCampeonatoFacade() throws Exception {
		log.info("Iniciando ejecucion de CampeonatoFacade.listaCampeonatoFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();
		CampeonatoTO oCampeonatoTO = new CampeonatoTO();
		try {
			oCachedRowSet = oCampeonatoDAO.listaCampeonatoDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoFacade.listaCampeonatoFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

	/**
	 * Construye la consulta de lista de los registros de la tabla campeonato
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet consultarCampeonatoListaFacade(CampeonatoIF oCampeonatoTO) throws Exception {
		log.info("Iniciando ejecucion de CampeonatoFacade.consultarCampeonatoListaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		CampeonatoDAO oCampeonatoDAO = new CampeonatoDAO();
		try {
			oCachedRowSet = oCampeonatoDAO.listaCampeonatoDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de CampeonatoFacade.consultarCampeonatoListaFacade");
			log.error("Error:" + e.getMessage());
		}
		return oCachedRowSet;
	}

}
