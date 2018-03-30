package com.jade.util.lbda;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.betcesc.game.common.Constants;

public class Conexion {

	static Logger log = Logger.getLogger(Conexion.class);

	static DataSource ds = null;

	public Conexion() {
		E = "";
		D = "";
		B = "";
		C = "";
		A = new Conector();
	}

	public Connection iniciarconexion() throws ConexionManejaError {
		Connection connection = null;
		if (ds == null) {
			try {

				Context initCtx = new InitialContext();

				Context envCtx = (Context) initCtx.lookup("java:comp/env");

				ds = (DataSource) envCtx.lookup("jdbc/game");
			} catch (Throwable t) {
				log.fatal("Error accesando al DataSource", t);
			}

		}

		try {
//			cargarInfoBd();
	//		Class.forName(E);
			connection = ds.getConnection();
			//connection.createStatement().execute("SET SESSION time_zone = '"+Constants.TIME_ZONE+ "'");
			// connection = DriverManager.getConnection(D, B, C);
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw new ConexionManejaError("Excepcion Encontrada: No es posible obtener una conexion a la base de datos. Package: com.jade.util.basededatos Metodo: iniciarconexion() Clase: Conexion Estado: " + sqlexception.getMessage());
		} catch (Exception exception) {
			throw new ConexionManejaError("Excepcion Encontrada: No es posible obtener una conexion a la base de datos. Package: com.jade.util.basededatos Metodo: iniciarconexion() Clase: Conexion Estado: " + exception.getMessage());
		}
		return connection;
	}

	public void cerrarConexion(Connection connection) throws ConexionManejaError {
		try {
			// log.info("Cerrando la conexion");
			if (connection != null && !connection.isClosed()) {
				try {
					if (connection.getAutoCommit() == false) {
						connection.commit();
						connection.setAutoCommit(true);
					}
				} catch (Exception e) {
					log.info("No pude hacer commit");
				}
				if (!connection.isClosed()) {
					connection.close();
				}
				// log.info("se cerro la conexion");
			}
		} catch (Exception exception) {
			throw new ConexionManejaError("Excepcion Encontrada: No es posible liberar la conexion con la base de datos. Package: com.jade.util.basededatos Metodo: cerrarConexion Clase: Conexion Estado: " + exception.getMessage());
		}
	}

	public void rollback(Connection connection) throws ConexionManejaError {
		try {
			// log.info("Cerrando la conexion");
			if (connection != null && !connection.isClosed()) {
				if (!connection.isClosed()) {
					connection.rollback();
					connection.close();
				}
				// log.info("se cerro la conexion");
			}
		} catch (Exception exception) {
			throw new ConexionManejaError("Excepcion Encontrada: No es posible liberar la conexion con la base de datos. Package: com.jade.util.basededatos Metodo: cerrarConexion Clase: Conexion Estado: " + exception.getMessage());
		}
	}

	public void cargarInfoBd() {
		A.iniciarConector();
		E = A.getJdbcDriver();
		D = A.getURL();
		B = A.getNombreUsuario();
		C = A.getPassword();
	}

	private String E;
	private String D;
	private String B;
	private String C;
	private Conector A;
}
