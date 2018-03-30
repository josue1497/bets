package com.betcesc.game.ctrl.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.betcesc.game.common.Constants;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JugadaTO;

public class ShowTicketAction extends Action {

	private static final Log log = LogFactory.getLog(ListGamePlayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		ActionErrors errores = new ActionErrors();
		JugadaIF oJugadaTO = new JugadaTO();

		try {

			UsuarioIF usuario = Constants.getUserSession(request);

			juegoFacade = new JuegoFacade(request);
			
			oJugadaTO.setIdJugada(request.getParameter("idJugada"));
			
			request.setAttribute("ticket",juegoFacade.getJugadaFacade(oJugadaTO));
			
			if(request.getParameter("pendiente")!=null) {
				request.setAttribute("pendiente", request.getParameter("pendiente"));
			}
			
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch (Exception e) {
			errores.add("error",new ActionError("error.sistema.general"));
		}
		saveErrors(request, errores);
		return mapping.findForward("success");
	}

}
