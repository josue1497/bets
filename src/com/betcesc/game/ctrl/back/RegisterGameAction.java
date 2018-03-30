package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.dao.DeporteDAO;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.facade.LanzadorFacade;
import com.betcesc.game.facade.LigaFacade;
import com.betcesc.game.facade.LogrosFacade;
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.interfaces.JuegoEquipoIF;
import com.betcesc.game.interfaces.LanzadorIF;
import com.betcesc.game.interfaces.LigaIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.DeporteTO;
import com.betcesc.game.to.JuegoEquipoTO;
import com.betcesc.game.to.LanzadorTO;
import com.betcesc.game.to.LigaTO;
import com.betcesc.game.to.ParametrosTO;

public class RegisterGameAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterGameAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			UsuarioIF oUsuarioIF = Constants.getUserSession(request);
			
			if(!oUsuarioIF.getIdRol().equals(Constants.ROL_ADMINISTRADOR) && !oUsuarioIF.getIdRol().equals(Constants.ROL_ADMINISTRADOR_DE_TAQUILLA)) {
				return mapping.findForward("end");
			}

			RegisterGameActionForm juego = new RegisterGameActionForm();
			
			RegisterGameActionForm forma = new RegisterGameActionForm();
			
			boolean modificar = false;

			request.setAttribute("modificar", new Boolean(false));
			JuegoFacade oJuegoFacade = new JuegoFacade(request);
			
			DeporteDAO oDeporteDAO = new DeporteDAO();
			DeporteTO oDeporteTO = new DeporteTO();
			boolean isMultiEquipo = false;
			
			// buscamos el parametro de la diferencia de hora
			ParametrosFacade oParametrosFacade = new ParametrosFacade(request);
			ParametrosTO oParametrosTO = new ParametrosTO();

			oParametrosTO.setNombre("DIFERENCIA_HORARIO");
			oParametrosTO = oParametrosFacade.consultarParametrosFacade(oParametrosTO);
			
			request.setAttribute("DIFERENCIA_HORARIO", oParametrosTO.getValor());
			//
			

			if(request.getParameter("idJuego")!=null && !request.getParameter("idJuego").trim().equals("") && !request.getParameter("idJuego").trim().equals("0") ) {
				
				juego.setIdJuego(request.getParameter("idJuego"));
				juego.setIdUsuario(oUsuarioIF.getIdUsuario());
				juego = oJuegoFacade.consultarJuegoCompletoFacade(juego, oUsuarioIF);
				
				oDeporteTO.setIdDeporte(juego.getIdDeporte());
				if(oDeporteDAO.cargarDeporteDAO(oDeporteTO)) {
					if(oDeporteTO.getEmpate().equals(Constants.JUEGO_MULTIPLE_EQUIPO)) {
						isMultiEquipo = true;
					}
				}

				LigaFacade oLigaFacade = new LigaFacade(request);
				LigaIF oLigaIF = new LigaTO();
				oLigaIF.setIdDeporte(juego.getIdDeporte());
				request.setAttribute("listaLiga",oLigaFacade.listaLigaFacade(oLigaIF));

				// buscamos las listas de los equipos
				oLigaIF.setIdLiga(juego.getIdLiga());
				request.setAttribute("listaEquipo",oLigaFacade.listaEquipoPorLigaFacade(oLigaIF));
				
				/* verificamos si el adm taquilla aun no ha abierto el juego y colocamos sus logros por defecto*/
				if(juego.get_IdUsuario()!=null && !juego.get_IdUsuario()[0].equals(oUsuarioIF.getIdUsuario()) && !oUsuarioIF.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
					for(int i=0; i<juego.get_IdUsuario().length ;i++) {
						if(!Constants.isNull(juego.get_IdUsuario()[i]) && !juego.get_IdUsuario()[i].equals(oUsuarioIF.getIdUsuario())) {
							try {
								if(Double.parseDouble(oUsuarioIF.getLogrosAltaBaja())!=0) {
									juego.get_TotalLogro()[i]=oUsuarioIF.getLogrosAltaBaja();
								}
							} catch(NullPointerException ex) {
								
							}
						}
					}
				}
				
				
				
				// buscamos las listas de los lanzadores
				if(!isMultiEquipo) {
					for(int i=0; i<juego.get_IdEquipo().length;i++) {
						LanzadorIF oLanzadorTO = new LanzadorTO(); 
						LanzadorFacade oLanzadorFacade = new LanzadorFacade(request);
						oLanzadorTO.setIdEquipo(juego.get_IdEquipo()[i]);
						request.setAttribute("listaLanzador".concat(String.valueOf(i)),oLanzadorFacade.listaLanzadorFacade(oLanzadorTO));
					}
				}
				
				JuegoEquipoIF oJuegoEquipoIF = new JuegoEquipoTO();
				oJuegoEquipoIF.setIdJuego(juego.getIdJuego());
				
				request.setAttribute("modificar", new Boolean(true));
				modificar = true;

				
				// quitamos las menues anteriores de la session;
				request.getSession().removeAttribute("forma");
				
				/* vamos a cargar algunas tablas de uso general */
				request.setAttribute("listaDeporte",oJuegoFacade.listaDeporteMultipleFacade(oJuegoFacade.listaDeporteFacade(), false));
			}
			request.setAttribute("equipoEmpate",oJuegoFacade.equipoEmpateFacade());
			
			request.setAttribute("listaDeporteEmpate",oJuegoFacade.listaDeporteEmpateJavaScriptFacade());

			request.setAttribute("juego", juego);

			/* vamos a cargar algunas tablas de uso general */
			if(request.getAttribute("listaDeporte")==null) {
				request.setAttribute("listaDeporte",oJuegoFacade.listaDeporteMultipleFacade(oJuegoFacade.listaDeporteFacade(false),false));
			}
			
			// buscamos la lista de los logros sugeridos
			LogrosFacade oLogrosFacade = new LogrosFacade(request);
			request.setAttribute("logros",oLogrosFacade.listaLogrosJSFacade());
			
			if(!modificar && request.getSession().getAttribute("forma")!=null) {
				forma = (RegisterGameActionForm) request.getSession().getAttribute("forma");
				
				juego.setIdDeporte(forma.getIdDeporte());
				juego.setFechaIni(Constants.getFechaCortaSqlToHtml(forma.getFechaIni()));
				juego.setHora(forma.getHora());
				juego.setMinuto(forma.getMinuto());
				juego.setMinutosCierre(forma.getMinutosCierre());
				juego.setIdLiga(forma.getIdLiga());
				juego.setIdCampeonato(forma.getIdCampeonato());
				juego.setNombre(forma.getNombre());

				LigaFacade oLigaFacade = new LigaFacade(request);
				LigaIF oLigaIF = new LigaTO();
				oLigaIF.setIdDeporte(juego.getIdDeporte());
				request.setAttribute("listaLiga",oLigaFacade.listaLigaFacade(oLigaIF));

				// buscamos las listas de los equipos
				oLigaIF.setIdLiga(juego.getIdLiga());
				request.setAttribute("listaEquipo",oLigaFacade.listaEquipoPorLigaFacade(oLigaIF));
			}
			
			if(juego.getIdStatusJuego()!=null && juego.getIdStatusJuego().equals(Constants.STATUS_JUEGO_BORRADOR)) {
				return mapping.findForward("success");
			} else if(juego.getIdStatusJuego()!=null && juego.getIdStatusJuego().equals(Constants.STATUS_JUEGO_ABIERTO)) {
				return mapping.findForward("successOpen");
			} else if(juego.getIdStatusJuego()!=null && juego.getIdStatusJuego().equals(Constants.STATUS_JUEGO_CERRADO) ) {
				return mapping.findForward("successClose");
			} else if(juego.getIdStatusJuego()!=null && juego.getIdStatusJuego().equals(Constants.STATUS_JUEGO_SUSPENDIDO) ) {
				return mapping.findForward("successClose");
			} else if(juego.getIdStatusJuego()!=null && juego.getIdStatusJuego().equals(Constants.STATUS_JUEGO_TOTALIZADO) ) {
				return mapping.findForward("successClose");
			} else if(!oUsuarioIF.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				return mapping.findForward("error");
			}
			return mapping.findForward(isMultiEquipo?"successMulti":"success");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			return Constants.FORWARD_START;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.FORWARD_START;
		}
	}
}
