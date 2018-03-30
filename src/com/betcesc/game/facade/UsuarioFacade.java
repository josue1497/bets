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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.dao.CuentaJugadorDAO;
import com.betcesc.game.dao.JugadaDAO;
import com.betcesc.game.dao.MensajeUserDAO;
import com.betcesc.game.dao.MenuDAO;
import com.betcesc.game.dao.RolDAO;
import com.betcesc.game.dao.StatusDAO;
import com.betcesc.game.dao.TipoCuentaDAO;
import com.betcesc.game.dao.UsuarioDAO;
import com.betcesc.game.exceptions.DomainException;
import com.betcesc.game.exceptions.InformationMachineNotFoundException;
import com.betcesc.game.exceptions.SessionDuplicateException;
import com.betcesc.game.exceptions.UserNotFoundException;
import com.betcesc.game.exceptions.UserNotValidMachineException;
import com.betcesc.game.interfaces.CuentaJugadorIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.MensajeUserTO;
import com.betcesc.game.to.RolTO;
import com.betcesc.game.to.StatusTO;
import com.betcesc.game.to.TipoCuentaTO;
import com.betcesc.game.to.UsuarioTO;
import com.jade.util.Encriptor;
import com.jade.util.lbda.EjecutorSql;

/**
 *
 * @author jrivero
 *
 */

public class UsuarioFacade
    {
	private static final Log log = LogFactory.getLog(UsuarioFacade.class);

	HttpServletRequest request = null;

	/**
	 * Constructor.
	 */
	public UsuarioFacade(HttpServletRequest request)
	    {
		super();
		this.request = request;
	    }

	/*
	 * ...........................USUARIO...........................
	 */

	/**
	 * Insertar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarUsuarioFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.insertarUsuarioFacade");
		int act = 0;

		UsuarioDAO oUsuarioDAO = new UsuarioDAO();

		try
		    {
			act = oUsuarioDAO.insertarUsuarioDAO(oUsuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.insertarUsuarioFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return (act);
	    }

	/**
	 * Actualizar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarUsuarioFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.actualizarUsuarioFacade");
		int act = 0;
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			act = oUsuarioDAO.actualizarUsuarioDAO(oUsuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.actualizarUsuarioFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return (act);
	    }

	/**
	 * Actualizar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarMensajeUsuarioFacade(String mensaje, String[] usuarios, String idUsuario) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.actualizarMensajeUsuarioFacade");
		int act = 0;
		EjecutorSql oEjecutorSql = new EjecutorSql();
		MensajeUserDAO oMensajeUserDAO = new MensajeUserDAO(oEjecutorSql);
		MensajeUserTO oMensajeUserTO = new MensajeUserTO();
		try
		    {
			oMensajeUserTO.setIdMensaje("0");
			oMensajeUserTO.setIdUsuario(idUsuario);
			oMensajeUserTO.setFechaView(null);
			oMensajeUserTO.setMensaje(mensaje);

			for (int i = 0; i < usuarios.length; i++)
			    {
				oMensajeUserTO.setIdUserTo(usuarios[i]);
				oMensajeUserDAO.insertarMensajeUserDAO(oMensajeUserTO);
			    }

			// le enviamos una copia del mensaje al admin principal
			// oMensajeUserTO.setIdUserTo(Constants.ID_USUARIO_ADMINISTRADOR);
			// oMensajeUserDAO.insertarMensajeUserDAO(oMensajeUserTO);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.actualizarMensajeUsuarioFacade");
			e.printStackTrace();
			throw e;
		    } finally
		    {
			oEjecutorSql.close();
		    }
		return (act);
	    }

	/**
	 * Eliminar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int eliminarUsuarioFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.eliminarUsuarioFacade");
		int act = 0;
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			act = oUsuarioDAO.eliminarUsuarioDAO(oUsuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.eliminarUsuarioFacade");
			log.error("Error:" + e.getMessage());
		    }
		return (act);
	    }

	/**
	 * Cargar registros un registro de la tabla Usuario.
	 *
	 * @return UsuarioTO.
	 */
	public UsuarioTO consultarUsuarioFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.cargarUsuarioFacade");
		int act = 0;

		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();
		usuarioTO.setIdUsuario(oUsuarioTO.getIdUsuario());

		try
		    {
			oUsuarioDAO.cargarUsuarioDAO(usuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de cargarUsuarioFacade.consultarUsuarioFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		    }
		return usuarioTO;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaPaginaUsuarioFacade(UsuarioIF oUsuarioTO, int orden, String idUsuario, UsuarioTO usuarioFiltro) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaPaginaUsuarioDAO(oUsuarioTO, orden, idUsuario, usuarioFiltro);

		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaUsuarioFacade(UsuarioIF oUsuarioTO, int orden, String idUsuario) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaUsuarioDAO(oUsuarioTO, orden, idUsuario, null);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	public CachedRowSet listaAdministradorTaquillaAbrirJuegoUsuarioFacade() throws Exception
	    {
		log.info("Iniciando ejecucion de listaAdministradorTaquillaAbrirJuegoUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaAdministradorTaquillaAbrirJuegoUsuarioDAO();
		    } catch (Exception e)
		    {
			e.printStackTrace();
		    }
		return oCachedRowSet;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJugadorFacade(UsuarioIF oUsuarioTO, int orden) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJugadorFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaJugadorDAO(oUsuarioTO, orden);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaJugadorFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJugadorAdmFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJugadorAdmTaqFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaJugadorAdmDAOReducida(oUsuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaJugadorAdmTaqFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaTaquillaFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaTaquillaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaTaquillaDAO(oUsuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaTaquillaFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaJugadorAdmTaqFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaJugadorAdmTaqFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaJugadorAdmTaqDAO(oUsuarioTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaJugadorAdmTaqFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Construye la consulta de lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet consultarUsuarioListaFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.consultarUsuarioListaFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaUsuarioDAO(oUsuarioTO, 0, oUsuarioTO.getIdUsuario());
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.consultarUsuarioListaFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Inicia al usuario en el sistema
	 *
	 * @throws Exception
	 *
	 */
	public void registrarUsuarioEnSession(UsuarioIF oUsuarioTO) throws UserNotFoundException, SessionDuplicateException, DomainException, Exception
	    {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();
		JugadaDAO oJugadaDAO = new JugadaDAO();
		String macAddress = null;

		/* buscamos el usuario en base de datos */
		usuarioTO.setUsuario(oUsuarioTO.getUsuario());
		usuarioTO.setClave(oUsuarioTO.getClave());
		macAddress = oUsuarioTO.getMacAddress();

		usuarioDAO.cargarUsuarioPorUsuarioClaveDAO(usuarioTO);
		if (usuarioTO.getIdUsuario() == null || usuarioTO.getIdUsuario().equals(""))
		    {
			throw new UserNotFoundException();
		    }

		// validamos si el usuario pertenece al dominio
		if (usuarioTO.getDominio() == null || !usuarioTO.getDominio().equals(Constants.getDominio(request).getIdDominio()))
		    {
			throw new DomainException();
		    }

		// verificamos si hay que validar el usuario con la maquina
		if (usuarioTO.getValidarMacAddress().equals("1"))
		    {
			if (macAddress == null || macAddress.trim().length() == 0 || macAddress.equals("undefined"))
			    {
				throw new InformationMachineNotFoundException();
			    }

			if (usuarioTO.getMacAddress() == null || usuarioTO.getMacAddress().trim().equals(""))
			    {
				// primera vez guardamos la mac address
				usuarioTO.setMacAddress(macAddress);
				usuarioDAO.actualizarUsuarioMacAddressDAO(usuarioTO);
			    } else
			    {
				if (!usuarioTO.getMacAddress().equals(macAddress))
				    {
					throw new UserNotValidMachineException();
				    }
			    }
		    }

		usuarioTO.setSession(request.getSession());

		// sacamos al usuario de cualquier session anterior
		Constants.deleteUserInSession(usuarioTO);

		Constants.RegisterUserInSession(usuarioTO);

		/* buscamos el menu segun el rol */
		MenuDAO menuDAO = new MenuDAO();
		ArrayList menu = menuDAO.consultarMenuDAO(usuarioTO);

		request.getSession().setAttribute("usuario", usuarioTO);
		request.getSession().setAttribute("menu", menu);

		// actualizamos el status de las jugadas vencidas
		oJugadaDAO.actualizarStatuJugadaVencidaJugadaDAO();

	    }

	/**
	 * Inicia al usuario en el sistema
	 *
	 * @throws Exception
	 *
	 */
	public void eliminarUsuarioEnSession(UsuarioIF oUsuarioTO, boolean isActual) throws UserNotFoundException, SessionDuplicateException, Exception
	    {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		/* sacamos el usuario de la session */
		Constants.deleteUserInSession(oUsuarioTO);

		if (isActual)
		    {
			request.getSession().removeAttribute("usuario");
			request.getSession().removeAttribute("menu");
		    }
	    }

	/**
	 * Devuelve una lista de objetos de la tabla rol
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaRolFacade(UsuarioIF oUsuarioTO, UsuarioIF oUsuarioActualTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaRolFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		RolDAO oRolDAO = new RolDAO();
		RolTO oRolTO = null;
		try
		    {
			oCachedRowSet = oRolDAO.listaRolDAO(oUsuarioTO);
			while (oCachedRowSet.next())
			    {
				if (oUsuarioTO.getIdUsuario() == null || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR))
				    {
					if (!oCachedRowSet.getString("id_rol").equals(Constants.ROL_JUGADOR))
					    {
						continue;
					    }
				    }
				// else if(oUsuarioActualTO!=null && !oUsuarioActualTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR)) {
				// if(!oCachedRowSet.getString("id_rol").equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
				// continue;
				// }
				// }
				oRolTO = new RolTO();
				oRolTO.setIdRol(oCachedRowSet.getString("id_rol"));
				oRolTO.setDescRol(oCachedRowSet.getString("desc_rol"));
				lista.add(oRolTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaRolFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return lista;
	    }

	/**
	 * Devuelve una lista de objetos de la tabla rol
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaRolModificarFacade(UsuarioIF oUsuarioTO, UsuarioIF oUsuarioActualTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaRolFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		RolDAO oRolDAO = new RolDAO();
		RolTO oRolTO = null;
		try
		    {
			oCachedRowSet = oRolDAO.listaRolDAO(oUsuarioTO);
			while (oCachedRowSet.next())
			    {
				if (oUsuarioTO.getIdUsuario() == null || oUsuarioTO.getIdRol().equals(Constants.ROL_JUGADOR))
				    {
					if (!oCachedRowSet.getString("id_rol").equals(Constants.ROL_JUGADOR))
					    {
						continue;
					    }
				    } else if (oUsuarioActualTO != null && !oUsuarioActualTO.getIdUsuario().equals(Constants.ID_USUARIO_ADMINISTRADOR))
				    {
					if (!oCachedRowSet.getString("id_rol").equals(Constants.ROL_JUGADOR_DE_TAQUILLA))
					    {
						continue;
					    }
				    }
				oRolTO = new RolTO();
				oRolTO.setIdRol(oCachedRowSet.getString("id_rol"));
				oRolTO.setDescRol(oCachedRowSet.getString("desc_rol"));
				lista.add(oRolTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaRolFacade");
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
	public ArrayList listaStatusFacade() throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaStatusFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		StatusDAO oStatusDAO = new StatusDAO();
		StatusTO oStatusTO = null;
		try
		    {
			oCachedRowSet = oStatusDAO.listaStatusDAO();
			while (oCachedRowSet.next())
			    {
				oStatusTO = new StatusTO();
				oStatusTO.setIdStatus(oCachedRowSet.getString("id_status"));
				oStatusTO.setDescStatus(oCachedRowSet.getString("desc_status"));
				lista.add(oStatusTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaStatusFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return lista;
	    }

	/**
	 * Devuelve una lista de objetos de la tabla tipo_cuenta
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaTipoCuentaFacade() throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaTipoCuentaFacade");
		ArrayList lista = new ArrayList();
		CachedRowSet oCachedRowSet = new CachedRowSet();
		TipoCuentaDAO oTipoCuentaDAO = new TipoCuentaDAO();
		TipoCuentaTO oTipoCuentaTO = null;
		try
		    {
			oCachedRowSet = oTipoCuentaDAO.listaTipoCuentaDAO();
			while (oCachedRowSet.next())
			    {
				oTipoCuentaTO = new TipoCuentaTO();
				oTipoCuentaTO.setIdTipoCuenta(oCachedRowSet.getString("id_tipo_cuenta"));
				oTipoCuentaTO.setDescTipoCuenta(oCachedRowSet.getString("desc_tipo_cuenta"));
				lista.add(oTipoCuentaTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaTipoCuentaFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return lista;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaUsuarioSupervisorAdminFacade(UsuarioIF oUsuarioTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioSupervisorAdminFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();
		ArrayList lista = new ArrayList();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaUsuarioSupervisorAdminDAO(oUsuarioTO);
			while (oCachedRowSet.next())
			    {
				usuarioTO = new UsuarioTO();
				usuarioTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
				usuarioTO.setUsuario(oCachedRowSet.getString("usuario").toUpperCase().concat(" (").concat(oCachedRowSet.getString("desc_rol").toLowerCase()).concat(")"));
				lista.add(usuarioTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioSupervisorAdminFacade");
			log.error("Error:" + e.getMessage());
		    }
		return lista;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaUsuarioSupervisorFacade(UsuarioIF oUsuarioTO, UsuarioIF oUsuarioActualTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioSupervisorFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();
		ArrayList lista = new ArrayList();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaUsuarioSupervisorDAO(oUsuarioTO, oUsuarioActualTO);
			while (oCachedRowSet.next())
			    {
				usuarioTO = new UsuarioTO();
				usuarioTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
				usuarioTO.setUsuario(oCachedRowSet.getString("usuario").toUpperCase().concat(" (").concat(oCachedRowSet.getString("desc_rol").toLowerCase()).concat(")"));
				lista.add(usuarioTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioSupervisorFacade");
			log.error("Error:" + e.getMessage());
		    }
		return lista;
	    }

	/**
	 * Construye la lista de los registros de la tabla usuario
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public ArrayList listaUsuarioSupervisorDominioFacade(UsuarioIF oUsuarioTO, UsuarioIF oUsuarioActualTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaUsuarioSupervisorFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = new UsuarioTO();
		ArrayList lista = new ArrayList();
		try
		    {
			oCachedRowSet = oUsuarioDAO.listaUsuarioSupervisorDominioDAO(oUsuarioTO, oUsuarioActualTO, request);
			while (oCachedRowSet.next())
			    {
				usuarioTO = new UsuarioTO();
				usuarioTO.setIdUsuario(oCachedRowSet.getString("id_usuario"));
				usuarioTO.setUsuario(oCachedRowSet.getString("usuario").toUpperCase().concat(" (").concat(oCachedRowSet.getString("desc_rol").toLowerCase()).concat(")"));
				lista.add(usuarioTO);
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaUsuarioSupervisorFacade");
			e.printStackTrace();
		    }
		return lista;
	    }

	/**
	 * Inserta registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int insertarSaldoFacade(CuentaJugadorIF oCuentaJugadorTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.insertarSaldoFacade");
		int act = 0;
		CuentaJugadorDAO oCuentaJugadorDAO = new CuentaJugadorDAO();
		try
		    {
			act = oCuentaJugadorDAO.insertarCuentaJugadorDAO(oCuentaJugadorTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.insertarSaldoFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return (act);
	    }

	/**
	 * Construye la lista de los registros de la tabla cuenta_jugador
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaCuentaJugadorFacade(CuentaJugadorIF oCuentaJugadorTO) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaCuentaJugadorFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		EjecutorSql oEjecutorSql = new EjecutorSql();
		CuentaJugadorDAO oCuentaJugadorDAO = new CuentaJugadorDAO(oEjecutorSql);
		try
		    {
			oEjecutorSql.getConnection().setAutoCommit(false);
			oCachedRowSet = oCuentaJugadorDAO.listaCuentaJugadorDAO(oCuentaJugadorTO);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaCuentaJugadorFacade");
			log.error("Error:" + e.getMessage());
		    } finally
		    {
			oEjecutorSql.getConnection().commit();
			oEjecutorSql.close();
		    }
		return oCachedRowSet;
	    }

	/**
	 * Cargar registros un registro de la tabla Usuario.
	 *
	 * @return UsuarioTO.
	 */
	public String consultarSaldoUsuarioFacade(UsuarioIF oUsuarioTO, boolean format) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.consultarSaldoUsuarioFacade");
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		String saldo = "0.00";
		try
		    {
			saldo = oUsuarioDAO.saldoJugadorDAO(oUsuarioTO, format);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de cargarUsuarioFacade.consultarSaldoUsuarioFacade");
			log.error("Error:" + e.getMessage());
			log.error("Error:" + e.getStackTrace());
		    }
		return saldo;
	    }

	/**
	 * Actualizar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarUsuarioSoloClaveFacade(UsuarioIF oUsuarioTO, String claveOld, String claveNew) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.actualizarUsuarioSoloClaveFacade");
		int act = 0;
		UsuarioDAO oUsuarioDAO = new UsuarioDAO();
		try
		    {
			if (Encriptor.getMD5(claveOld).equals(oUsuarioTO.getClave()))
			    {
				oUsuarioTO.setClave(claveNew);
				act = oUsuarioDAO.actualizarUsuarioSoloClaveDAO(oUsuarioTO);
			    } else
			    {
				throw new Exception("La clave actual no coincide con la clave registrada.");
			    }
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.actualizarUsuarioSoloClaveFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    }
		return (act);
	    }

	/**
	 * Actualizar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarMenuUsuarioFacade(String idUsuario, String menues, String usuarios) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.actualizarMenuUsuarioFacade");
		int act = 0;
		EjecutorSql oEjecutorSql = new EjecutorSql();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO(oEjecutorSql);
		try
		    {
			oEjecutorSql.getConnection().setAutoCommit(false);
			act = oUsuarioDAO.actualizarMenuUsuarioDAO(idUsuario, menues, usuarios);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.actualizarMenuUsuarioFacade");
			log.error("Error:" + e.getMessage());
			throw e;
		    } finally
		    {
			oEjecutorSql.getConnection().commit();
			oEjecutorSql.close();
		    }
		return (act);
	    }

	/**
	 * Retorna los permisos asignados a un usuario administrador
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaMenuUsuarioFacade(String idUsuario) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaMenuUsuarioFacade");
		CachedRowSet oCachedRowSet = new CachedRowSet();
		MenuDAO oMenuDAO = new MenuDAO();
		try
		    {
			oCachedRowSet = oMenuDAO.listaMenuUsuarioDAO(idUsuario);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaMenuUsuarioFacade");
			log.error("Error:" + e.getMessage());
		    }
		return oCachedRowSet;
	    }

	/**
	 * Retorna los permisos asignados a un usuario administrador
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public CachedRowSet listaMensajeUserFacade()
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.listaMensajeUserFacade");
		MensajeUserDAO oMensajeUserDAO = new MensajeUserDAO();
		CachedRowSet oCachedRowSet = null;
		try
		    {
			oCachedRowSet = oMensajeUserDAO.listaMensajeUserDAO();
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.listaMensajeUserFacade");
			e.printStackTrace();
		    }
		return oCachedRowSet;
	    }

	public static synchronized void obtenerMensajes(HttpServletRequest req, UsuarioIF usuario)
	    {

		try
		    {
			HttpSession session = req.getSession();
			ArrayList lista = new ArrayList();
			MensajeUserTO oMensajeUserTO;

			CachedRowSet crs = Constants.MENSAJES_POR_USUARIO;
			crs.beforeFirst();
			while (crs.next())
			    {
				if (crs.getString("id_user_to").equals(usuario.getIdUsuario()))
				    {
					oMensajeUserTO = new MensajeUserTO();
					oMensajeUserTO.setIdMensaje(crs.getString("id_mensaje"));
					oMensajeUserTO.setMensaje(crs.getString("mensaje"));
					oMensajeUserTO.setIdUsuario(crs.getString("id_usuario"));
					oMensajeUserTO.setUsuario(crs.getString("usuario"));
					oMensajeUserTO.setFechaSis(Constants.getFechaLargaWithHour(new com.jade.util.Date(crs.getDate("fecha_sis"))));
					// oMensajeUserTO.setFechaSis(crs.getString("fecha_sis"));
					lista.add(oMensajeUserTO);
				    }
			    }
			session.setAttribute("MENSAJES_POR_USUARIO", lista);
		    } catch (Exception e)
		    {
			e.printStackTrace();
		    }

	    }

	/**
	 * Actualizar registros en la tabla Usuario.
	 *
	 * @return ArrayList, array cuerpo del main.
	 */
	public int actualizarUsuarioAdminFacade(String idUsuario, String idAdmin) throws Exception
	    {
		log.info("Iniciando ejecucion de UsuarioFacade.actualizarUsuarioAdminFacade");
		int act = 0;
		EjecutorSql oEjecutorSql = new EjecutorSql();
		UsuarioDAO oUsuarioDAO = new UsuarioDAO(oEjecutorSql);
		try
		    {
			act = oUsuarioDAO.actualizarUsuarioAdminDAO(idUsuario, idAdmin);
		    } catch (Exception e)
		    {
			log.info("Error en la ejecucion de UsuarioFacade.actualizarUsuarioAdminFacade");
			e.printStackTrace();
			throw e;
		    } finally
		    {
			oEjecutorSql.close();
		    }
		return (act);
	    }

    }
