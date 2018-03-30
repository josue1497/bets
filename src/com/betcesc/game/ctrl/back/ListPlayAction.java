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

public class ListPlayAction extends Action {

	private static final Log log = LogFactory.getLog(ListUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet lista = null;
		CachedRowSet listaJugador;
		JugadaIF oJugadaTO = new JugadaTO();
		JugadaIF oJugada2TO = new JugadaTO();
		String fecha = "";
		String jugador = "";
		String estado = "";
		String fecha2 = "";
		String jugador2 = "";
		
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);
			
			fecha = (request.getParameter("fechaJugada")!=null?request.getParameter("fechaJugada"):(session.getAttribute("fechaJugada")!=null?session.getAttribute("fechaJugada").toString():""));
			jugador = (request.getParameter("jugador")!=null?request.getParameter("jugador"):(session.getAttribute("jugador")!=null?session.getAttribute("jugador").toString():""));
			estado = (request.getParameter("estado")!=null?request.getParameter("estado"):(session.getAttribute("estado")!=null?session.getAttribute("estado").toString():""));

			if(request.getParameter("fechaJugada")==null && session.getAttribute("fechaJugada")==null ) {
				fecha = Constants.getFechaCorta();
			}
			
			oJugadaTO.setFechaSis(fecha);
			oJugadaTO.setIdUsuario(jugador);
			oJugadaTO.setIdStatusJugada(estado);
			
			oJugada2TO.setFechaSis(fecha);
			//oJugada2TO.setIdUsuario(jugador);
			oJugada2TO.setIdStatusJugada(estado);
			
			int orden = 0;
			try {
				orden = Integer.parseInt(request.getParameter("orden"));
			} catch(Exception e) {
				try {
					orden = Integer.parseInt(String.valueOf(session.getAttribute("listPlay_orden")));
				} catch(Exception ex) {
					
				}
			}
			
			if(request.getParameter("isModulo")==null) {
				lista = juegoFacade.listaPaginaJugadaFacade(usuario,oJugadaTO,oJugada2TO,orden,null);
			}
			
			if(usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				listaJugador = usuarioFacade.listaJugadorAdmFacade(usuario);
			} else {
				listaJugador = usuarioFacade.listaJugadorAdmTaqFacade(usuario);
			}

			String pageNum = request.getParameter("pageNum");
			if(pageNum==null) {
				pageNum="1";
			}
			//log.info(pageNum);
			//log.info(orden);
			session.setAttribute("pageNum",new Integer(pageNum));
			session.setAttribute("listPlay_orden", String.valueOf(orden));
			session.setAttribute("fechaJugada",fecha);
			session.setAttribute("jugador",jugador);
			session.setAttribute("estado",estado);
			if(lista!=null) {
				request.setAttribute("lista",lista);
			}
			request.setAttribute("listaJugador",listaJugador);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
