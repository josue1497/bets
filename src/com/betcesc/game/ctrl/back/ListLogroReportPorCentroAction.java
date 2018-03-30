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
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JuegoTO;
import com.betcesc.game.to.ParametrosTO;

public class ListLogroReportPorCentroAction extends Action {

	private static final Log log = LogFactory.getLog(ListLogroReportPorCentroAction.class);

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

		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			if (usuario.getIdRol().equals(Constants.ROL_AUTO_JUGADOR))
			{
				usuario.setIdRol(Constants.ROL_JUGADOR_DE_TAQUILLA);
			}
			
			// clave del cache supervisor + roldelUsuario
			String claveCached = (new StringBuffer(usuario.getIdSupervisor()).append("_").append(usuario.getIdRol())).toString();
			
			
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);

			String idDeporte = request.getParameter("idDeporte");
			
			boolean isDay = false;
			if(request.getParameter("isDay")!=null && request.getParameter("isDay").equals("true")) {
				isDay = true;
			}

			boolean isLiga = false;
			if(request.getParameter("isLiga")!=null && request.getParameter("isLiga").equals("true")) {
				isLiga = true;
			}
			
			if (idDeporte != null) {

				oJuegoTO.setIdDeporte(idDeporte);
				oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);

				if(isDay) {
					oJuegoTO.setFechaIni(Constants.getFechaCorta());
				}
				
				if(isLiga) {
					String[] idLigas = request.getParameterValues("idLiga");
					if(idLigas!=null) {
						StringBuffer cad = new StringBuffer();
						char sep = ' ';
						for(int i=0; i<idLigas.length; i++) {
							cad.append(sep).append(idLigas[i]);;
							sep = ',';
						}
						oJuegoTO.setIdLiga(cad.toString());
					}
				}

				lista = juegoFacade.listaLogroPorCentroFacade(usuario, oJuegoTO,isLiga);

				if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
					listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
				} else {
					listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
				}

				request.setAttribute("lista", lista);
				request.setAttribute("titulo", request.getParameter("titulo"));
				
				if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
					request.setAttribute("centro", usuario.getCentroHipico());
				} else if (usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
					request.setAttribute("centro", usuario.getCentroHipico());
					//request.setAttribute("centro", usuario.getSupervisor().getCentroHipico());
				}
				
				
				// esta incluido beisbol
				String[] ids = new String[1];
				if(idDeporte.indexOf("-")!=-1) {
					ids = idDeporte.split("-");
				} else {
					ids[0] = idDeporte;
				}
				for(int i=0; i<ids.length; i++) {
					if(ids[i].equals(Constants.ID_EQUIPO_BEISBOL)) {
						idDeporte = Constants.ID_EQUIPO_BEISBOL;
						break;
					}
				}
				
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
					
					// juego del anota primero
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
				
				for(int i=0; i<ids.length; i++) {
					if(ids[i].equals(Constants.ID_EQUIPO_BASKETBALL)) {
						idDeporte = Constants.ID_EQUIPO_BASKETBALL;
						break;
					}
				}
				
				if(idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL)) {
					oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_BASKETBALL_MITAD);
					oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);
					listaMitad	 = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

					if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
						listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
					} else {
						listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
					}
					request.setAttribute("listaMitad", listaMitad);
				}

				/*
				for(int i=0; i<ids.length; i++) {
					if(ids[i].equals(Constants.ID_EQUIPO_SOCCER)) {
						idDeporte = Constants.ID_EQUIPO_SOCCER;
						break;
					}
				}
				
				if(idDeporte.equals(Constants.ID_EQUIPO_SOCCER)) {
					oJuegoTO.setIdDeporte(Constants.ID_EQUIPO_SOCCER_MITAD);
					oJuegoTO.setIdStatusJuego(Constants.STATUS_JUEGO_ABIERTO);
					listaMitad	 = juegoFacade.listaLogroFacade(usuario, oJuegoTO);

					if (usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
						listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
					} else {
						listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
					}
					request.setAttribute("listaMitad", listaMitad);
				}
				*/

			}

			ParametrosIF oParametros = new ParametrosTO();
			String pieDePagina = null;
			
			ParametrosFacade parametrosFacade;
			parametrosFacade = new ParametrosFacade(request);

			oParametros.setNombre("PIE_PAGINA_LOGRO");
			pieDePagina = parametrosFacade.consultarParametrosFacade(oParametros).getValor();
			request.setAttribute("PIE_PAGINA_LOGRO", pieDePagina);

			request.setAttribute("idDeporte", idDeporte);
			
			if(idDeporte.equals(Constants.ID_EQUIPO_BEISBOL) || idDeporte.equals(Constants.ID_EQUIPO_BASKETBALL) ) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("successOld");
			}

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}

}
