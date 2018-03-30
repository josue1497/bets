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
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JuegoTO;
import com.betcesc.game.to.ParametrosTO;
import com.betcesc.game.to.UsuarioTO;

public class ListLogroReportAction extends Action {

	private static final Log log = LogFactory.getLog(ListLogroReportAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet lista;
		CachedRowSet lista5to;
		CachedRowSet listaMitad;
		CachedRowSet lista1ro;
		CachedRowSet lista0ro;
		CachedRowSet listaJugador = null;
		JuegoTO oJuegoTO = new JuegoTO();
		ParametrosFacade parametrosFacade;
		ParametrosIF oParametros = new ParametrosTO();
		String pieDePagina = null;

		try {
			UsuarioIF usuario = new UsuarioTO();
			usuario.setIdUsuario("1");
			usuario.setIdSupervisor("1");
			usuario.setIdRol(Constants.ROL_ADMINISTRADOR);

			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);
			parametrosFacade = new ParametrosFacade(request);

			String idDeporte = request.getParameter("idDeporte");
			
			boolean isDay = false;
			if(request.getParameter("isDay")!=null && request.getParameter("isDay").equals("true")) {
				isDay = true;
			}

			oParametros.setNombre("PIE_PAGINA_LOGRO");
			pieDePagina = parametrosFacade.consultarParametrosFacade(oParametros).getValor();
			request.setAttribute("PIE_PAGINA_LOGRO", pieDePagina);
			

			if (idDeporte != null) {

				oJuegoTO.setIdDeporte(idDeporte);
				oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);
				
				if(isDay) {
					oJuegoTO.setFechaIni(Constants.getFechaCorta());
				}

				lista = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

				if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
					listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
				} else {
					listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
				}

				request.setAttribute("lista", lista);
				request.setAttribute("titulo", request.getParameter("titulo"));

				if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL)) {
					oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_BEISBOL_5);
					oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);

					lista5to = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

					if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
						listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
					} else {
						listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
					}

					request.setAttribute("lista5to", lista5to);

					
					// juego del 1er inning
					oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_BEISBOL_1);
					oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);

					lista1ro = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

					if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
						listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
					} else {
						listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
					}

					request.setAttribute("lista1ro", lista1ro);
					
					// juego del Anota primero
					oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_BEISBOL_0);
					oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);

					lista0ro = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

					if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
						listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
					} else {
						listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
					}

					request.setAttribute("lista0ro", lista0ro);
				}
			}

			request.setAttribute("idDeporte", idDeporte);
			
			if(idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL)) {
				oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_BASKETBALL_MITAD);
				oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);

				listaMitad	 = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

				request.setAttribute("listaMitad", listaMitad);
				
			/*} else if(idDeporte.equals(Constants.ID_EQUIPO_SOCCER)) {
				oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_SOCCER_MITAD);
				oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);

				listaMitad	 = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

				request.setAttribute("listaMitad", listaMitad);*/
			}

			//if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL) || idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL) || idDeporte.equals(Constants.ID_EQUIPO_SOCCER)) {
			if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL) || idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("successOld");
			}

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}

}
