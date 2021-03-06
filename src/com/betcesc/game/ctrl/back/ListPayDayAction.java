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

public class ListPayDayAction extends Action {

	private static final Log log = LogFactory.getLog(ListPayDayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet lista;
		CachedRowSet listaJugador;
		JugadaIF oJugadaTO = new JugadaTO();
		JugadaIF oJugada2TO = new JugadaTO();
		String fecha = "";
		String jugador = "";
		String fecha2 = "";
		String jugador2 = "";
		
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);
			
			fecha = request.getParameter("fecha");
			jugador = request.getParameter("jugador");

			fecha2 = request.getParameter("fecha2");
			jugador2 = request.getParameter("jugador2");
			
			if(Constants.isNull(fecha)) {
				fecha = Constants.getFechaCorta();
			}
			
			oJugadaTO.setFechaSis(fecha);
			oJugadaTO.setIdUsuario(jugador);
			
			oJugada2TO.setFechaSis(fecha2);
			oJugada2TO.setIdUsuario(jugador2);
			
			int orden = 0;
			try {
				orden = Integer.parseInt(request.getParameter("orden"));
			} catch(Exception e) {
				
			}
			
			lista = juegoFacade.listaJugadaPagadaFacade(usuario,oJugadaTO,oJugada2TO,orden);
			
			if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
			} else {
				listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
			}

			request.setAttribute("fecha",fecha);
			request.setAttribute("fecha2",fecha);
			request.setAttribute("jugador",jugador);
			request.setAttribute("lista",lista);
			request.setAttribute("listaJugador",listaJugador);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
