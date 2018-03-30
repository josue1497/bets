package com.betcesc.game.ctrl.back;

import java.util.ArrayList;

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

public class ListGameAction extends Action {

	private static final Log log = LogFactory.getLog(ListGameAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		ArrayList lista;
		CachedRowSet listaStatus;
		CachedRowSet listaDeportes;
		JuegoIF oJuegoTO = new JuegoTO();
		String fecha = "";
		String status = "";
		String idDeporteListGame = "";

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);

			fecha = (request.getParameter("fecha") != null ? request.getParameter("fecha") : (session.getAttribute("fecha") != null ? session.getAttribute("fecha").toString() : ""));
			status = (request.getParameter("status") != null ? request.getParameter("status") : (session.getAttribute("status") != null ? session.getAttribute("status").toString() : Constants.STATUS_JUEGO_ABIERTO));
			idDeporteListGame = (request.getParameter("idDeporteListGame") != null ? request.getParameter("idDeporteListGame") : (session.getAttribute("idDeporteListGame") != null ? session.getAttribute("idDeporteListGame").toString() : ""));

			oJuegoTO.setFechaIni(fecha);
			oJuegoTO.setIdStatusJuego(status);
			oJuegoTO.setIdDeporte(idDeporteListGame);
			
			lista = juegoFacade.listaJuegoFacade(usuario, oJuegoTO);

			if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				listaStatus = juegoFacade.listaStatusJuegoFacade();
			} else {
				listaStatus = juegoFacade.listaStatusTaquillaJuegoFacade();
			}

			listaDeportes = juegoFacade.listaDeporteActivoFacade(true);

			session.setAttribute("fecha", fecha);
			session.setAttribute("status", status);
			session.setAttribute("idDeporteListGame", idDeporteListGame);

			request.setAttribute("lista", lista);
			request.setAttribute("listaStatus", listaStatus);
			request.setAttribute("listaDeportes", listaDeportes);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}

}
