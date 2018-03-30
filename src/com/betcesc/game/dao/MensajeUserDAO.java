package com.betcesc.game.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.MensajeUserIF;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla liga.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla liga
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class MensajeUserDAO {
	private static final Log log = LogFactory.getLog(MensajeUserDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public MensajeUserDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public MensajeUserDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;
	}

	/**
	 * 
	 * Inserta un registro en la tabla liga
	 * 
	 */
	public int insertarMensajeUserDAO(MensajeUserIF oMensajeUserTO) throws Exception {
		log.info("Iniciando ejecucion de MensajeUserDAO.insertarMensajeUserDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oMensajeUserTO.setIdMensaje(Constants.isNull(oMensajeUserTO.getIdMensaje(),"0"));
			oMensajeUserTO.setFechaSis(Constants.getFechaLargaSQL());
			
			oParametros.add(oMensajeUserTO.getIdMensaje());
			oParametros.add(oMensajeUserTO.getFechaSis());
			oParametros.add(oMensajeUserTO.getIdUsuario());
			oParametros.add(oMensajeUserTO.getIdUserTo());
			oParametros.add(oMensajeUserTO.getFechaView());
			oParametros.add(oMensajeUserTO.getMensaje());

			strBuffquery.append("INSERT INTO mensaje_user VALUES (?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			
			Constants.MENSAJES_POR_USUARIO=null;
		} catch (Exception e) {
			log.info("Error en la ejecucion de MensajeUserDAO.insertarMensajeUserDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Actualiza un registro en la tabla liga
	 * 
	 */
	public int actualizarMensajeUserDAO(MensajeUserIF oMensajeUserTO) throws Exception {
		log.info("Iniciando ejecucion de MensajeUserDAO.actualizarMensajeUserDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try {
			oMensajeUserTO.setFechaView(Constants.getFechaLargaSQL());

			oParametros.add(oMensajeUserTO.getFechaView());
			oParametros.add(oMensajeUserTO.getIdMensaje());
			
			strBuffquery.append("UPDATE mensaje_user SET ");
			strBuffquery.append("fecha_view=? ");
			strBuffquery.append("WHERE  id_mensaje = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de MensajeUserDAO.actualizarMensajeUserDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}

	/**
	 * 
	 * Consulta una lista de registros en la tabla liga
	 * 
	 */
	public CachedRowSet listaMensajeUserDAO() throws Exception {
		log.info("Iniciando ejecucion de MensajeUserDAO.listaMensajeUserDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {

			strBuffquery.append("SELECT a.id_mensaje,a.fecha_sis,a.id_usuario,a.id_user_to,a.fecha_view,a.mensaje,b.usuario ");
			strBuffquery.append("FROM mensaje_user a, usuario b ");
			strBuffquery.append("WHERE a.id_usuario=b.id_usuario AND a.fecha_view IS NULL ");
			strBuffquery.append("ORDER BY fecha_sis ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
		} catch (Exception e) {
			log.info("Error en la ejecucion de MensajeUserDAO.listaMensajeUserDAO");
			e.printStackTrace();
			throw e;
		}
		return oCachedRowSet;
	}
	
	


}
