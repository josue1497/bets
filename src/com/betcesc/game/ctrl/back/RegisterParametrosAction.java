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
import com.betcesc.game.exceptions.SessionInvalidException;
import com.betcesc.game.facade.ParametrosFacade;
import com.betcesc.game.interfaces.ParametrosIF;
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.ParametrosTO;

public class RegisterParametrosAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterParametrosAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		try {
			UsuarioIF usuario = Constants.getUserSession(request);

			if(!usuario.getIdRol().equals(Constants.ROL_ADMINISTRADOR)) {
				return mapping.findForward("end");
			}
			
			ParametrosIF oParametrosTO = new ParametrosTO();
			oParametrosTO.setNombre(usuario.getNombre());
			
			request.setAttribute("modificar", new Boolean(false));
			ParametrosFacade oUsuarioFacade = new ParametrosFacade(request);
			
			if(!Constants.isNull(request.getParameter("nombre"))) {
				
				oParametrosTO.setNombre(request.getParameter("nombre"));
				oParametrosTO = oUsuarioFacade.consultarParametrosFacade(oParametrosTO);
				request.setAttribute("modificar", new Boolean(true));
			}
			request.setAttribute("parametros", oParametrosTO);
			
			return mapping.findForward("success");
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
	}
	
}
