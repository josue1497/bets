package com.betcesc.game.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.VistaIF;
import com.betcesc.game.to.VistaTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla vista.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla vista
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class VistaDAO {
	private static final Log log = LogFactory.getLog(VistaDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public VistaDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	/**
	 * 
	 * Inserta un registro en la tabla vista
	 * 
	 */
	public int insertarVistaDAO(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.insertarVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oVistaTO.setIdVista(Constants.isNull(oVistaTO.getIdVista(),"0"));

			if(Constants.isNull(oVistaTO.getTitle())) {
				oVistaTO.setTitle("");
			}
			if(Constants.isNull(oVistaTO.getContent())) {
				oVistaTO.setContent("");
			}


			oParametros.add(oVistaTO.getIdVista());
			oParametros.add(oVistaTO.getTitle());
			oParametros.add(oVistaTO.getContent());
			oParametros.add(oVistaTO.getImage());
			oParametros.add(oVistaTO.getStatus());
			oParametros.add(oVistaTO.getTipo());
			
			strBuffquery.append("INSERT INTO vista VALUES (?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			
			oVistaTO.setIdVista(String.valueOf(oEjecutorSql.getGeneratedKey()));
		} catch (Exception e) {
			numRegAct = actualizarVistaDAO(oVistaTO);
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla vista
	 * 
	 */
	public int actualizarVistaDAO(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.actualizarVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			if(Constants.isNull(oVistaTO.getTitle())) {
				oVistaTO.setTitle("");
			}
			if(Constants.isNull(oVistaTO.getContent())) {
				oVistaTO.setContent("");
			}
			
			oParametros.add(oVistaTO.getTitle());
			oParametros.add(oVistaTO.getContent());
			oParametros.add(oVistaTO.getStatus());
			oParametros.add(oVistaTO.getTipo());
			
			oParametros.add(oVistaTO.getIdVista()); // primary key

			strBuffquery.append("UPDATE vista SET ");
			strBuffquery.append("title=?, content=?, status=?, tipo=?  ");
			if(oVistaTO.getImage()!=null && !oVistaTO.getImage().trim().equals("")) {
				strBuffquery.append(",image='").append(oVistaTO.getImage()).append("' ");
			}
			strBuffquery.append("WHERE  idVista = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaDAO.actualizarVistaDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Elimina un registro en la tabla vista
	 * 
	 */
	public int eliminarVistaDAO(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.eliminarVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			

			oParametros.add(oVistaTO.getIdVista());
			strBuffquery.append("DELETE FROM vista  WHERE  (idVista = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaDAO.eliminarVistaDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla vista
	 * 
	 */
	public CachedRowSet listaVistaDAO() throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.listaVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT idVista, desc_vista, UPPER(abreviatura), desc_corta, tipo ");
			strBuffquery.append("FROM vista ");
			strBuffquery.append("ORDER BY UPPER(desc_vista) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaDAO.listaVistaDAO");
			e.printStackTrace();
			throw e;
		}
		return oCachedRowSet;
	}

	/**
	 * 
	 * Consulta un registro en la tabla vista
	 * 
	 */
	public boolean cargarVistaDAO(VistaIF oVistaTO) throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.consultarVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT  idVista, title, content, image, status, tipo ");
			strBuffquery.append("FROM vista ");

			boolean procesar = false;
			if (!oVistaTO.getIdVista().equalsIgnoreCase("")) {
				oParametros.add(oVistaTO.getIdVista());
				strBuffquery.append(" WHERE idVista = ? ");
				procesar = true;
			} else if (!oVistaTO.getTitle().equalsIgnoreCase("")) {
				strBuffquery.append(" WHERE UPPER(title) LIKE '%");
				strBuffquery.append(oVistaTO.getTitle().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			}

			if (procesar) {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next()) {
					oVistaTO.setIdVista(oCachedRowSet.getString("idVista"));
					oVistaTO.setTitle(oCachedRowSet.getString("title"));
					oVistaTO.setContent(oCachedRowSet.getString("content"));
					oVistaTO.setImage(oCachedRowSet.getString("image"));
					oVistaTO.setStatus(oCachedRowSet.getString("status"));
					oVistaTO.setTipo(oCachedRowSet.getString("tipo"));
					retorno = true;
				}
			}
		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaDAO.consultarVistaDAO");
			e.printStackTrace();
			throw e;
		}

		return retorno;

	}
	

	
	/**
	 * 
	 * Consulta una lista de registros en la tabla vista
	 * 
	 */
	public ArrayList listVistaDAO() throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.listVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		VistaIF oVistaTO = null;
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT idVista, title, content, image, status, tipo FROM vista ORDER BY idVista");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			while(oCachedRowSet.next()) {
				oVistaTO = new VistaTO();
				oVistaTO.setIdVista(oCachedRowSet.getString("idVista"));
				oVistaTO.setTitle(oCachedRowSet.getString("title"));
				oVistaTO.setContent(oCachedRowSet.getString("content"));
				oVistaTO.setImage(oCachedRowSet.getString("image"));
				oVistaTO.setStatus(oCachedRowSet.getString("status"));
				oVistaTO.setTipo(oCachedRowSet.getString("tipo"));
				lista.add(oVistaTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaDAO.listVistaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla vista
	 * 
	 */
	public ArrayList listVistaActivaDAO(String tipo) throws Exception {
		log.info("Iniciando ejecucion de VistaDAO.listVistaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		ArrayList lista = new ArrayList();
		VistaIF oVistaTO = null;
		try {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT idVista, title, content, image, status, tipo FROM vista ");
			strBuffquery.append("WHERE status=1 ");
			if(tipo!=null) {
				strBuffquery.append("AND tipo='").append(tipo).append("' ");
			}
			strBuffquery.append("ORDER BY idVista");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			while(oCachedRowSet.next()) {
				oVistaTO = new VistaTO();
				oVistaTO.setIdVista(oCachedRowSet.getString("idVista"));
				oVistaTO.setTitle(oCachedRowSet.getString("title"));
				oVistaTO.setContent(oCachedRowSet.getString("content"));
				oVistaTO.setImage(oCachedRowSet.getString("image"));
				oVistaTO.setStatus(oCachedRowSet.getString("status"));
				oVistaTO.setTipo(oCachedRowSet.getString("tipo"));
				lista.add(oVistaTO);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de VistaDAO.listVistaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		}
		return lista;
	}
	

}
