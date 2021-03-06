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
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JugadaTO;

public class ListReportTicketAction extends Action {

	private static final Log log = LogFactory.getLog(ListReportTicketAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet listaJugador;
		CachedRowSet listaTaquilla;
		CachedRowSet listaStatusJugada;
		JugadaIF oJugadaTO = new JugadaTO();
		String fecha = "";
		String jugador = "";

		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			HttpSession session = request.getSession();
			usuarioFacade = new UsuarioFacade(request);
			juegoFacade = new JuegoFacade(request);

			oJugadaTO.setFechaSis(fecha);
			oJugadaTO.setFechaExp(fecha);
			oJugadaTO.setIdUsuario(jugador);

			listaTaquilla = new CachedRowSet();			
			if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
				listaTaquilla = usuarioFacade.listaTaquillaFacade(usuario);
			} else {
				listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
			}
			
			listaStatusJugada = juegoFacade.listaStatusSoloJugadaFacade();
			

			request.setAttribute("fecha", Constants.getFechaCorta());
			request.setAttribute("fecha2", Constants.getFechaCorta());
			request.setAttribute("listaJugador", listaJugador);
			request.setAttribute("listaTaquilla", listaTaquilla);
			request.setAttribute("listaStatusJugada", listaStatusJugada);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}

}
