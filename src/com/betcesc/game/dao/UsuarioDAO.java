/*
 * Proyecto Betcesc.com - Sistema de Jugadas Deportivas.
 * Fecha: 23/04/2010 -  08:50:23
 * 
 * Copyright (C) Betcesc, 2010. Caracas, Venezuela. All rights
 * reserved. Todos los derechos reservados.
 */

package com.betcesc.game.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.UsuarioTO;
import com.jade.util.lbda.EjecutorSql;
import com.jade.util.lbda.EjecutorSqlManejaError;

/**
 * Administracion de la tabla usuario.
 * 
 * @author jrivero
 * 
 *         Esta clase permite la actualizacion de la tabla usuario
 * 
 * @see EjecutorSql
 * @see CachedRowSet
 * 
 */

public class UsuarioDAO
    {
	private static final Log log = LogFactory.getLog(UsuarioDAO.class);
	private EjecutorSql oEjecutorSql = new EjecutorSql();
	private StringBuffer strBuffquery = new StringBuffer();
	public CachedRowSet oCachedRowSet = null;

	/**
	 * El constructor no tiene parámetros.
	 */
	public UsuarioDAO()
	    {
		super();
		oEjecutorSql = new EjecutorSql();
	    }

	public UsuarioDAO(EjecutorSql oEjecutorSql)
	    {
		super();
		this.oEjecutorSql = oEjecutorSql;
	    }

	/**
	 * 
	 * Inserta un registro en la tabla usuario
	 * 
	 */
	public int insertarUsuarioDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.insertarUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try
		    {
			// parametros iniciales
			oUsuarioTO.setIdUsuario(oUsuarioTO.getIdUsuario().trim().equals("") ? "0" : oUsuarioTO.getIdUsuario());
			oUsuarioTO.setDiasVencTicket(oUsuarioTO.getDiasVencTicket().trim().equals("") ? "0" : oUsuarioTO.getDiasVencTicket());
			oUsuarioTO.setLogrosAltaBaja(oUsuarioTO.getLogrosAltaBaja().trim().equals("") ? "0" : oUsuarioTO.getLogrosAltaBaja());
			oUsuarioTO.setMonto(oUsuarioTO.getMonto().trim().equals("") ? "0" : oUsuarioTO.getMonto());
			oUsuarioTO.setTipo(oUsuarioTO.getTipo() == null || oUsuarioTO.getTipo().trim().equals("") ? "0" : oUsuarioTO.getTipo());
			oUsuarioTO.setValidarMacAddress(oUsuarioTO.getValidarMacAddress() == null || oUsuarioTO.getValidarMacAddress().trim().equals("") ? "0" : oUsuarioTO.getValidarMacAddress());
			oUsuarioTO.setTopePorDerecho(Constants.parseDouble(oUsuarioTO.getTopePorDerecho()));
			oUsuarioTO.setActivarBono(Constants.parseInt(oUsuarioTO.getActivarBono()));
			oUsuarioTO.setLogrosMin(oUsuarioTO.getLogrosMin() == null || oUsuarioTO.getLogrosMin().trim().equals("") ? Constants.LOGROS_MINIMO_AUTOREGISTRO : oUsuarioTO.getLogrosMin());
			oUsuarioTO.setTopePorCombinacion(Constants.parseDouble(oUsuarioTO.getTopePorCombinacion()));
			oUsuarioTO.setEliminarJugada(Constants.parseInt(oUsuarioTO.getEliminarJugada()));
			oUsuarioTO.setPagoVeces(Constants.isNull(oUsuarioTO.getPagoVeces(), ""));
			oUsuarioTO.setDominio(Constants.isNull(oUsuarioTO.getDominio(), "000"));

			oParametros.add(oUsuarioTO.getIdUsuario());
			oParametros.add(oUsuarioTO.getCedula());
			oParametros.add(oUsuarioTO.getApellido());
			oParametros.add(oUsuarioTO.getNombre());
			oParametros.add(oUsuarioTO.getUsuario());
			oParametros.add(oUsuarioTO.getClave());
			oParametros.add(oUsuarioTO.getCorreo());
			oParametros.add(oUsuarioTO.getDiasVencTicket());
			oParametros.add(oUsuarioTO.getCentroHipico());
			oParametros.add(oUsuarioTO.getRif());
			oParametros.add(oUsuarioTO.getTelefono());
			oParametros.add(oUsuarioTO.getCelular());
			oParametros.add(oUsuarioTO.getBanco());
			oParametros.add(oUsuarioTO.getNumeroCuenta());
			oParametros.add(oUsuarioTO.getTitularCuenta());
			oParametros.add(oUsuarioTO.getLogrosAltaBaja());
			oParametros.add(oUsuarioTO.getLogrosCalc());
			oParametros.add(oUsuarioTO.getMonto());
			oParametros.add(oUsuarioTO.getIdRol());
			oParametros.add(oUsuarioTO.getIdStatus());
			oParametros.add(oUsuarioTO.getIdTipoCuenta());
			oParametros.add(oUsuarioTO.getIdSupervisor());
			oParametros.add(oUsuarioTO.getTicketNota());
			oParametros.add(oUsuarioTO.getComisionVenta() != null && !oUsuarioTO.getComisionVenta().trim().equals("") ? oUsuarioTO.getComisionVenta() : "0");
			oParametros.add(oUsuarioTO.getOtrosGastos() != null && !oUsuarioTO.getOtrosGastos().trim().equals("") ? oUsuarioTO.getOtrosGastos() : "0");
			oParametros.add(oUsuarioTO.getTipo());
			oParametros.add(oUsuarioTO.getMacAddress());
			oParametros.add(oUsuarioTO.getValidarMacAddress());
			oParametros.add(oUsuarioTO.getLogrosMin());
			oParametros.add(oUsuarioTO.getActivarSuperRunline());
			oParametros.add(oUsuarioTO.getTopePorDerecho());
			oParametros.add(oUsuarioTO.getActivarBono());
			oParametros.add(oUsuarioTO.getApuestaEquipo());
			oParametros.add(oUsuarioTO.getComisionVentaParley() != null && !oUsuarioTO.getComisionVentaParley().trim().equals("") ? oUsuarioTO.getComisionVentaParley() : "0");
			oParametros.add(oUsuarioTO.getOtrosGastosParley() != null && !oUsuarioTO.getOtrosGastosParley().trim().equals("") ? oUsuarioTO.getOtrosGastosParley() : "0");
			oParametros.add(oUsuarioTO.getTopePorCombinacion());
			oParametros.add(oUsuarioTO.getEliminarJugada());
			oParametros.add(oUsuarioTO.getPagoVeces());
			oParametros.add(oUsuarioTO.getDominio());
			oParametros.add(Constants.isNull(oUsuarioTO.getPagoClave(), "0"));
			oParametros.add(Constants.isNull(oUsuarioTO.getAbrirJuego(), "0"));
			oParametros.add(Constants.isNull(oUsuarioTO.getVencidoReporte(), "1"));
			oParametros.add(oUsuarioTO.getJugadaMinima());

			strBuffquery.append("INSERT INTO usuario VALUES (?,?,?,?,?,MD5(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			oUsuarioTO.setIdUsuario(String.valueOf(oEjecutorSql.getGeneratedKey()));

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.insertarUsuarioDAO");
			log.error("Error:" + e.getMessage());
			e.printStackTrace();
			throw e;
		    }
		return numRegAct;
	    }

	/**
	 * 
	 * Actualiza un registro en la tabla usuario
	 * 
	 */
	public int actualizarUsuarioDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.actualizarUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try
		    {
			oUsuarioTO.setLogrosAltaBaja(oUsuarioTO.getLogrosAltaBaja().trim().equals("") ? "0" : oUsuarioTO.getLogrosAltaBaja());
			oUsuarioTO.setMonto(oUsuarioTO.getMonto().trim().equals("") ? "0" : oUsuarioTO.getMonto());
			oUsuarioTO.setTipo(oUsuarioTO.getTipo().trim().equals("") ? "0" : oUsuarioTO.getTipo());
			oUsuarioTO.setTopePorDerecho(Constants.parseDouble(oUsuarioTO.getTopePorDerecho()));
			oUsuarioTO.setActivarBono(Constants.parseInt(oUsuarioTO.getActivarBono()));
			oUsuarioTO.setPagoVeces(Constants.isNull(oUsuarioTO.getPagoVeces(), ""));
			oUsuarioTO.setDominio(Constants.isNull(oUsuarioTO.getDominio(), "000"));

			oParametros.add(oUsuarioTO.getCedula());
			oParametros.add(oUsuarioTO.getApellido());
			oParametros.add(oUsuarioTO.getNombre());
			oParametros.add(oUsuarioTO.getUsuario());
			oParametros.add(oUsuarioTO.getClave());
			oParametros.add(oUsuarioTO.getClave()); // se repite la clave

			oParametros.add(oUsuarioTO.getCorreo());
			oParametros.add(oUsuarioTO.getDiasVencTicket());
			oParametros.add(oUsuarioTO.getCentroHipico());
			oParametros.add(oUsuarioTO.getRif());
			oParametros.add(oUsuarioTO.getTelefono());
			oParametros.add(oUsuarioTO.getCelular());
			oParametros.add(oUsuarioTO.getBanco());
			oParametros.add(oUsuarioTO.getNumeroCuenta());
			oParametros.add(oUsuarioTO.getTitularCuenta());
			oParametros.add(oUsuarioTO.getLogrosAltaBaja());
			oParametros.add(oUsuarioTO.getLogrosCalc());
			oParametros.add(oUsuarioTO.getMonto());
			oParametros.add(oUsuarioTO.getIdRol());
			oParametros.add(oUsuarioTO.getIdStatus());
			oParametros.add(oUsuarioTO.getIdTipoCuenta());
			oParametros.add(oUsuarioTO.getIdSupervisor());
			oParametros.add(oUsuarioTO.getTicketNota());
			oParametros.add(oUsuarioTO.getComisionVenta());
			oParametros.add(oUsuarioTO.getOtrosGastos());
			oParametros.add(oUsuarioTO.getTipo());
			oParametros.add(oUsuarioTO.getValidarMacAddress().equals("1") ? oUsuarioTO.getMacAddress() : "");
			oParametros.add(oUsuarioTO.getValidarMacAddress());
			oParametros.add(oUsuarioTO.getLogrosMin());
			oParametros.add(oUsuarioTO.getActivarSuperRunline());
			oParametros.add(oUsuarioTO.getTopePorDerecho());
			oParametros.add(oUsuarioTO.getActivarBono());
			oParametros.add(oUsuarioTO.getApuestaEquipo());
			oParametros.add(oUsuarioTO.getComisionVentaParley() != null && !oUsuarioTO.getComisionVentaParley().trim().equals("") ? oUsuarioTO.getComisionVentaParley() : "0");
			oParametros.add(oUsuarioTO.getOtrosGastosParley() != null && !oUsuarioTO.getOtrosGastosParley().trim().equals("") ? oUsuarioTO.getOtrosGastosParley() : "0");
			oParametros.add(oUsuarioTO.getTopePorCombinacion());
			oParametros.add(oUsuarioTO.getEliminarJugada());
			oParametros.add(oUsuarioTO.getPagoVeces());
			oParametros.add(oUsuarioTO.getDominio());
			oParametros.add(Constants.isNull(oUsuarioTO.getPagoClave(), "0"));
			oParametros.add(Constants.isNull(oUsuarioTO.getAbrirJuego(), "0"));
			oParametros.add(Constants.isNull(oUsuarioTO.getVencidoReporte(), "1"));
			oParametros.add(oUsuarioTO.getJugadaMinima());

			oParametros.add(oUsuarioTO.getIdUsuario()); // primary key

			strBuffquery.append("UPDATE usuario SET ");
			strBuffquery.append("cedula=? , apellido=?, nombre=?, ");
			strBuffquery.append("usuario=? , clave=if(clave=?,clave,MD5(?)), correo=?, ");
			strBuffquery.append("dias_venc_ticket=? , centro_hipico=?, rif=?, ");
			strBuffquery.append("telefono=? , celular=?, banco=?, ");
			strBuffquery.append("numero_cuenta=? , titular_cuenta=?, logros_alta_baja=?, logros_calc=?,");
			strBuffquery.append("monto=? , id_rol=?, id_status=?, ");
			strBuffquery.append("id_tipo_cuenta=? , id_supervisor=?, ticket_nota=?, comision_venta=?, otros_gastos=?, ");
			strBuffquery.append("tipo = ?, mac_address=?, validar_mac_address=?, logros_min=?, activar_super_runline=?, ");
			strBuffquery.append("tope_por_derecho=?, activar_bono=?, apuesta_equipo=?, comision_venta_parley=?, otros_gastos_parley=?, ");
			strBuffquery.append("tope_por_combinacion=?, eliminar_jugada=?, pago_veces=?, dominio=?, pago_clave=?, ");
			strBuffquery.append("abrir_juego=?, vencido_reporte=?, jugada_minima=? ");
			strBuffquery.append("WHERE  id_usuario = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			// si estoy descativando el usuario, elimino sus mensajes
			if (oUsuarioTO.getIdStatus().equals(Constants.STATUS_USUARIO_INACTIVO))
			    {
				strBuffquery.setLength(0);
				strBuffquery.append("DELETE FROM mensaje_user WHERE id_user_to = ").append(oUsuarioTO.getIdUsuario());
				numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), null);
			    }

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.actualizarUsuarioDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return numRegAct;
	    }

	/**
	 * 
	 * Actualiza un registro en la tabla usuario
	 * 
	 */
	public int actualizarUsuarioSoloClaveDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.actualizarUsuarioSoloClaveDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try
		    {
			oParametros.add(oUsuarioTO.getClave());
			oParametros.add(oUsuarioTO.getIdUsuario()); // primary key

			strBuffquery.append("UPDATE usuario SET clave=MD5(?) WHERE  id_usuario = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.actualizarUsuarioSoloClaveDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return numRegAct;
	    }

	/**
	 * 
	 * Actualiza un registro en la tabla usuario
	 * 
	 */
	public int actualizarUsuarioMacAddressDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.actualizarUsuarioMacAddressDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;

		try
		    {
			oParametros.add(oUsuarioTO.getMacAddress());
			oParametros.add(oUsuarioTO.getIdUsuario()); // primary key

			strBuffquery.append("UPDATE usuario SET mac_address=? WHERE  id_usuario = ? ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.actualizarUsuarioMacAddressDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return numRegAct;
	    }

	/**
	 * 
	 * Actualiza un registro en la tabla usuario
	 * 
	 */
	public int actualizarMenuUsuarioDAO(String idUsuario, String menues, String usuarios) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.actualizarMenuUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		String[] menu = menues.split(",");
		String[] usuario = usuarios.split(",");
		String sep = "";
		boolean procesar = false;

		try
		    {
			oParametros.add(idUsuario);

			// eliminamos las menues anteriores del usuario
			strBuffquery.setLength(0);
			strBuffquery.append("DELETE FROM menu_user WHERE id_usuario=?");
			oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			// eliminamos los usuarios asignados actualmente
			strBuffquery.setLength(0);
			strBuffquery.append("DELETE FROM usuario_admin WHERE id_admin=?");
			oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

			// insertamos las nuevas menues del usuario
			procesar = false;
			oParametros = new ArrayList();
			strBuffquery.setLength(0);
			strBuffquery.append("INSERT INTO menu_user (id_menu,id_usuario) VALUES");
			for (int i = 0; i < menu.length && !menues.trim().equals(""); i++)
			    {
				strBuffquery.append(sep).append("(?,?)");
				oParametros.add(menu[i]);
				oParametros.add(idUsuario);
				sep = ",";
				procesar = true;
			    }
			if (procesar)
			    {
				numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			    }

			// insertamos los nuevos usuarios asignados
			procesar = false;
			oParametros = new ArrayList();
			sep = "";
			strBuffquery.setLength(0);
			strBuffquery.append("INSERT INTO usuario_admin (id_usuario,id_admin) VALUES");
			for (int i = 0; i < usuario.length && !usuarios.trim().equals(""); i++)
			    {
				strBuffquery.append(sep).append("(?,?)");
				oParametros.add(usuario[i]);
				oParametros.add(idUsuario);
				sep = ",";
				procesar = true;
			    }
			if (procesar)
			    {
				numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);
			    }

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.actualizarMenuUsuarioDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return numRegAct;
	    }

	/**
	 * 
	 * Elimina un registro en la tabla usuario
	 * 
	 */
	public int eliminarUsuarioDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.eliminarUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		try
		    {

			oParametros.add(oUsuarioTO.getIdUsuario());
			strBuffquery.append("DELETE FROM usuario  WHERE  (id_usuario = ?) ");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.eliminarUsuarioDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return numRegAct;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaUsuarioDAO(UsuarioIF oUsuarioTO, int orden, String idUsuario) throws Exception
	    {
		return listaUsuarioDAO(oUsuarioTO, orden, idUsuario, null);
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaUsuarioDAO(UsuarioIF oUsuarioTO, int orden, String idUsuario, UsuarioTO usuarioFiltro) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		boolean isWhere = false;
		strBuffquery.setLength(0);
		try
		    {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.*,r.*, s.*, sup.usuario as supervisor, if(a.id_usuario is not null,a.id_usuario,0) As activo  ");
			strBuffquery.append("FROM usuario u LEFT OUTER JOIN rol r ON u.id_rol=r.id_rol ");
			strBuffquery.append("LEFT OUTER JOIN status s ON u.id_status=s.id_status ");
			strBuffquery.append("LEFT OUTER JOIN usuario sup ON sup.id_usuario=u.id_supervisor ");
			if (oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				oParametros.add(idUsuario);
				strBuffquery.append("LEFT OUTER JOIN usuario_admin a ON u.id_usuario=a.id_usuario AND a.id_admin=? ");
			    } else
			    {
				oParametros.add(oUsuarioTO.getIdUsuario());
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("INNER JOIN usuario_admin a ON (u.id_usuario=a.id_usuario AND a.id_admin=? OR u.id_supervisor=a.id_usuario AND a.id_admin=?) ");
			    }

			if (usuarioFiltro != null)
			    {
				if (usuarioFiltro.getDominio() != null && !usuarioFiltro.getDominio().trim().equals(""))
				    {
					strBuffquery.append(!isWhere ? "WHERE" : "AND").append(" u.dominio='").append(usuarioFiltro.getDominio()).append("' ");
					isWhere = true;
				    }
				if (usuarioFiltro.getUsuario() != null && !usuarioFiltro.getUsuario().trim().equals(""))
				    {
					strBuffquery.append(!isWhere ? "WHERE" : "AND").append(" u.usuario like '%").append(usuarioFiltro.getUsuario()).append("%' ");
					isWhere = true;
				    }
			    }

			switch (orden) {
			case 1:
			    strBuffquery.append("ORDER BY UPPER(u.apellido) ");
			    break;
			case 2:
			    strBuffquery.append("ORDER BY UPPER(u.nombre) ");
			    break;
			case 3:
			    strBuffquery.append("ORDER BY UPPER(r.desc_rol),u.tipo ");
			    break;
			case 4:
			    strBuffquery.append("ORDER BY UPPER(s.desc_status) ");
			    break;
			case 5:
			    strBuffquery.append("ORDER BY UPPER(u.usuario) ");
			    break;
			case 6:
			    strBuffquery.append("ORDER BY UPPER(sup.usuario),UPPER(u.usuario) ");
			    break;
			case 7:
			    strBuffquery.append("ORDER BY u.dominio,UPPER(u.usuario) ");
			    break;
			default:
			    strBuffquery.append("ORDER BY UPPER(u.apellido) ");
			}

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				Constants.ULTIMA_JUGADA = usuarioUltimaJugada();
			    }

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaUsuarioDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaPaginaUsuarioDAO(UsuarioIF oUsuarioTO, int orden, String idUsuario, UsuarioTO usuarioFiltro) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaPaginaUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		boolean isWhere = false;
		strBuffquery.setLength(0);
		try
		    {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.id_usuario, u.apellido, u.nombre, u.usuario, sup.usuario As supervisor, ");
			strBuffquery.append("u.dominio, u.tipo, r.id_rol, r.desc_rol, s.desc_status, ");
			strBuffquery.append("if(a.id_usuario is not null,a.id_usuario,0) As activo ");
			strBuffquery.append("FROM usuario u LEFT OUTER JOIN rol r ON u.id_rol=r.id_rol ");
			strBuffquery.append("LEFT OUTER JOIN status s ON u.id_status=s.id_status ");
			strBuffquery.append("LEFT OUTER JOIN usuario sup ON sup.id_usuario=u.id_supervisor ");
			if (oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				oParametros.add(idUsuario);
				strBuffquery.append("LEFT OUTER JOIN usuario_admin a ON u.id_usuario=a.id_usuario AND a.id_admin=? ");
			    } else
			    {
				oParametros.add(oUsuarioTO.getIdUsuario());
				oParametros.add(oUsuarioTO.getIdUsuario());

				log.debug("oUsuarioTO.getIdUsuario():" + oUsuarioTO.getIdUsuario());
				strBuffquery.append("INNER JOIN usuario_admin a ON (u.id_usuario=a.id_usuario AND a.id_admin=? OR u.id_supervisor=a.id_usuario AND a.id_admin=?) ");
			    }

			if (usuarioFiltro != null)
			    {
				if (usuarioFiltro.getDominio() != null && !usuarioFiltro.getDominio().trim().equals(""))
				    {
					strBuffquery.append(!isWhere ? "WHERE" : "AND").append(" u.dominio='").append(usuarioFiltro.getDominio()).append("' ");
					isWhere = true;
				    }
				if (usuarioFiltro.getUsuario() != null && !usuarioFiltro.getUsuario().trim().equals(""))
				    {
					strBuffquery.append(!isWhere ? "WHERE" : "AND").append(" u.usuario like '%").append(usuarioFiltro.getUsuario()).append("%' ");
					isWhere = true;
				    }
				if (usuarioFiltro.getIdRol() != null && !usuarioFiltro.getIdRol().trim().equals(""))
				    {
					strBuffquery.append(!isWhere ? "WHERE" : "AND").append(" u.id_rol = ").append(usuarioFiltro.getIdRol()).append(" ");
					isWhere = true;
				    }
			    }

			switch (orden) {
			case 1:
			    strBuffquery.append("ORDER BY UPPER(u.apellido) ");
			    break;
			case 2:
			    strBuffquery.append("ORDER BY UPPER(u.nombre) ");
			    break;
			case 3:
			    strBuffquery.append("ORDER BY UPPER(r.desc_rol),u.tipo ");
			    break;
			case 4:
			    strBuffquery.append("ORDER BY UPPER(s.desc_status) ");
			    break;
			case 5:
			    strBuffquery.append("ORDER BY UPPER(u.usuario) ");
			    break;
			case 6:
			    strBuffquery.append("ORDER BY UPPER(sup.usuario),UPPER(u.usuario) ");
			    break;
			case 7:
			    strBuffquery.append("ORDER BY u.dominio,UPPER(u.usuario) ");
			    break;
			default:
			    strBuffquery.append("ORDER BY UPPER(u.apellido) ");
			}

			if (log.isDebugEnabled())
			    {
				log.debug(strBuffquery.toString());
			    }
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				Constants.ULTIMA_JUGADA = usuarioUltimaJugada();
			    }

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaPaginaUsuarioDAO");
			e.printStackTrace();
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaJugadorDAO(UsuarioIF oUsuarioTO, int orden) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try
		    {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.*,r.*, s.*, sup.usuario AS supervisor,  ");
			strBuffquery.append("(select format(sum(if(operacion='I',1,-1)*x.monto),2) from cuenta_jugador x where x.id_jugador=u.id_usuario ) As saldo ");
			strBuffquery.append("FROM usuario u, rol r, status s, usuario sup ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append(",usuario_admin a ");
			    }
			strBuffquery.append("WHERE u.id_rol=r.id_rol AND u.id_status=s.id_status AND u.id_supervisor=sup.id_usuario ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append("AND a.id_admin=? ");
				strBuffquery.append("AND (u.id_usuario=a.id_usuario OR u.id_supervisor=a.id_usuario) ");
			    }

			oParametros.add(Constants.ROL_JUGADOR);
			strBuffquery.append("AND u.id_rol=? ");
			switch (orden) {
			case 1:
			    strBuffquery.append("ORDER BY UPPER(u.apellido) ");
			    break;
			case 2:
			    strBuffquery.append("ORDER BY UPPER(u.nombre) ");
			    break;
			case 3:
			    strBuffquery.append("ORDER BY saldo ");
			    break;
			case 4:
			    strBuffquery.append("ORDER BY UPPER(s.desc_status) ");
			    break;
			case 5:
			    strBuffquery.append("ORDER BY UPPER(u.usuario) ");
			    break;
			case 6:
			    strBuffquery.append("ORDER BY UPPER(sup.usuario),UPPER(u.usuario) ");
			    break;
			default:
			    strBuffquery.append("ORDER BY UPPER(u.apellido) ");
			}

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaUsuarioDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaJugadorAdmDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaJugadorAdmDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try
		    {
			oParametros.add(Constants.ROL_JUGADOR_DE_TAQUILLA);
			oParametros.add(Constants.ROL_JUGADOR);
			oParametros.add(Constants.ROL_AUTO_JUGADOR);
			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.*,r.*, s.*  ");
			strBuffquery.append("FROM usuario u, rol r, status s ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append(", usuario_admin a ");
			    }
			strBuffquery.append("WHERE u.id_rol=r.id_rol AND u.id_status=s.id_status ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append("AND a.id_admin='").append(oUsuarioTO.getIdUsuario()).append("' ");
				strBuffquery.append("AND (u.id_usuario=a.id_usuario OR u.id_supervisor=a.id_usuario) ");
			    }
			strBuffquery.append("AND u.id_rol IN (?,?,?) ");
			strBuffquery.append("ORDER BY UPPER(u.usuario) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaJugadorAdmDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaJugadorAdmDAOReducida(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaJugadorAdmDAOReducida");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try
		    {
			oParametros.add(Constants.ROL_JUGADOR_DE_TAQUILLA);
			oParametros.add(Constants.ROL_JUGADOR);
			oParametros.add(Constants.ROL_AUTO_JUGADOR);
			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.id_usuario, u.usuario ");
			strBuffquery.append("FROM usuario u, rol r, status s ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append(", usuario_admin a ");
			    }
			strBuffquery.append("WHERE u.id_rol=r.id_rol AND u.id_status=s.id_status ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append("AND a.id_admin='").append(oUsuarioTO.getIdUsuario()).append("' ");
				strBuffquery.append("AND (u.id_usuario=a.id_usuario OR u.id_supervisor=a.id_usuario) ");
			    }
			strBuffquery.append("AND u.id_rol IN (?,?,?) ");
			strBuffquery.append("ORDER BY UPPER(u.usuario) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaJugadorAdmDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaAdministradorTaquillaAbrirJuegoUsuarioDAO() throws Exception
	    {
		log.info("Iniciando ejecucion de listaAdministradorTaquillaAbrirJuegoUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try
		    {
			oParametros.add(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT id_usuario  ");
			strBuffquery.append("FROM usuario ");
			strBuffquery.append("WHERE id_rol=? AND abrir_juego=1 ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			e.printStackTrace();
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaTaquillaDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaTaquillaDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try
		    {
			oParametros.add(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.*,r.*, s.*  ");
			strBuffquery.append("FROM usuario u, rol r, status s ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append(", usuario_admin a ");
			    }
			strBuffquery.append("WHERE u.id_rol=r.id_rol AND u.id_status=s.id_status ");
			if (!oUsuarioTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
			    {
				strBuffquery.append("AND a.id_admin='").append(oUsuarioTO.getIdUsuario()).append("' ");
				strBuffquery.append("AND (u.id_usuario=a.id_usuario OR u.id_supervisor=a.id_usuario) ");
			    }
			strBuffquery.append("AND u.id_rol = ? ");
			strBuffquery.append("ORDER BY UPPER(u.apellido) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaTaquillaDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaJugadorAdmTaqDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaJugadorAdmTaqDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		try
		    {
			oParametros.add(Constants.ROL_JUGADOR_DE_TAQUILLA);
			oParametros.add(oUsuarioTO.getIdUsuario());

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT u.*,r.*, s.*  ");
			strBuffquery.append("FROM usuario u, rol r, status s ");
			strBuffquery.append("WHERE u.id_rol=r.id_rol AND u.id_status=s.id_status ");
			strBuffquery.append("AND u.id_rol=? ");
			strBuffquery.append("AND u.id_supervisor=? ");
			strBuffquery.append("ORDER BY UPPER(u.apellido) ");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaJugadorAdmTaqDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public String saldoJugadorDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		return saldoJugadorDAO(oUsuarioTO, true);
	    }

	public String saldoJugadorDAO(UsuarioIF oUsuarioTO, boolean format) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.saldoJugadorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		String saldo = "0.00";
		try
		    {
			oParametros.add(oUsuarioTO.getIdUsuario());

			oCachedRowSet = new CachedRowSet();
			if (format)
			    {
				strBuffquery.append("SELECT format(sum(if(operacion='I',1,-1)*x.monto),2) AS saldo ");
			    } else
			    {
				strBuffquery.append("SELECT sum(if(operacion='I',1,-1)*x.monto) AS saldo ");
			    }
			strBuffquery.append("FROM cuenta_jugador x ");
			strBuffquery.append("WHERE x.id_jugador=? ");
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			if (oCachedRowSet.next())
			    {
				saldo = Constants.isNull(oCachedRowSet.getString("saldo"), "0.00");
			    }

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.saldoJugadorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return saldo;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaUsuarioSupervisorAdminDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaUsuarioSupervisorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		oCachedRowSet = null;
		try
		    {
			oCachedRowSet = new CachedRowSet();

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_AUTO_JUGADOR))
			    {
				oParametros.add("3");
				oParametros.add("3");
				oParametros.add("3");

			    } else
			    {
				oParametros.add(oUsuarioTO.getIdRol());
				oParametros.add(oUsuarioTO.getIdRol());
				oParametros.add(oUsuarioTO.getIdRol());
			    }
			strBuffquery.append("SELECT a.*, b.desc_rol ");
			strBuffquery.append("FROM usuario a, rol b ");
			strBuffquery.append("WHERE a.id_rol=b.id_rol AND a.id_rol IN (1,2) ");
			// strBuffquery.append("AND a.id_rol = if(?=4,1,if(?=3,2,if(?=2,1,1))) ");
			// //supervisor para jugadores el admin
			strBuffquery.append("AND a.id_rol = if(?=4,2,if(?=3,2,if(?=2,1,1))) "); // supervisor
												// para
												// jugadores
												// admtaq
			if (oUsuarioTO.getIdRol().equals(Constants.ROL_ADMINISTRADOR))
			    {
				strBuffquery.append("AND a.id_usuario=").append(Constants.ID_USUARIO_ADMINISTRADOR).append(" ");
			    }
			strBuffquery.append("ORDER BY id_rol,UPPER(apellido) ");

			// System.err.println(strBuffquery);
			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaUsuarioSupervisorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaUsuarioSupervisorDAO(UsuarioIF oUsuarioTO, UsuarioIF oUsuarioActualTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaUsuarioSupervisorDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		oCachedRowSet = null;
		try
		    {
			oCachedRowSet = new CachedRowSet();

			if (oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR))
			    {
				oParametros.add(oUsuarioActualTO.getIdUsuario());
				strBuffquery.append("SELECT a.*, b.desc_rol ");
				strBuffquery.append("FROM usuario a, rol b ");
				strBuffquery.append("WHERE a.id_rol=b.id_rol ");
				strBuffquery.append("AND a.id_usuario=? ");
				// strBuffquery.append("UNION ");
			    } else
			    {
				//
				/*
				 * oParametros.add(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA); oParametros.add(oUsuarioActualTO.getIdUsuario());
				 * 
				 * oParametros.add(Constants.ROL_JUGADOR_DE_TAQUILLA); oParametros.add(oUsuarioActualTO.getIdUsuario()); oParametros.add(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA);
				 * 
				 * strBuffquery.append( "SELECT a.*, a.apellido As orden, b.desc_rol "); strBuffquery. append("FROM usuario a, rol b, usuario_admin c "); strBuffquery.append("WHERE a.id_rol=b.id_rol "); strBuffquery.append("AND a.id_usuario=c.id_usuario ");
				 * strBuffquery.append("AND a.id_rol=? "); strBuffquery.append("AND c.id_admin=? ");
				 * 
				 * strBuffquery.append("UNION ");
				 * 
				 * strBuffquery.append( "SELECT d.*, d.apellido As orden, b.desc_rol FROM usuario a, rol b, usuario_admin c, usuario d " ); strBuffquery.append("WHERE d.id_rol=b.id_rol "); strBuffquery.append("AND a.id_usuario=c.id_usuario "); strBuffquery.append("AND a.id_rol=? ");
				 * strBuffquery.append("AND c.id_admin=? "); strBuffquery.append("AND a.id_supervisor=d.id_usuario "); strBuffquery.append("AND d.id_rol=? "); strBuffquery.append("GROUP BY a.id_supervisor ");
				 * 
				 * strBuffquery.append("ORDER BY id_rol,UPPER(apellido) ");
				 */
				strBuffquery.setLength(0);
				strBuffquery.append("SELECT a.*, a.apellido As orden, b.desc_rol FROM usuario a, rol b WHERE a.id_rol=b.id_rol AND a.id_rol=2");
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), null);
			    }

			if (!strBuffquery.toString().trim().equals(""))
			    {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaUsuarioSupervisorDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public CachedRowSet listaUsuarioSupervisorDominioDAO(UsuarioIF oUsuarioTO, UsuarioIF oUsuarioActualTO, HttpServletRequest request) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaUsuarioSupervisorDominioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		oCachedRowSet = null;
		int indice = -1;
		try
		    {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT a.*, b.desc_rol ");
			strBuffquery.append("FROM usuario a, rol b ");
			strBuffquery.append("WHERE a.id_rol=b.id_rol ");
			strBuffquery.append("AND a.id_usuario IN (");
			strBuffquery.append(Constants.getDominio(request).getIdAdminTaquilla());
			strBuffquery.append(")");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaUsuarioSupervisorDominioDAO");
			e.printStackTrace();
			throw e;
		    }
		return oCachedRowSet;
	    }

	/**
	 * 
	 * Consulta un registro en la tabla usuario
	 * 
	 */
	public boolean cargarUsuarioDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.consultarUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try
		    {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT * ");
			strBuffquery.append("FROM usuario ");

			boolean procesar = false;
			if (!oUsuarioTO.getIdUsuario().equalsIgnoreCase(""))
			    {
				oParametros.add(oUsuarioTO.getIdUsuario());
				strBuffquery.append(" WHERE id_usuario = ? ");
				procesar = true;
			    } else if (!oUsuarioTO.getCedula().equalsIgnoreCase(""))
			    {
				strBuffquery.append(" WHERE UPPER(cedula) LIKE '%");
				strBuffquery.append(oUsuarioTO.getCedula().toUpperCase().trim());
				strBuffquery.append("%'");
				procesar = true;
			    }

			if (procesar)
			    {
				oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
				if (oCachedRowSet.next())
				    {
					load(oCachedRowSet, oUsuarioTO);
					retorno = true;
				    }
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.consultarUsuarioDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		    }

		return retorno;
	    }

	/**
	 * 
	 * Consulta un registro en la tabla usuario
	 * 
	 */
	public boolean cargarUsuarioPorUsuarioClaveDAO(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.cargarUsuarioPorUsuarioClaveDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		boolean retorno = false;
		try
		    {
			oCachedRowSet = new CachedRowSet();

			strBuffquery.append("SELECT * ");
			strBuffquery.append("FROM usuario ");
			strBuffquery.append("WHERE usuario = ? ");
			strBuffquery.append("AND clave = MD5(?) ");
			strBuffquery.append("AND id_status = ? ");

			oParametros.add(oUsuarioTO.getUsuario());
			oParametros.add(oUsuarioTO.getClave());
			oParametros.add(Constants.STATUS_USUARIO_ACTIVO);

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);
			if (oCachedRowSet.next())
			    {
				load(oCachedRowSet, oUsuarioTO);
				retorno = true;
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.cargarUsuarioPorUsuarioClaveDAO");
			log.error("Error:" + e.getMessage());
			log.error(e.getStackTrace());
			throw e;
		    }

		return retorno;
	    }

	/**
	 * 
	 * Consulta una lista de registros en la tabla usuario
	 * 
	 */
	public TreeMap listaUsuarioDAO() throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.listaUsuarioDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		TreeMap lista = new TreeMap();
		try
		    {

			oCachedRowSet = new CachedRowSet();
			strBuffquery.append("SELECT * FROM usuario");

			oCachedRowSet = oEjecutorSql.ejecutaQuery(strBuffquery.toString(), oParametros);

			UsuarioTO oUsuarioTO;
			while (oCachedRowSet.next())
			    {
				oUsuarioTO = new UsuarioTO();
				load(oCachedRowSet, oUsuarioTO);
				lista.put(oUsuarioTO.getIdUsuario(), oUsuarioTO);
			    }

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.listaUsuarioDAO");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return lista;
	    }

	public TreeMap usuarioUltimaJugada() throws EjecutorSqlManejaError, Exception
	    {
		TreeMap lista = new TreeMap();

		CachedRowSet crs = oEjecutorSql.ejecutaQuery("select id_usuario,max(fecha_sis) from jugada group by id_usuario", null);
		while (crs.next())
		    {
			lista.put(crs.getString(1), crs.getString(2));
		    }

		return lista;
	    }

	public int actualizarUsuarioAdminDAO(String idUsuario, String idAdmin) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioDAO.actualizarUsuarioAdminDAO");
		ArrayList oParametros = new ArrayList();
		strBuffquery.setLength(0);
		int numRegAct = 0;
		String sep = "";
		boolean procesar = false;

		try
		    {
			oParametros.add(idUsuario);
			oParametros.add(idAdmin);

			strBuffquery.append("INSERT INTO usuario_admin (id_usuario,id_admin) VALUES(?,?)");

			numRegAct = oEjecutorSql.ejecutaSqlRetornaNumRegAct(strBuffquery.toString(), oParametros);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioDAO.actualizarOpcionUsuarioDAO");
			e.printStackTrace();
			throw e;
		    }
		return numRegAct;
	    }

	private void load(CachedRowSet oCachedRowSet, UsuarioIF oUsuarioTO) throws SQLException
	    {
		oUsuarioTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
		oUsuarioTO.setCedula(oCachedRowSet.getString("cedula"));
		oUsuarioTO.setApellido(oCachedRowSet.getString("apellido"));
		oUsuarioTO.setNombre(oCachedRowSet.getString("nombre"));
		oUsuarioTO.setUsuario(oCachedRowSet.getString("usuario"));
		oUsuarioTO.setClave(oCachedRowSet.getString("clave"));
		oUsuarioTO.setCorreo(oCachedRowSet.getString("correo"));
		oUsuarioTO.setDiasVencTicket(oCachedRowSet.getString("dias_venc_ticket"));
		oUsuarioTO.setCentroHipico(oCachedRowSet.getString("centro_hipico"));
		oUsuarioTO.setRif(oCachedRowSet.getString("rif"));
		oUsuarioTO.setTelefono(oCachedRowSet.getString("telefono"));
		oUsuarioTO.setCelular(oCachedRowSet.getString("celular"));
		oUsuarioTO.setBanco(oCachedRowSet.getString("banco"));
		oUsuarioTO.setNumeroCuenta(oCachedRowSet.getString("numero_cuenta"));
		oUsuarioTO.setTitularCuenta(oCachedRowSet.getString("titular_cuenta"));
		oUsuarioTO.setLogrosAltaBaja(oCachedRowSet.getString("logros_alta_baja"));
		oUsuarioTO.setLogrosCalc(oCachedRowSet.getString("logros_calc"));
		oUsuarioTO.setMonto(oCachedRowSet.getString("monto"));
		oUsuarioTO.setIdRol(oCachedRowSet.getString("id_rol"));
		oUsuarioTO.setIdStatus(oCachedRowSet.getString("id_status"));
		oUsuarioTO.setIdTipoCuenta(oCachedRowSet.getString("id_tipo_cuenta"));
		oUsuarioTO.setIdSupervisor(oCachedRowSet.getString("id_supervisor"));
		oUsuarioTO.setTicketNota(oCachedRowSet.getString("ticket_nota"));
		oUsuarioTO.setComisionVenta(oCachedRowSet.getString("comision_venta"));
		oUsuarioTO.setOtrosGastos(oCachedRowSet.getString("otros_gastos"));
		oUsuarioTO.setComisionVentaParley(oCachedRowSet.getString("comision_venta_parley"));
		oUsuarioTO.setOtrosGastosParley(oCachedRowSet.getString("otros_gastos_parley"));
		oUsuarioTO.setTipo(oCachedRowSet.getString("tipo"));
		oUsuarioTO.setMacAddress(oCachedRowSet.getString("mac_address"));
		oUsuarioTO.setValidarMacAddress(oCachedRowSet.getString("validar_mac_address"));
		oUsuarioTO.setLogrosMin(oCachedRowSet.getString("logros_min"));
		oUsuarioTO.setActivarSuperRunline(oCachedRowSet.getString("activar_super_runline"));
		oUsuarioTO.setTopePorDerecho(oCachedRowSet.getString("tope_por_derecho"));
		oUsuarioTO.setActivarBono(oCachedRowSet.getString("activar_bono"));
		oUsuarioTO.setApuestaEquipo(oCachedRowSet.getString("apuesta_equipo"));
		oUsuarioTO.setTopePorCombinacion(oCachedRowSet.getString("tope_por_combinacion"));
		oUsuarioTO.setEliminarJugada(oCachedRowSet.getString("eliminar_jugada"));
		oUsuarioTO.setPagoVeces(oCachedRowSet.getString("pago_veces"));
		oUsuarioTO.setDominio(oCachedRowSet.getString("dominio"));
		oUsuarioTO.setPagoClave(oCachedRowSet.getString("pago_clave"));
		oUsuarioTO.setAbrirJuego(oCachedRowSet.getString("abrir_juego"));
		oUsuarioTO.setVencidoReporte(oCachedRowSet.getString("vencido_reporte"));
		oUsuarioTO.setJugadaMinima(oCachedRowSet.getInt("jugada_minima"));
	    }
    }
