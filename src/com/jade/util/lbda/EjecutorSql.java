package com.jade.util.lbda;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import sun.jdbc.rowset.CachedRowSet;

public class EjecutorSql
    {

	private Conexion B;
	private CachedRowSet A;
	private Connection connection = null;
	private PreparedStatement preparedstatement = null;
	private int generatedKey = 0;
	private StringBuffer consulta = new StringBuffer();
	private static Logger log = Logger.getLogger(EjecutorSql.class);

	public EjecutorSql()
	    {
		// log.info("Iniciando ejecutor personalizado");
		B = new Conexion();
		A = null;
	    }

	public Connection getConnection() throws ConexionManejaError, Exception
	    {
		if (connection == null || connection.isClosed())
		    {
			connection = B.iniciarconexion();
		    }
		return connection;
	    }

	public boolean getAutoCommit() throws SQLException
	    {
		if (connection == null)
		    {
			return true;
		    }
		return connection.getAutoCommit();
	    }

	public void close() throws Exception
	    {
		B.cerrarConexion(connection);
	    }

	public void rollback() throws Exception
	    {
		B.rollback(connection);
	    }

	public CachedRowSet ejecutaQuery(String s, ArrayList arraylist) throws EjecutorSqlManejaError, Exception
	    {
		Connection connection = null;
		Object obj = null;
		Object obj1 = null;
		try
		    {
			A = new CachedRowSet();
			connection = getConnection();

			log.info("Ejecutando " + s);
			preparedstatement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
			if (arraylist != null)
			    {
				for (int i = 0; i < arraylist.size(); i++)
				    {
					preparedstatement.setObject(i + 1, arraylist.get(i));
					log.info("ID" + (i + 1) + " --- " + arraylist.get(i));
				    }

			    }
			consulta.setLength(0);
			consulta.append(preparedstatement.toString());

			long t1 = System.currentTimeMillis();

			ResultSet resultset = preparedstatement.executeQuery();
			long t2 = System.currentTimeMillis();

			long tiempo = t2 - t1;

			if (tiempo > 10 * 1000)
			    {
				log.warn("Query Retardado " + tiempo / 1000 + " Segundos  [" + consulta.toString() + "]");
			    }

			A.populate(resultset);

			if (log.isInfoEnabled())
			    {

				StringBuffer strDebug = new StringBuffer();
				for (int i = 1; i <= A.getMetaData().getColumnCount(); i++)
				    {
					strDebug.append(A.getMetaData().getColumnName(i) + "| ");
				    }

				log.info(strDebug);
			    }

			resultset.last();
			log.info("Cantidad de Records  " + resultset.getRow());

			resultset.close();
			preparedstatement.close();
			log.debug("Ejecutando " + s);
		    } catch (Exception exception)
		    {
			if (!getAutoCommit())
			    {
				log.info("Se realizo un roll back");
				connection.rollback();
			    }

			throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: " + s + "Package: com.jade.util.basededatos " + "Metodo: ejecutaQuery " + "Clase: EjecutorSql " + "Estatus: " + exception.getMessage());
		    } finally
		    {
			try
			    {
				if (getAutoCommit())
				    {
					try
					    {
						B.cerrarConexion(connection);
					    } catch (ConexionManejaError conexionmanejaerror)
					    {
						throw new EjecutorSqlManejaError("Excepcion al liberar la conexion con la base de datos Package: com.jade.util.basededatos Metodo: ejecutaQuery Clase: EjecutorSql Estatus: " + conexionmanejaerror.getMessage());
					    }
				    } else
				    {
					// log.info("Sigue abierta la conexion");
				    }
			    } catch (Exception e)
			    {
				throw new Exception("Error al pedir la conextion de la base de datos Package: com.jade.util.basededatos Metodo: ejecutaQuery Clase: EjecutorSql Estatus: " + e.getMessage());
			    }
		    }
		return A;
	    }

	public int ejecutaSqlRetornaNumRegAct(String s, ArrayList arraylist) throws EjecutorSqlManejaError, Exception
	    {
		int i;
		Connection connection = null;
		Object obj = null;
		i = 0;
		try
		    {
			connection = getConnection();
			preparedstatement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
			if (arraylist != null)
			    {
				for (int j = 0; j < arraylist.size(); j++)
				    preparedstatement.setObject(j + 1, arraylist.get(j));

			    }
			consulta.setLength(0);
			consulta.append(preparedstatement.toString());

			long t1 = System.currentTimeMillis();

			i = preparedstatement.executeUpdate();
			long t2 = System.currentTimeMillis();

			long tiempo = t2 - t1;

			if (tiempo > 10 * 1000)
			    {
				log.warn("Timepo excedido de query " + tiempo + "[" + consulta.toString() + "]");
			    }

			ResultSet rs = preparedstatement.getGeneratedKeys();
			if (rs.next())
			    {
				setGeneratedKey(rs.getInt(1));
			    }
			log.debug("Ejecutando " + consulta.toString());

		    } catch (SQLException exception)
		    {
			if (!getAutoCommit())
			    {
				log.info("Se realizo un roll back");
				connection.rollback();
			    }
			log.info(consulta.toString());
			throw new EjecutorSqlManejaError("ERROR CODE:" + exception.getErrorCode() + " QUERY: " + preparedstatement.toString() + " Estatus: " + exception.getMessage());
		    } catch (Exception exception)
		    {
			if (!getAutoCommit())
			    {
				log.info("Se realizo un roll back");
				connection.rollback();
			    }
			throw new EjecutorSqlManejaError("ERROR: Query: " + preparedstatement.toString() + " Estatus: " + exception.getMessage());
		    } finally
		    {
			try
			    {
				if (getAutoCommit())
				    {
					try
					    {
						B.cerrarConexion(connection);
					    } catch (ConexionManejaError conexionmanejaerror)
					    {
						throw new EjecutorSqlManejaError("Excepcion al liberar la conexion con la base de datos Package: com.jade.util.basededatos Metodo: ejecutaSqlRetornaNumRegAct Clase: EjecutorSql Estatus: " + conexionmanejaerror.getMessage());
					    }
				    } else
				    {
					// log.info("Sigue abierta la conexion");
				    }
			    } catch (Exception e)
			    {
				throw new Exception("Error al pedir la conextion de la base de datos Package: com.jade.util.basededatos Metodo: ejecutaSqlRetornaNumRegAct Clase: EjecutorSql Estatus: " + e.getMessage());
			    }
		    }
		return i;
	    }

	public int[] ejecutaSqlBatch(ArrayList arraylist) throws EjecutorSqlManejaError
	    {
		int i[];
		Object obj = null;
		Connection connection = null;
		boolean error = false;
		try
		    {
			connection = getConnection();
			Statement stmt = connection.createStatement();
			if (arraylist != null)
			    {
				for (int j = 0; j < arraylist.size(); j++)
				    {
					stmt.addBatch(String.valueOf(arraylist.get(j)));
				    }
			    } else
			    {
				throw new SQLException("No hay intrucciones por ejecutar para este Batch ");
			    }
			i = stmt.executeBatch();
		    } catch (SQLException sqlexception)
		    {
			error = true;
			throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: BATCH " + sqlexception.getMessage());
		    } catch (Exception exception)
		    {
			error = true;
			throw new EjecutorSqlManejaError("Ocurrio una Excepcion al ejecutar el Query: BATCH " + "Clase: EjecutorSql " + "Estatus: " + exception.getMessage());
		    } finally
		    {
			try
			    {
				if (getAutoCommit())
				    {
					try
					    {
						B.cerrarConexion(connection);
					    } catch (ConexionManejaError conexionmanejaerror)
					    {
						throw new EjecutorSqlManejaError("Excepcion al liberar la conexion con la base de datos Package: com.jade.util.basededatos Metodo: ejecutaSqlRetornaNumRegAct Clase: EjecutorSql Estatus: " + conexionmanejaerror.getMessage());
					    }
				    } else
				    {
					// log.info("Sigue abierta la conexion");
				    }
			    } catch (Exception conexionmanejaerror)
			    {
				throw new EjecutorSqlManejaError("Excepcion al liberar la conexion con la base de datosPackage: com.integris.util.basededatos Metodo: ejecutaSqlRetornaNumRegAct Clase: EjecutorSql Estatus: " + conexionmanejaerror.getMessage());
			    }
		    }
		return i;
	    }

	/**
	 * @return Returns the generatedKey.
	 */
	public int getGeneratedKey()
	    {
		return generatedKey;
	    }

	/**
	 * @param generatedKey
	 *            The generatedKey to set.
	 */
	public void setGeneratedKey(int generatedKey)
	    {
		this.generatedKey = generatedKey;
	    }

	public void printQuery(String query, ArrayList parametros)
	    {

		for (int i = 0; i < parametros.size(); i++)
		    {
			query = query.replaceFirst("\\?", "'" + parametros.get(i).toString().trim() + "'");
		    }
		log.debug(query);

	    }

	public void saveImage(String fileName, File file)
	    {

		Connection connection = null;
		boolean error = false;
		try
		    {
			System.err.println("Guardando imagen" + fileName);
			System.err.println("Guardando imagen FILE" + file.getName());
			connection = getConnection();
			Statement stmt = connection.createStatement();

			stmt.executeUpdate("DELETE from imagenes where nombre='" + fileName + "'");
			stmt.close();
			FileInputStream fis = null;
			PreparedStatement ps = null;
			try
			    {
				connection.setAutoCommit(false);

				fis = new FileInputStream(file);
				ps = connection.prepareStatement("INSERT INTO imagenes (nombre, imagen) values (?, ?)");
				ps.setString(1, fileName);


				ps.setBinaryStream(2, fis, (int) file.length());
				ps.executeUpdate();
				connection.commit();

			    } catch (Exception e)
			    {
				log.error(e);

			    } finally
			    {
				ps.close();
				fis.close();
			    }

		    } catch (Exception e)
		    {
			log.error("Error guardando imagen", e);
		    }

	    }

	public void deleteImage(String fileName)
	    {

		Connection connection = null;
		boolean error = false;
		try
		    {
			connection = getConnection();
			Statement stmt = connection.createStatement();

			stmt.executeUpdate("DELETE from imagenes where nombre='" + fileName + "'");
			log.info("DELETE from imagenes where nombre='" + fileName + "'");
			stmt.close();

		    } catch (Exception e)
		    {
			log.error("Error guardando imagen", e);
		    }

	    }
    }
