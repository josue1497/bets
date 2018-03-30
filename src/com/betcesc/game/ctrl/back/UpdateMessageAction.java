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
import com.betcesc.game.interfaces.UsuarioIF;
import com.betcesc.game.to.ParametrosTO;

public class UpdateMessageAction extends Action {

	private static final Log log = LogFactory.getLog(UpdateMessageAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ParametrosFacade oParametrosFacade = new ParametrosFacade(request);
		ParametrosTO oParametrosTO = new ParametrosTO();
		try {
			UsuarioIF usuario = Constants.getUserSession(request);
 
			String AVISO = "AVISO_HOME_".concat(Constants.getDominio(request).getIdDominio());
			String mensaje = request.getParameter("aviso_home");
			mensaje = mensaje.replaceAll("'", "");
			
			oParametrosTO.setNombre(AVISO);
			oParametrosTO.setValor(mensaje);
			// actualizamos el parametro
			oParametrosFacade.actualizarParametrosFacade(oParametrosTO);
			
		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}

		return mapping.findForward("success");
	}

}
 