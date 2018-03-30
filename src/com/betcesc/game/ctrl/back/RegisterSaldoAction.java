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
import com.betcesc.game.exceptions.InvalidUserException;
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.CuentaJugadorIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.CuentaJugadorTO;
import com.betcesc.game.to.UsuarioTO;

public class RegisterSaldoAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterUserAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		ActionErrors errores = new ActionErrors();
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			UsuarioIF oUsuarioTO = new UsuarioTO();
			CuentaJugadorIF oCuentaJugadorTO = new CuentaJugadorTO();

			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				return mapping.findForward("end");
			}

			UsuarioFacade oUsuarioFacade = new UsuarioFacade(request);
			
			oUsuarioTO.setIdUsuario(request.getParameter("idUsuario"));
			oUsuarioTO = oUsuarioFacade.consultarUsuarioFacade(oUsuarioTO);
			
			oCuentaJugadorTO.setIdJugador(oUsuarioTO.getIdUsuario());
			oCuentaJugadorTO.setIdUsuario(usuario.getIdUsuario());
			oCuentaJugadorTO.setNombreJugador(oUsuarioTO.getUsuario());
			
			request.setAttribute("cuenta", oCuentaJugadorTO );

		} catch (InvalidUserException e) {
			errores.add("error", new ActionError("error.sistema.administrador.only"));
			saveErrors(request, errores);
			return mapping.findForward("error");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}

		return mapping.findForward("success");

	}
}
