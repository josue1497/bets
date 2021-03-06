package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.jdbc.rowset.CachedRowSet;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.JuegoIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JuegoTO;
import com.betcesc.game.to.UsuarioTO;

public class ListLogroAction extends Action {

	private static final Log log = LogFactory.getLog(ListLogroAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		CachedRowSet lista;
		CachedRowSet listaStatus;
		CachedRowSet listaDeporte;
		JuegoIF oJuegoTO = new JuegoTO();
		String fecha = "";
		String status = "";

		try {
			UsuarioIF usuario = new UsuarioTO();
			usuario.setIdUsuario("1");
			usuario.setIdSupervisor("1");
			usuario.setIdRol(Constants.ROL_ADMINISTRADOR);
			
			if(true) { // inabilitamos esta funcion
				throw new SessionInvalidException("Usuario no autorizado");
			}

			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);

			fecha = "";
			status = Constants.STATUS_JUEGO_ABIERTO;

			oJuegoTO.setFechaIni(fecha);
			oJuegoTO.setIdStatusJuego(status);

			lista = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

			if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				listaStatus = juegoFacade.listaStatusJuegoFacade();
			} else {
				listaStatus = juegoFacade.listaStatusTaquillaJuegoFacade();
			}

			listaDeporte = juegoFacade.listaDeporteActivoFacade(true);
			
			session.setAttribute("fecha", fecha);
			session.setAttribute("status", status);
			request.setAttribute("lista", lista);
			request.setAttribute("listaStatus", listaStatus);
			request.setAttribute("listaDeporte", listaDeporte);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}

}
