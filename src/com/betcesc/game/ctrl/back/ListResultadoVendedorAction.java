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

public class ListResultadoVendedorAction extends Action {

	private static final Log log = LogFactory.getLog(ListResultadoVendedorAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet lista;
		JugadaIF oJugadaTO = new JugadaTO();
		JugadaIF oJugada2TO = new JugadaTO();
		
		String fecha = "";
		String fecha2 = "";

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			usuarioFacade = new UsuarioFacade(request);

			fecha = request.getParameter("fecha");
			fecha2 = request.getParameter("fecha2");

			if(Constants.isNull(fecha)) {
				fecha = Constants.getFechaCorta();
			}
			if(Constants.isNull(fecha2)) {
				fecha2 = Constants.getFechaCorta();
			}
			
			oJugadaTO.setFechaSis(fecha);
			oJugada2TO.setFechaSis(fecha2);
			
			int orden = 0;
			try {
				orden = Integer.parseInt(request.getParameter("orden"));
			} catch(Exception e) {
				
			}

			lista = juegoFacade.listaResultadoVendedorFacade(usuario,oJugadaTO,oJugada2TO);
			
			request.setAttribute("fecha",fecha);
			request.setAttribute("fecha2",fecha2);
			request.setAttribute("lista",lista);

			return mapping.findForward("success");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
