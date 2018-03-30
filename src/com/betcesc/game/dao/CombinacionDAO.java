package com.betcesc.game.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.to.CombinacionTO;
import com.jade.util.lbda.EjecutorSql;

/**
 * Administracion de la tabla combinacion.
 * 
 * @author jrivero
 * 
 * Esta clase permite la actualizacion de la tabla combinacion
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class CombinacionDAO {
	private static final Log log = LogFactory.getLog(CombinacionDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public CombinacionDAO() {
		super();
		oEjecutorSql = new EjecutorSql();
	}

	public CombinacionDAO(EjecutorSql oEjecutorSql) {
		super();
		this.oEjecutorSql = oEjecutorSql;

	}
	
	/**
	 * 
	 * Inserta un registro en la tabla combinacion
	 * 
	 */
	public int insertarCombinacionDAO(CombinacionTO oCombinacionTO) throws Exception {
		log.info("Iniciando ejecucion de CombinacionDAO.insertarCombinacionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try {
			oParametros.add(oCombinacionTO.getIdJugada());
			oParametros.add(oCombinacionTO.getCombinacion());
			oParametros.add(oCombinacionTO.getIdUsuario());
			oParametros.add(oCombinacionTO.getMontoApuesta());

			strBuffquery.append("INSERT INTO combinacion VALUES (?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
		} catch (Exception e) {
			log.info("Error en la ejecucion de CombinacionDAO.insertarCombinacionDAO");
			e.printStackTrace();
			throw e;
		}
		return numRegAct;
	}


	/**
	 * 
	 * Consulta una lista de registros en la tabla combinacion
	 * 
	 */
	public CachedRowSet listaCombinacionPorUsuarioDAO(CombinacionTO oCombinacionTO) throws Exception {
		log.info("Iniciando ejecucion de CombinacionDAO.listaCombinacionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try {
			
			oParametros.add(oCombinacionTO.getIdUsuario());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT * ");
			strBuffquery.append("FROM combinacion ");
			strBuffquery.append("WHERE id_usuario=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		} catch (Exception e) {
			log.info("Error en la ejecucion de CombinacionDAO.listaCombinacionDAO");
			e.printStackTrace();
			throw e;
		}
		return oCachedRowSet;
	}
	
	/**
	 * 
	 * Consulta una lista de registros en la tabla combinacion
	 * 
	 */
	public double[] montoCombinacionPorUsuarioDAO(CombinacionTO oCombinacionTO) throws Exception {
		log.info("Iniciando ejecucion de CombinacionDAO.listaCombinacionDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		double montoApuesta = 0;
		double[] montos = new double[2];
		try {
			
			oParametros.add(oCombinacionTO.getCombinacion());
			oParametros.add(oCombinacionTO.getIdUsuario());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT sum(monto_apuesta) ");
			strBuffquery.append("FROM combinacion ");
			strBuffquery.append("WHERE combinacion=? AND id_usuario=? ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			
			if(oCachedRowSet.next()) {
				montoApuesta = oCachedRowSet.getDouble(1);
			}

		} catch (Exception e) {
			log.info("Error en la ejecucion de CombinacionDAO.listaCombinacionDAO");
			e.printStackTrace();
			throw e;
		}
		montos[0]=montoApuesta;
		
		return montos;
	}
	

}
