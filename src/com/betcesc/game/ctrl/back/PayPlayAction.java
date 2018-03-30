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
import com.betcesc.game.exceptions.SecurityIDNotValidException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.JuegoFacade;
import com.betcesc.game.interfaces.JugadaIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.JugadaTO;
import com.jade.util.Encriptor;

public class PayPlayAction extends Action {

	private static final Log log = LogFactory.getLog(PayPlayAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		JuegoFacade juegoFacade;
		ActionErrors errores = new ActionErrors();
		JugadaIF oJugadaTO = new JugadaTO();

		try {

			UsuarioIF usuario = Constants.getUserSession(request);
			
			if(!usuario.getIdRol().equals(Constants.ROL_JUGADOR_DE_TAQUILLA)) {
				throw new SessionInvalidException("El usuario no puede realizar esta operacion");
			}

			juegoFacade = new JuegoFacade(request);

			oJugadaTO.setIdJugada(request.getParameter("idJugada"));

			// validamos el codigo SID de la jugada
			//if(!usuario.getIdPerfil().equals(Constants.PERFIL_ADMINISTRADOR) ) {
			if(usuario.getPagoClave().equals(Constants.PAGO_CLAVE_SI)) {
				if(!Encriptor.getSID(request.getParameter("idJugada")).equals(request.getParameter("SID"))) {
					throw new SecurityIDNotValidException("El SID no es correcto, no puede pagar la jugada");
				}
			}

			juegoFacade.pagarJugadaFacade(oJugadaTO);
			
			if(request.getParameter("reload")!=null) {
				request.setAttribute("reload", new Boolean(true));
			}

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		} catch (SecurityIDNotValidException e) {
			errores.add("error", new ActionError("error.sistema.sid"));
			ActionForward forward = new ActionForward("/showTicketSimple.do?error=".concat(e.getMessage()));
			return forward;
		} catch (Exception e) {
			errores.add("error", new ActionError("error.sistema.general"));
		}
		saveErrors(request, errores);
		return mapping.findForward("success");
	}

}
