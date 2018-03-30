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

public class ListReportApuestaEquipoAction extends Action {

	private static final Log log = LogFactory.getLog(ListReportApuestaEquipoAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet lista;
		CachedRowSet listaJugador;
		JugadaIF oJugadaTO = new JugadaTO();
		JugadaIF oApuesta2TO = new JugadaTO();
		
		String fecha = "";
		String jugador = "";
		String fecha2 = "";
		String jugador2 = "";
		String status = "";
		String descStatus = "";
		String resumido = "";
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);

			resumido = request.getParameter("resumido");
			
			fecha = request.getParameter("fecha");
			jugador = request.getParameter("jugador");
			status = request.getParameter("status");
			
			fecha2 = request.getParameter("fecha2");
			jugador2 = request.getParameter("jugador2");

			if(Constants.isNull(fecha)) {
				fecha = Constants.getFechaCorta();
			}
			if(Constants.isNull(fecha2)) {
				fecha2 = Constants.getFechaCorta();
			}
			
			oJugadaTO.setFechaSis(fecha);
			oJugadaTO.setIdUsuario(jugador);
			oJugadaTO.setIdStatusJugada(status);
			

			oApuesta2TO.setFechaSis(fecha2);
			oApuesta2TO.setIdUsuario(jugador2);
			oApuesta2TO.setIdStatusJugada(status);
			
			
			int orden = 0;
			try {
				orden = Integer.parseInt(request.getParameter("orden"));
			} catch(Exception e) {
				
			}
			descStatus = juegoFacade.cargarStatuJugadaFacade(oJugadaTO);			
			
			lista = juegoFacade.listaJugadaFacade(usuario,oJugadaTO,oApuesta2TO,orden,null);
			
			if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
			} else {
				listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
			}

			request.setAttribute("fecha",fecha);
			request.setAttribute("fecha2",fecha2);
			request.setAttribute("jugador",jugador);
			request.setAttribute("lista",lista);
			request.setAttribute("listaJugador",listaJugador);
			request.setAttribute("status",Constants.isNull(descStatus, "TODOS"));
			request.setAttribute("resumido",resumido);
			

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
