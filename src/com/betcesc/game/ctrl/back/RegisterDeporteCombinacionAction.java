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
import com.betcesc.game.interfaces.UsuarioIF;

public class RegisterDeporteCombinacionAction extends Action {

	private static final Log log = LogFactory.getLog(RegisterDeporteCombinacionAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Iniciando excecute()");
		UsuarioFacade usuarioFacade;
		JuegoFacade juegoFacade;
		CachedRowSet lista;

		try {
			UsuarioIF usuario = Constants.getUserSession(request);
			HttpSession session = request.getSession();
			juegoFacade = new JuegoFacade(request);
			
			juegoFacade.registerDeportePorUsuarioFacade();
			
			session.setAttribute("MENSAJE", "Cambios Aplicados");

		} catch (SessionInvalidException e) {
			return Constants.FORWARD_START;
		}
		return mapping.findForward("success");
	}

}
