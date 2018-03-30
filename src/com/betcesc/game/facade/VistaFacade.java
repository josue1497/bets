package com.betcesc.game.facade;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.dao.VistaDAO;
import com.betcesc.game.interfaces.VistaIF;
import com.betcesc.game.to.VistaTO;
import com.betcesc.game.util.FileUtil;
import com.jade.util.Date;
import com.jade.util.lbda.EjecutorSql;

/**
 * 
 * @author jrivero
 * 
 */

public class VistaFacade {
	private static final Log log = LogFactory.getLog(VistaFacade.class);

	HttpServletRequest request = null;

	/**
	 * Constructor.
	 */
	public VistaFacade(HttpServletRequest request) {
		super();
		this.request = request;
	}

	/*
	 * ...........................VISTA...........................
	 */

	/**
	 * Insertar registros en la tabla Vista.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarVistaFacade(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.insertarVistaFacade");
		int act = 0;

		VistaDAO oVistaDAO = new VistaDAO();

		try {
			act = oVistaDAO.insertarVistaDAO(oVistaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.insertarVistaFacade");
			e.printStackTrace();
		}
		return (act);
	}

	/**
	 * Actualizar registros en la tabla Vista.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarVistaFacade() throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.actualizarVistaFacade");
		int act = 0;
		VistaDAO oVistaDAO = new VistaDAO();
		VistaTO oVistaTO = new VistaTO();
		try {
			Date date = new Date();
			String name = "f".concat(String.valueOf(date.getTime()));
			
			// procesamos el archivo inicialmente
			FileUtil fileUtil = new FileUtil();
			Properties parametros = fileUtil.procesaFicheros(request,name);

			if (parametros.getProperty("eliminar").equals("true")) {
				oVistaTO.setIdVista(parametros.getProperty("idVista"));
				
				// cargamos el registro antes de eliminarlo
				if(oVistaDAO.cargarVistaDAO(oVistaTO)) {
					StringBuffer nameFile = new StringBuffer(); 
					nameFile.append(request.getSession().getServletContext().getRealPath("/").replace((char) 92, '/') + "dbimg/");
					nameFile.append(oVistaTO.getImage());
					oVistaTO.setImage(nameFile.toString());
					
					oVistaDAO.eliminarVistaDAO(oVistaTO);
					File f = new File(oVistaTO.getImage());
					
					EjecutorSql sql = new EjecutorSql();
					sql.deleteImage(f.getName());
					// eliminamos el archivo
				}
				
			} else {

				oVistaTO.setIdVista(parametros.getProperty("idVista"));
				oVistaTO.setTitle(parametros.getProperty("title"));
				oVistaTO.setContent(parametros.getProperty("content"));
				oVistaTO.setImage(parametros.getProperty("image"));
				oVistaTO.setStatus(parametros.getProperty("status") != null ? "1" : "0");
				oVistaTO.setTipo(parametros.getProperty("tipo"));

				if (Constants.isNull(oVistaTO.getIdVista(), "0").equals("0")) {
					act = oVistaDAO.insertarVistaDAO(oVistaTO);
				} else {
					act = oVistaDAO.actualizarVistaDAO(oVistaTO);
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.actualizarVistaFacade");
			e.printStackTrace();
		}
		return (act);
	}

	/**
	 * Eliminar registros en la tabla Vista.
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public int eliminarVistaFacade(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.eliminarVistaFacade");
		int act = 0;
		VistaDAO oVistaDAO = new VistaDAO();
		try {
			act = oVistaDAO.eliminarVistaDAO(oVistaTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.eliminarVistaFacade");
			e.printStackTrace();
		}
		return (act);
	}

	/**
	 * Cargar registros un registro de la tabla Vista.
	 * 
	 * @return VistaTO.
	 */
	public VistaTO consultarVistaFacade(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.cargarVistaFacade");
		int act = 0;

		VistaDAO oVistaDAO = new VistaDAO();
		VistaTO equipoTO = new VistaTO();
		equipoTO.setIdVista(oVistaTO.getIdVista());

		try {
			oVistaDAO.cargarVistaDAO(equipoTO);
		} catch (Exception e) {
			log.info("Error en la ejecucion de cargarVistaFacade.consultarVistaFacade");
			e.printStackTrace();
		}
		return equipoTO;
	}

	/**
	 * Construye la lista de los registros de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaVistaFacade() throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.listaVistaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		VistaDAO oVistaDAO = new VistaDAO();
		VistaTO oVistaTO = new VistaTO();
		try {
			oCachedRowSet = oVistaDAO.listaVistaDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.listaVistaFacade");
			e.printStackTrace();
		}
		return oCachedRowSet;
	}

	/**
	 * Construye la lista de los registros de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaVistaActivaFacade() throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.listaVistaFacade");
		ArrayList lista = new ArrayList();
		VistaDAO oVistaDAO = new VistaDAO();
		VistaTO oVistaTO = new VistaTO();
		try {
			lista = oVistaDAO.listVistaActivaDAO(null);
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.listaVistaFacade");
			e.printStackTrace();
		}
		return lista;
	}
	
	/**
	 * Construye la lista de los registros de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaVistaActivaFacade(String tipo) throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.listaVistaFacade");
		ArrayList lista = new ArrayList();
		VistaDAO oVistaDAO = new VistaDAO();
		VistaTO oVistaTO = new VistaTO();
		try {
			lista = oVistaDAO.listVistaActivaDAO(tipo);
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.listaVistaFacade");
			e.printStackTrace();
		}
		return lista;
	}
	

	/**
	 * Construye la lista de los registros de la tabla equipo
	 * 
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaVistaArrayFacade() throws Exception {
		log.info("Iniciando ejecucion de VistaFacade.listaVistaFacade");
		ArrayList lista = new ArrayList();
		VistaDAO oVistaDAO = new VistaDAO();
		VistaTO oVistaTO = new VistaTO();
		try {
			lista = oVistaDAO.listVistaDAO();
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaFacade.listaVistaFacade");
			e.printStackTrace();
		}
		return lista;
	}

}
