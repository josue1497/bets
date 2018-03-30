package com.betcesc.game.ctrl.front;

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
import com.betcesc.game.dao.MenuDAO;
import com.betcesc.game.exceptions.SessionDuplicateException;
import com.betcesc.game.exceptions.UserNotFoundException;
import com.betcesc.game.facade.UsuarioFacade;
import com.betcesc.game.interfaces.UsuarioIF;

public class EndSessionAction extends Action {

	private static final Log log = LogFactory.getLog(EndSessionAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");

		UsuarioIF user = null;
		UsuarioFacade usuarioFacade = new UsuarioFacade(request);
		ActionErrors errores = new ActionErrors();
		try {
			user = (UsuarioIF) request.getSession().getAttribute("usuario");
			if (user != null) {
				usuarioFacade.eliminarUsuarioEnSession(user, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("success".concat(Constants.getDominio(request).getIdDominio()));
	}
}
